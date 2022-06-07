package com.hspedu.thread;

import java.util.Scanner;

/**
 * @author 吴辉
 * @version 1.0
 */
public class Homework0101 {
    public static void main(String[] args) {
        //(1)在main方法中启动两个线程
        //(2)第1个线程循环随机打印100以内的整数
        //(3)直到第2个线程从键盘读取了“Q”命令。
        Method01 method01 = new Method01();
        Method02 method02 = new Method02(method01);
        method01.setName("线程一");
        method02.setName("线程二");
        method01.start();
        method02.start();
    }
}
class Method01 extends Thread {
    private boolean loop = true;
    public void setLoop(boolean loop) {
        this.loop = loop;
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
class Method02 extends Thread {
    private Method01 method01;//将线程一当做线程二的属性，方便对线程一的loop属性进行修改
    private Scanner sca = new Scanner(System.in);//这样写更好，把Scanner对象当做变量，用起来更方便
    public Method02(Method01 method01) {
        this.method01 = method01;
    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "在运行");
        System.out.println("请输入\"Q\"表示退出线程一和线程二");
        while (true) {
            if (sca.nextLine().toUpperCase().equals("Q")) {
                method01.setLoop(false);
                System.out.println("线程二退出");
                break;
            }
        }
    }
}