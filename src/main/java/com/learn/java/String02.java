package com.learn.java;

public class String02 {

    /**
     * 用三反转等效左移字符串
     *
     * @param from
     *            需要移动的字符串
     * @param index
     *            需要移动的位数
     */
    public static void print(String from, int index) {
        from = reChange(from);	//循环左移 （先整体，再部分）
        System.out.println(from);
        String first = reChange(from.substring(0, from.length() - index));
        System.out.println(first);
        String second = reChange(from.substring(from.length() - index));
        System.out.println(second);
        from = first + second;
        //from = reChange(from); //循环右移（先部分在整体）
        System.out.println(from);
    }


    /**
     * 反转字符串,把from字符串反转过来（很常见牢记）
     * 字符串求长度用length(),数组求长度用length
     * @param from
     * @return
     */
    public static String reChange(String from) {
        char[] froms = from.toCharArray();
        for (int i = 0; i < from.length() / 2; i++) {
            char temp = froms[i];
            froms[i] = froms[from.length() - 1 - i];
            froms[froms.length - 1 - i] = temp;
        }
        return String.valueOf(froms);
    }



    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String string="abcde";
        String02.print(string, 2);
        //System.out.println(String02.reChange(string));
    }

}
