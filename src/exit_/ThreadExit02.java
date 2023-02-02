package exit_;

/**
 * @author haohao
 * @version 1.0
 */
public class ThreadExit02 {
    public static void main(String[] args) throws InterruptedException {
        T1 t1 = new T1();
        t1.setName("gong");
        t1.setPriority(Thread.MIN_PRIORITY);
        t1.start();
        //主线程打印五个hi，然后就中断子线程的休眠
        for (int i = 0; i < 5; i++) {
            Thread.sleep(100);
            System.out.println("hi"+i);
        }
        System.out.println(t1.getName()+"线程的优先级是"+t1.getPriority());
        t1.interrupt();

    }
}
class T1 extends Thread{

    @Override
    public void run() {
        while(true) {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName()+"wangwang" +i );
            }

            try {
                System.out.println("休眠中");
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName()+"被interrupt了");
            }
        }
    }
}
