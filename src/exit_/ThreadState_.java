package exit_;

/**
 * @author haohao
 * @version 1.0
 */
public class ThreadState_ {
    public static void main(String[] args) throws InterruptedException {
        T4 t4 = new T4();
        System.out.println(t4.getName()+"状态："+ t4.getState());
        t4.start();
        while(Thread.State.TERMINATED!= t4.getState()){
            System.out.println(t4.getName()+"状态："+ t4.getState());
            Thread.sleep(100);
        }
        System.out.println(t4.getName()+"状态："+ t4.getState());
    }
}
class T4 extends Thread{
    @Override
    public void run() {
        while(true){
            for (int i = 0; i < 10; i++) {
                System.out.println("hi"+i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            break;
        }
    }
}
