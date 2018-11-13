package org.apress.prohadoop.myhadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class MapJoin {

    static class RecordMapper extends Mapper<LongWritable, Text, Text, Text> {
        private Map<String, String> stationMap = new HashMap<String, String>();

        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            //预处理，把要关联的文件加载到缓存中
            Path[] paths = context.getLocalCacheFiles();
            //新的检索缓存文件的API是 context.getCacheFiles() ，而 context.getLocalCacheFiles() 被弃用
            //然而 context.getCacheFiles() 返回的是 HDFS 路径； context.getLocalCacheFiles() 返回的才是本地路径

            //这里只缓存了一个文件，所以取第一个即可
            BufferedReader reader = new BufferedReader(new FileReader(paths[0].toString()));
            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    String[] vals = line.split("\\t");
                    if (vals.length == 2) {
                        stationMap.put(vals[0], vals[1]);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                reader.close();
            }
            super.setup(context);
        }

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] vals = value.toString().split("\\t");
            if (vals.length == 3) {
                String stationName = stationMap.get(vals[0]); //Join
                stationName = stationName == null ? "" : stationName;
                context.write(new Text(vals[0]),
                        new Text(stationName + "\t" + vals[1] + "\t" + vals[2]));
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        if (otherArgs.length != 3) {
            System.err.println("Parameter number is wrong, please enter three parameters：<ncdc input> <station input> <output>");
            System.exit(-1);
        }

        Path inputPath = new Path(otherArgs[0]);
        Path stationPath = new Path(otherArgs[1]);
        Path outputPath = new Path(otherArgs[2]);

        Job job = Job.getInstance(conf, "MapJoin");
        job.setJarByClass(MapJoin.class);

        FileInputFormat.addInputPath(job, inputPath);
        FileOutputFormat.setOutputPath(job, outputPath);

        job.addCacheFile(stationPath.toUri()); //添加缓存文件，可添加多个

        job.setMapperClass(RecordMapper.class);
        job.setMapOutputKeyClass(Text.class);

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
