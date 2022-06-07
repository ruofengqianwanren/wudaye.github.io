package com.hspedu.thread;

/**
 * @author 吴辉
 * @version 1.0
 */
public class ThreadExercise01 {
    public static void main(String[] args) throws InterruptedException {
        //1.主线程每隔1s,输出 hi ,一共10次
        //2.当输出到hi 第5次时，启动一个子线程(要求实现Runnable),
        //每隔1s输出hello，等该线程输出10次 hello后，退出
        //3.主线程继续输出hi，直到主线程退出.
        Sun sun = new Sun();
        Thread thread = new Thread(sun);
        for (int i = 1; i <= 10; i++) {
            Thread.sleep(1000);
            System.out.println("hi"+ i);
            if (i == 5){
                thread.start();
                thread.join();
            }
        }
        System.out.println("主线程结束");
    }
}
class Sun implements Runnable{
    @Override
    public void run() {
        int temp = 1;
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            System.out.println("hello"+ temp++);
            if (temp == 11){
                System.out.println("子线程结束");
                break;
            }
        }
    }
}