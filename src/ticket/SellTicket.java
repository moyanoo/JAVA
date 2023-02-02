package ticket;

/**
 * @author haohao
 * @version 1.0
 * 使用多线程模拟三个窗口同时售票
 */
public class SellTicket {
    public static void main(String[] args) {
//        SellTicket01 sellTicket01 = new SellTicket01();
//        SellTicket01 sellTicket02 = new SellTicket01();
//        SellTicket01 sellTicket03 = new SellTicket01();
//        sellTicket01.start();
//        sellTicket02.start();
//        sellTicket03.start();
//        SellTicket02 sellTicket02 = new SellTicket02();
//        new Thread(sellTicket02).start();
//        new Thread(sellTicket02).start();
//        new Thread(sellTicket02).start();
        SellTicket03 sellTicket03 = new SellTicket03();
        new Thread(sellTicket03).start();
        new Thread(sellTicket03).start();
        new Thread(sellTicket03).start();
     }
}
//使用synchronized实现线程同步
class SellTicket03 implements Runnable {
    private int num = 100;//多个线程共享num
    private boolean loop=true;
    public synchronized void m(){
        if (num <= 0) {
            System.out.println("售票结束");
            loop=false;
            return;
        }
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("窗口" + Thread.currentThread().getName() + "售出一张票"
                + "剩余票数=" + (--num));
    }

    @Override
    public void run() {//同步方法在用一时刻，只能有同一线程来执行run方法
        while (loop) {
            m();
    }
}
//Thread方式
class SellTicket01 extends Thread{
    private int num=100;//多个线程共享num
    @Override
    public void run() {
        while(true){
            if(num<=0){
                System.out.println("售票结束");
                break;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("窗口"+Thread.currentThread().getName()+"售出一张票"
                    +"剩余票数="+(--num));
        }
    }
}
//实现接口方式
class SellTicket02 implements Runnable {
    private int num = 100;//多个线程共享num

    @Override
    public void run() {
        while (true) {
            if (num <= 0) {
                System.out.println("售票结束");
                break;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("窗口" + Thread.currentThread().getName() + "售出一张票"
                    + "剩余票数=" + (--num));
        }
    }
}}
