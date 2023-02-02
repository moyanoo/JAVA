package thread;

/**
 * @author haohao
 * @version 1.0
 */
public class thread01 {
    public static void main(String[] args) {
        //创建一个cat对象可以当做线程使用
        Cat cat = new Cat();
        cat.start();//启动线程
        for (int i = 0; i < 60; i++) {
            System.out.println("i="+i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
//当一个类继承了Thread类，该类就可以当做一个线程来使用
//一般重写run方法，写自己的代码
//run Thread类实现了Runnable接口的run方法

class Cat extends Thread {
    int times=0;
    public void run() {//重写了run方法
        while (true) {
            System.out.println("miao,miao"+(++times));
            //休眠一秒
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(times==80){
                break;
            }
        }
    }

}
