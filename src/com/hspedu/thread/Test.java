package com.hspedu.thread;

import java.util.Scanner;

/**
 * @author 吴辉
 * @version 1.0
 */
public class Test {
    public static void main(String[] args) {
        //(1)在main方法中启动两个线程
        //(2)第1个线程循环随机打印100以内的整数
        //(3)直到第2个线程从键盘读取了“Q”命令。
        Method011 method011 = new Method011();
        Method022 method022= new Method022();
        method011.setName("线程一");
        method022.setName("线程二");
        method011.start();
        method022.start();
    }
}
@SuppressWarnings("all")
class Method011 extends Thread {
    private static boolean loop = true;
    public static void setLoop(boolean loop) {
        Method011.loop = loop;
    }
    @Override
    public void run() {
        while (loop) {
            System.out.println(Thread.currentThread().getName()
                    + "随机打印100以内的整数：" + (int) (Math.random() * 100 + 1));
            try {
                Thread.sleep(1000);//休眠一秒等线程二的输入操作
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("线程一退出");
    }
}
@SuppressWarnings("all")
class Method022 extends Thread {
    private Scanner sca = new Scanner(System.in);//这样写更好，把Scanner对象当做变量，用起来更方便
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "在运行");
        System.out.println("请输入\"Q\"表示退出线程一和线程二");
        while (true) {
            if (sca.nextLine().toUpperCase().equals("Q")) {
                Method011.setLoop(false);
                System.out.println("线程二退出");
                break;
            }
        }
    }
}