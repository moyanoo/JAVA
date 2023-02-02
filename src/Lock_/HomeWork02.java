package Lock_;

/**
 * @author haohao
 * @version 1.0
 */
public class HomeWork02 {
    public static void main(String[] args) {
        A1 a1 = new A1();
        Thread thread1 = new Thread(a1);
        thread1.setName("a2");
        Thread thread2 = new Thread(a1);
        thread2.setName("a1");
        thread1.start();
        thread2.start();
    }
}

class A1 implements Runnable {
    private static int Mon = 10000;
    private static boolean loop = true;
    @Override
    public void run() {
        while (loop) {
            synchronized (this) {
                if (Mon <= 0) {
                    System.out.println("你的余额已不足");
                    loop = false;
                    return;
                }
                Mon -= 1000;
                System.out.println(Thread.currentThread().getName() + "你的余额还有" + Mon + "元");
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
