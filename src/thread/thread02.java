package thread;

import java.net.Proxy;

/**
 * @author haohao
 * @version 1.0
 */
public class thread02 {
    public static void main(String[] args) {
        Dog dog=new Dog();
        //不能调用start
        Thread thread = new Thread(dog);
        thread.start();

        Tiger tiger = new Tiger();
        proxy proxy = new proxy(tiger);
        proxy.start();
    }
}
class Animal{}
class Tiger extends Animal implements Runnable{

    @Override
    public void run() {

    }
}
class proxy implements Runnable{//可以吧proxy当做ThreadProxy

    private Runnable target=null;//属性，类型是reunnable

    @Override
    public void run() {
        if(target!=null){
            target.run();
        }
    }

    public proxy(Runnable target) {
        this.target = target;
    }
    
    public void start(){
        start0();
    }

    public void start0(){
        run();
    }
}
class Dog implements Runnable{//通过实现Runnable接口开发线程
    int count=0;
    @Override
    public void run() {
        while(true){
            System.out.println("wangwang"+(++count)+Thread.currentThread().getName());
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
