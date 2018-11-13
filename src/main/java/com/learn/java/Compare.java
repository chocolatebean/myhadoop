package com.learn.java;

import java.util.Comparator;

public class Compare {
    class IntegerComparator implements Comparator<Integer> {
        public int compare(Integer a, Integer b) {
            return String.valueOf(a).compareTo(String.valueOf(b));
        }
    }

    private IntegerComparator getEntry(){
        return new Compare().new IntegerComparator();
    }

    public static void main (String args []){
        Compare com = new Compare();
        IntegerComparator ic = com.getEntry();
        int a= 21;
        int b= 23;
        int res = 0;
        res = ic.compare(a,b);
        System.out.println(res);
    }
}
