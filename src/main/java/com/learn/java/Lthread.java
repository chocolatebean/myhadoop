package com.learn.java;

public class Lthread extends Thread {
    private String name;
    public Lthread(String name) {
        this.name=name;
    }
    public void run() {
        int a;
        for (int i = 0; i < 5; i++) {
            System.out.println(name + "运行  :  " + i);
            try {
                a = (int) Math.random() * 10;
                System.out.println("sleepmills=" + a);
                sleep(a);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
 class Main {

    public static void main(String[] args) {
        Lthread mTh1=new Lthread("A");
        Lthread mTh2=new Lthread("B");
        mTh1.start();
        mTh2.start();

    }
}
