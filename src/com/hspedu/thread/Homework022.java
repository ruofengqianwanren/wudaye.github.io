package com.hspedu.thread;

/**
 * @author 吴辉
 * @version 1.0
 */
public class Homework022 {
    public static void main(String[] args) {
        //(1)有2个用户分别从同一个卡上取钱(总额:10000)
        //(2)每次都取1000,当余额不足时，就不能取款了
        //(3)不能出现超取现象--->线程同步问题.
        User1 user1 = new User1();
        Thread thread01 = new Thread(user1);
        thread01.setName("用户一");
        Thread thread02 = new Thread(user1);
        thread02.setName("用户二");
        thread01.start();
        thread02.start();
    }
}
class User1 implements Runnable {
    private static int sal = 100000;
    @Override
    public void run() {
        while (true) {
            //解读
            //1.这里使用synchronized 实现了线程同步
            //2.当多个线程执行到这里时，就会去争夺this对象锁
            //3.哪个线程争夺到(获取)this对象锁，就执行synchronized 代码块，执行完后，会释放this对象锁
            //4.争夺不到this对象锁，就blocked堵塞 ，准备继续争夺
            //5.this对象锁是非公平锁，谁抢到就是谁的，不按排队顺序来
            synchronized (this) {//谁先拿到锁谁就先执行线程同步，执行完同步代码块，立即释放锁
                //就开始下一轮的抢锁过程，还是谁先抢到谁就先执行同步代码块
                if (sal < 1000) {
                    System.out.println("当前余额不足，" + Thread.currentThread().getName() + "不能取款");
                    return;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "取走1000" + "卡上还剩" + (sal - 1000) + "元");
                sal = sal - 1000;
            }
        }
    }
}