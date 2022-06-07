package com.hspedu.thread;

/**
 * @author 吴辉
 * @version 1.0
 */
public class SellTicket {
    public static void main(String[] args) {
        Top top = new Top();
      new Thread(top).start();
      new Thread(top).start();
      new Thread(top).start();

    }
}
class Top implements Runnable {//售票问题用Runnable解决，多个线程共享一个资源ticket
    private boolean loop = true;
    private static int ticket = 100;
    public  synchronized void sell(){//加了线程同步，同一时间只能有一个线程在执行这个方法，
        //其他线程只能等待此线程执行完这个方法，才能执行此方法。也就是一个一个来。
        if (ticket <= 0) {
            System.out.println(Thread.currentThread().getName()+"窗口票已卖完");
            loop = false;
            return;
        }
        try {
            Thread.sleep(50);//模拟卖票耗时
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"窗口"+"余票还有：" + --ticket);

    }
    @Override
    public void run() {//每个线程每次开始执行的是这里的代码
        while (loop) {
           sell();
        }
    }
}