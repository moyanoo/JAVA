package exit_;

/**
 * @author haohao
 * @version 1.0
 */
public class Threadyield03 {
    public static void main(String[] args) throws InterruptedException {
        T2 t2 = new T2();

        for (int i = 1; i <= 10; i++) {
            System.out.println("hi"+i);
            Thread.sleep(1000);
            if (i==5){
                System.out.println("让子线程运行");
                t2.start();
                t2.join();
                System.out.println("继续运行主线程");
            }
        }
    }
}
class T2 extends Thread{
    private int i;
    @Override
    public void run() {
        while(true){
            System.out.println("hello"+(++i));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (i==10){
                break;
            }
        }
    }
}
