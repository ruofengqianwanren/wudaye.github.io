package com.hspedu.thread;

/**
 * @author 吴辉
 * @version 1.0
 */
public class ThreadDaemon01 {
    public static void main(String[] args) throws InterruptedException {
        Sun01 sun01 = new Sun01();
        Thread thread = new Thread(sun01);
        thread.setDaemon(true);//设置线程thread为守护线程，被守护线程结束，thread线程也会结束，
        //相当于被守护线程外面套着一个守护线程thread，被守护线程结束的同时，守护线程也会结束
        thread.start();
        for (int i = 1; i <= 10; i++) {
            Thread.sleep(1000);
            System.out.println("hi" + i);
        }
        System.out.println("主线程结束");
    }
}
class Sun01 implements Runnable {
    @Override
    public void run() {
        int temp = 1;
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            System.out.println("hello" + temp++);
        }
    }
}