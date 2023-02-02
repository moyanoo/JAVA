package TankGame04;

import java.util.Vector;

/**
 * @author haohao
 * @version 1.0
 */
public class MyTank extends Tank {
    //定义一个Shot对象，表示射击行为
    Shot shot = null;
    //可以发射多可子弹
    Vector<Shot> shots=new Vector<>();
    public MyTank(int x, int y) {
        super(x, y);
    }

    public void ShotEnemyTank() {
        if (shots.size()==5){
            return;
        }
        //创建Shou对象，根据当前MyTank对象的位置来创建对象Shot
        switch (getDirect()) {//得到MyTank的方向
            case 0:
                shot = new Shot(getX() + 20, getY(), 0);
                break;
            case 1:
                shot = new Shot(getX() + 60, getY() + 20, 1);
                break;
            case 2:
                shot = new Shot(getX() + 20, getY() + 60, 2);
                break;
            case 3:
                shot = new Shot(getX(), getY() + 20, 3);
                break;
        }
        //把新创建的shot放入到集合中
        shots.add(shot);
        //启动Shot线程
        new Thread(shot).start();

    }
}
