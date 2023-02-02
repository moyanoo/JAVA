package exit_;

import javax.security.auth.login.CredentialNotFoundException;

/**
 * @author haohao
 * @version 1.0
 * 守护线程的定义
 */
public class ThreadMethod04 {
    public static void main(String[] args) throws InterruptedException {
        MyDaemonThread myDaemonThread = new MyDaemonThread();
        //把子线程定义为守护线程跟随主线程的结束而结束
        myDaemonThread.setDaemon(true);
        myDaemonThread.start();


        for (int i = 1; i <=10 ; i++) {
            System.out.println("工作");
            Thread.sleep(1000);
        }
    }
}
class MyDaemonThread extends Thread{
    @Override
    public void run() {
        while(true){
            System.out.println("聊天。。。");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
