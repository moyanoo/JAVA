package exit_;

/**
 * @author haohao
 * @version 1.0
 */
public class ThreadExit_ {
    public static void main(String[] args) throws InterruptedException {
        T t = new T();
        t.start();
        //main线程控制程序的终止，必须可以修改loop变量
        //让t1退出run方法从而终止线程   通知方式
        //让主线程休眠10秒再通知退出
        Thread.sleep(10*1000);
        t.setLoop(false);
    }
}
class T extends Thread{
    int count=0;
    //设置一个控制变量
    private boolean loop=true;
    @Override
    public void run() {
        while(loop){
            System.out.println("wangwang"+(++count)+Thread.currentThread().getName());
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
//            if (count==10){
//                break;
//            }
        }
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }
}
