package com.hspedu.thread;

/**
 * @author 吴辉
 * @version 1.0
 */
public class Runnable01 {
    public static void main(String[] args) {
        Dog dog = new Dog();
        Thread thread = new Thread(dog);//将实现了Runnable接口的dog对象放入到Thread中，
        // 利用代理模式，相当于thread对象帮助dog对象完成某些功能，帮助执行dog线程
        thread.start();//thread对象代理dog对象完成start方法，最终启动了dog线程
    }
}
class Dog implements Runnable {
    @Override
    public void run() {//重写Runnable的run方法
        System.out.println("当前线程的名字为：" + Thread.currentThread().getName());
        for (int i = 1; i <= 8; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("小狗汪汪叫***" + i);
        }
    }
}