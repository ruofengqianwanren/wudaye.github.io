package com.hspedu.thread;

/**
 * @author 吴辉
 * @version 1.0
 */
public class Runnable02 {
    public static void main(String[] args) {
        Person01 person01 = new Person01();
        Person02 person02 = new Person02();
        Thread thread01 = new Thread(person01);
        Thread thread02 = new Thread(person02);
        thread01.start();
        thread02.start();
    }
}
class Person01 implements Runnable {
    @Override
    public void run() {
        System.out.println("当前线程的名称" + Thread.currentThread().getName());
        int temp = 0;
        while (true) {
            System.out.println("Person01线程的第" + ++temp + "次输出");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(temp==5){

            }
            if (temp == 8) {
                break;
            }
        }
    }
}
class Person02 implements Runnable {
    @Override
    public void run() {
        System.out.println("当前线程的名称" + Thread.currentThread().getName());
        int temp = 0;
        while (true) {
            System.out.println("Person02线程的第" + ++temp + "次输出");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (temp == 9) {
                break;
            }
        }
    }
}