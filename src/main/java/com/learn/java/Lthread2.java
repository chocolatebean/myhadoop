package com.learn.java;

public class Lthread2 implements Runnable {
    private String name;

    public Lthread2(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(name + "运行  :  " + i);
            try {
                Thread.sleep((int) Math.random() * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}

class LthreadMain {

    public static void main(String[] args) {
        new Thread(new Lthread2("C")).start();
        new Thread(new Lthread2("D")).start();
    }
}
