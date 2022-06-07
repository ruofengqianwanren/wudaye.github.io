package com.hspedu.thread;
/**
 * @author 吴辉
 * @version 1.0
 */
public class Runnable04 {
    public static void main(String[] args) throws InterruptedException {
        DogOne01 dogOne01 = new DogOne01();
        dogOne01.setName("吴辉");//给线程改名字
        dogOne01.setPriority(6);//设置线程优先级
        dogOne01.start();//启动线程
        //在主线程中运行其他语句
        for (int i = 0; i < 5; i++) {
            Thread.sleep(1000);
            System.out.println("这是在主线程中执行的语句"+i);
        }
        System.out.println("当前线程优先级为："+Thread.currentThread().getPriority());//main线程的优先级为5
        dogOne01.interrupt();//立即中断dogOne01线程的sleep休眠过程（中断dogOne01线程不是终止dogOne01线程），
        //让dogOne01线程继续运行
        //该语句执行一次，就有一次的中断效果，执行完本次就不再中断dogOne01线程
    }
}
class DogOne01 extends Thread{
    @Override
    public void run() {
        int temp = 0;
        System.out.println("当前线程名称为："+Thread.currentThread().getName());//吴辉
        System.out.println("当前线程优先级为："+Thread.currentThread().getPriority());//6
        while (true){
            for (int i = 1; i < 5; i++) {
                System.out.println("循环"+i);
            }
            try {
                Thread.sleep(20000);//计划休息20秒
            } catch (InterruptedException e) {//捕获中断异常
                System.out.println("休眠过程被interrupt方法中断");//继续执行下面的语句，进行循环
            }
            System.out.println(++temp);
        }
    }
}