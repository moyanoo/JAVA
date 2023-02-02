package thread;

/**
 * @author haohao
 * @version 1.0
 */
public class Thread03 {
    public static void main(String[] args) {
        T1 t1 = new T1();
        T2 t2 = new T2();
        Thread thread1 = new Thread(t1);
        Thread thread2 = new Thread(t2);
        thread1.start();
        thread2.start();

    }
}

class T1 implements Runnable {
    int count = 0;

    @Override
    public void run() {
        while (true) {//每隔一秒输出，输出十次
            System.out.println("hello " + (++count));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (count==10){
                break;
            }
        }

    }
}

class T2 implements Runnable {
    int count = 0;

    @Override
    public void run() {
        while (true) {//每隔一秒输出，输出五次
            System.out.println("hi " + (++count));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (count==5){
                break;
            }
        }

    }
}
