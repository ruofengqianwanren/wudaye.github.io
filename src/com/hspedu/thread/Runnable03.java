package com.hspedu.thread;

/**
 * @author 吴辉
 * @version 1.0
 */
public class Runnable03 {
    public static void main(String[] args) throws InterruptedException {
        DogOne dogOne = new DogOne();
        Thread thread = new Thread(dogOne);
        thread.setName("thread");//更改线程名字
        thread.start();//启动子线程thread
        Thread.sleep(10000);//主线程休息10秒之后，
        // 执行dogOne对象的setLoop方法关闭dogOne线程
        dogOne.setLoop(false);
    }
}
class DogOne implements Runnable{
    private boolean loop = true;
    @Override
    public void run() {
        int temp = 0;
        System.out.println("当前线程名称为："+Thread.currentThread().getName());

        while (loop){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(++temp);
        }
    }
    public void setLoop(boolean loop) {
        this.loop = loop;
    }
}