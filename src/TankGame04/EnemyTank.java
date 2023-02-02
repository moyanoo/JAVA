package TankGame04;

import java.util.Vector;

/**
 * @author haohao
 * @version 1.0
 */
public class EnemyTank extends Tank implements Runnable {
    //在敌人坦克类使用Vector保存多个Shot
    Vector<Shot> shots = new Vector<>();
    boolean isLive = true;

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    @Override
    public void run() {
        while (true) {
            //判断如果shot.size=0
            if (isLive && shots.size() < 1) {
                Shot s=null;
                //判断坦克的方向创建对应的子弹
                switch(getDirect()){
                    case 0:
                        s=new Shot(getX()+20,getY(),0);
                        break;
                    case 1:
                        s=new Shot(getX()+60,getY()+20,1);
                        break;
                    case 2:
                        s=new Shot(getX()+20,getY()+60,2);
                        break;
                    case 3:
                        s=new Shot(getX(),getY()+20,3);
                        break;
                }
                shots.add(s);
                shots.add(s);
                new Thread(s).start();
            }
            //根据坦克的方向来继续移动
            switch (getDirect()) {
                case 0:
                    //让坦克沿着一个方向多走几步
                    for (int i = 0; i < 15; i++) {
                        if (getY() > 0) {
                            moveUp();
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }

                    }
                    break;
                case 1:
                    for (int i = 0; i < 15; i++) {
                        if (getX() + 60 < 1000) {
                            moveRight();
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }

                    }
                    break;
                case 2:
                    for (int i = 0; i < 15; i++) {
                        if (getY() + 60 < 750) {
                            moveDown();
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }

                    }
                    break;
                case 3:
                    for (int i = 0; i < 15; i++) {
                        if (getX() > 0) {
                            moveLife();
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }

                    }
                    break;
            }
            //休眠50毫秒
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //随机数改变坦克方向0-3
            setDirect((int) (Math.random() * 4));
            if (!isLive) {
                break;
            }
        }
    }
}
