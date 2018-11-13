package com.learn.java;



public class Test {

    /**
     * 返回斐波那契数第n个值,n从0开始
     * 实现方式，基于递归实现
     * @param n
     * @return
     * @author
     * @since
     */
    public static int getFib(int n){
        if(n < 0){
            return -1;
        }else if(n == 0){
            return 0;
        }else if(n == 1 || n ==2){
            return 1;
        }else{
            return getFib(n - 1) + getFib(n - 2);
        }
    }

    public enum SeasonEnum {
        SPRING(1), SUMMER(2), AUTUMN(3), WINTER(4);
        int seq;
        SeasonEnum(int seq){
            this.seq = seq;
        }
    }

    public static void main(String args[]){
        //System.out.println(Test.getFib(6));

        System.out.println(SeasonEnum.SPRING);

    }
}
