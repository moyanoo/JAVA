package Lock_;

import java.util.Locale;
import java.util.Scanner;

/**
 * @author haohao
 * @version 1.0
 */
public class HomeWork01 {
    public static void main(String[] args) {
        A a = new A();
        B b = new B(a);
        a.start();
        b.start();
    }
}
class A extends Thread{//输出1到100随机数
    private boolean loop=true;
    @Override
    public void run() {
        while(loop){
            System.out.println((int)(Math.random()*100+1));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }
}
class B extends Thread{
    private A a;
    private Scanner scanner=new Scanner(System.in);

    public B(A a) {
        this.a = a;
    }

    @Override
    public void run() {
        //接受用户的输入
        while (true) {
            System.out.println("请输入你的指令Q表示退出");
            char key = scanner.next().toUpperCase().charAt(0);
            if(key=='Q'){
                a.setLoop(false);
                break;
            }
        }
    }
}
