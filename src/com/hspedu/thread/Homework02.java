package com.hspedu.thread;

/**
 * @author 吴辉
 * @version 1.0
 */
public class Homework02 {
    public static void main(String[] args) {
        //(1)有2个用户分别从同一个卡上取钱(总额:10000)
        //(2)每次都取1000,当余额不足时，就不能取款了
        //(3)不能出现超取现象--->线程同步问题.
        User user = new User();
        Thread thread01 = new Thread(user);
        thread01.setName("用户一");
        Thread thread02 = new Thread(user);
        thread02.setName("用户二");
        thread01.start();
        thread02.start();
    }
}
class User implements Runnable {
    private boolean loop = true;
    private static int sal = 10000000;
    public synchronized void getMoney() throws Exception {//谁先拿到锁谁就先执行线程同步，执行完同步代码方法，立即释放锁
        //就开始下一轮的抢锁过程，还是谁先抢到谁就先执行同步代码方法
        if (sal <= 0) {
            System.out.println("当前余额不足，不能取款");
            loop = false;
            return;
        }
        Thread.sleep(100);
        System.out.println(Thread.currentThread().getName() + "取走1000" + "卡上还剩" + (sal - 1000) + "元");
        sal = sal - 1000;
    }
    @Override
    public void run() {
        while (loop) {
            try {
                getMoney();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}