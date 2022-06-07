package com.hspedu.thread;

/**
 * @author 吴辉
 * @version 1.0
 */
//运行 Thread01 程序时，相当于启动了一个进程Thread01，然后启动main线程，然后启动main线程下的子线程（若有子线程）
public class Thread01 {
    public static void main(String[] args) throws InterruptedException {//main是一个线程
        //1)请编写程序,开启一个线程,该线程每隔1秒。在控制台输出“哺瞄,我是小猫咪”
        //2)对上题改进:当输出8次瞄唶,我是小猫咪,结束该线程
        Cat cat = new Cat();//把Cat的对象当做线程，是main线程下的子线程
        cat.start();//启动cat子线程
        //main线程下的子线程启动时，不会堵塞其他的子线程，会和其他线程交替执行
        System.out.println("当前的线程为main主线程，名字为："+Thread.currentThread().getName());//main
        for (int i = 0; i < 12; i++) {
            Thread.sleep(1000);
            System.out.println(i);
        }
    }
}
class Cat extends Thread {
    @Override
    public void run() {//重写Thread的run方法
        int time = 0;
        System.out.println("当前线程名字：" + Thread.currentThread().getName());//获取当前线程名 //Thread-0
        while (true) {
            System.out.println("喵喵，我是小猫咪" + (++time));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.getMessage();
            }
            if (time == 8) {
                return;
                //break;
            }
        }
    }
}