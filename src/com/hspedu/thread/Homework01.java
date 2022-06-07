package com.hspedu.thread;
import java.util.Scanner;

/**
 * @author 吴辉
 * @version 1.0
 */
public class Homework01 {
    public static void main(String[] args) {
        //(1)在main方法中启动两个线程
        //(2)第1个线程循环随机打印100以内的整数
        //(3)直到第2个线程从键盘读取了“Q”命令。
        Task01 task01 = new Task01();
        Task02 task02 = new Task02();
        Thread thread01 = new Thread(task01);
        Thread thread02 = new Thread(task02);
        thread01.setName("线程一");
        thread02.setName("线程二");
        thread01.setDaemon(true);
        thread01.start();
        thread02.start();
    }
}
class Task01 implements Runnable {
    @Override
    public void run() {
        for (; ; ) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "随机打印100以内的整数："
                    + (int) (Math.random() * 100 + 1));
        }
    }
}

class Task02 implements Runnable {
    private Scanner sca = new Scanner(System.in);
    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + "在执行");
            if (sca.next().equals("Q")) {
                System.out.println("线程二结束");
                System.out.println("线程一结束");
                return;
            }
        }
    }
}