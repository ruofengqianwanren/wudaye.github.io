package com.hspedu.thread;

/**
 * @author 吴辉
 * @version 1.0
 */
public class Runnable021 {
    public static void main(String[] args) {
        Person011 person01 = new Person011();
        person01.start();
    }
}
class Person011 extends Thread {
    Person012 person012 = new Person012();
    @Override
    public void run() {
        int temp = 0;
        while (true) {
            System.out.println("Person011线程的第" + ++temp + "次输出");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (temp == 3) {
                person012.start();
                try {
                    person012.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (temp == 8) {
                break;
            }
        }
    }
}
class Person012 extends Thread {
    @Override
    public void run() {
        int temp = 0;
        while (true) {
            System.out.println("Person012线程的第" + ++temp + "次输出");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (temp == 8) {
                break;
            }
        }
    }
}