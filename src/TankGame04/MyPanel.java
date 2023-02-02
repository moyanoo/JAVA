package TankGame04;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 * @author haohao
 * @version 1.0
 * 绘图区域
 */
//为了监听键盘事件需要实现KeyListener
//为了让panel不停的重绘子弹需要将Mypanel实现Runnable，当做一个线程使用
public class MyPanel extends JPanel implements KeyListener, Runnable {
    //定义我的坦克
    MyTank myTank = null;
    //定义敌人坦克，放入到Vector
    Vector<EnemyTank> enemyTanks = new Vector<>();
    //定义一个Vector，用于存放炸弹
    //当子弹击中坦克时就加入一个boom对象到booms中
    Vector<Boom> booms = new Vector<>();
    int enemyTanksize = 8;

    //定义三张炸弹图片，用于显示爆炸效果
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;

    public MyPanel() {
        myTank = new MyTank(750, 100);//初始化自己的
        //初始化敌人的
        for (int i = 0; i < enemyTanksize; i++) {
            EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 0);
            enemyTank.setDirect(2);
            //启动敌方坦克线程，动起来
            new Thread(enemyTank).start();
            //给该enmeyTank加入一颗子弹
            Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY(), enemyTank.getDirect());
            //加入到enemyTank的Vector成员
            enemyTank.shots.add(shot);
            //启动shot对象
            new Thread(shot).start();
            enemyTanks.add(enemyTank);
        }
        //初始化图片对象
        image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/boom1.gif"));
        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/boom2.gif"));
        image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/boom3.gif"));

        myTank.setSpeed(4);//坦克的速度
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);
        //画出自己的坦克
        if (myTank!=null&&myTank.islive){
            drawTank(myTank.getX(), myTank.getY(), g, myTank.getDirect(), 0);
        }

        //画出射击的子弹

        //将mytank的子弹集合shots遍历取出
        for (int i = 0; i < myTank.shots.size(); i++) {
            Shot shot=myTank.shots.get(i);
            if (shot.isLive != false) {
                g.draw3DRect(shot.x, shot.y, 3, 3,false);
            }else {//对象已经销毁，从集合中拿掉
                myTank.shots.remove(shot);
            }
        }
        //如果booms集合中有炸弹对象
        for (int i = 0; i < booms.size(); i++) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //取出
            Boom boom = booms.get(i);
            //根据当前对象的life值去画出对应的图片
            if (boom.life > 6) {
                g.drawImage(image3, boom.x, boom.y, 60, 60, this);

            } else if (boom.life > 3) {
                g.drawImage(image2, boom.x, boom.y, 60, 60, this);

            } else {
                g.drawImage(image1, boom.x, boom.y, 60, 60, this);

            }
            //让炸弹的生命值减少
            boom.lifeDown();
            //如果boom life为零，就从booms的集合中删除
            if (boom.life == 0) {
                booms.remove(boom);
            }
        }
        //画出敌人的坦克，遍历Vector
        for (int i = 0; i < enemyTanks.size(); i++) {
            //取出坦克
            EnemyTank enemyTank = enemyTanks.get(i);
            //判断当前坦克是否还存活
            if (enemyTank.isLive) {
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 1);
                //画出敌人坦克的子弹
                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    //取出
                    Shot shot = enemyTank.shots.get(j);
                    //绘制
                    if (shot.isLive) {
                        g.draw3DRect(shot.x, shot.y, 3, 3, false);
                    } else {
                        //移除已经死了的子弹
                        enemyTank.shots.remove(shot);
                    }
                }
            }
        }
    }
    //编写方法画出坦克


    /**
     * @param x      x坐标
     * @param y      y坐标
     * @param g      画笔
     * @param direct 坦克方向
     * @param type   坦克类型
     */
    public void drawTank(int x, int y, Graphics g, int direct, int type) {
        //不同类型的坦克给不同的颜色
        switch (type) {
            case 0://自己的坦克
                g.setColor(Color.pink);
                break;
            case 1://敌人的坦克
                g.setColor(Color.orange);
                break;
        }
        //根据方向绘制坦克
        //根据坦克的方向绘制对应形状的坦克
        //0表示上，1表示向右，2表示向下，3表示向左
        switch (direct) {
            case 0:
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y, x + 20, y + 30);
                break;
            case 1:
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x + 60, y + 20);
                break;
            case 2:
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y + 60);
                break;
            case 3:
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x, y + 20);
                break;
            default:
                System.out.println("暂时没处理");
        }
    }
    //多颗子弹判断是需要把子弹集合中的所有子弹都取出与敌人的所有坦克进行判断
    public void hitEnemyTank(){
        for (int i = 0; i < myTank.shots.size(); i++) {
            Shot shot = myTank.shots.get(i);

            //判断是否击中敌人的坦克
            if (shot != null && shot.isLive) {//判断我坦克的子弹还存活
                //遍历所有敌人的坦克
                for (int j = 0; j < enemyTanks.size(); j++) {
                    EnemyTank enemyTank = enemyTanks.get(j);
                    hitTank(shot, enemyTank);
                }

            }
        }

    }
    //编写方法判断敌方坦克是否击中我方坦克
    public void hitMyTank(){
        for (int i = 0; i < enemyTanks.size(); i++) {
            //取出敌人坦克
            EnemyTank enemyTank = enemyTanks.get(i);
            //遍历enemyTank对象的所有子弹
            for (int j = 0; j < enemyTank.shots.size(); j++) {
                //取出子弹
                Shot shot = enemyTank.shots.get(j);
                //判断shot是否击中我方坦克
                if (myTank.islive&&shot.isLive){
                    hitTank(shot,myTank);
                }
            }
        }
    }
    //编写方法判断我方子弹是否击中敌人的坦克
    //放入run方法里判断
    public void hitTank(Shot s, Tank Tank) {
        //判断击中坦克
        switch (Tank.getDirect()) {
            case 0:
            case 2:
                if (s.x > Tank.getX() && s.x < Tank.getX() + 40
                        && s.y > Tank.getY() && s.y < Tank.getY() + 60) {
                    s.isLive = false;
                    Tank.islive = false;
                    enemyTanks.remove(Tank);
                    //创建一个boom对象，加入到booms集合中
                    Boom boom = new Boom(Tank.getX(), Tank.getY());
                    booms.add(boom);
                }
                break;
            case 1:
            case 3:
                if (s.x > Tank.getX() && s.x < Tank.getX() + 60
                        && s.y > Tank.getY() && s.y < Tank.getY() + 40) {
                    s.isLive = false;
                    Tank.islive = false;
                    enemyTanks.remove(Tank);
                    //创建一个boom对象，加入到booms集合中
                    Boom boom = new Boom(Tank.getX(), Tank.getY());
                    booms.add(boom);
                }
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //处理wasd按下的事件
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_S) {
            myTank.setDirect(2);
            if (myTank.getY() + 60 < 750) {
                myTank.moveDown();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            myTank.setDirect(0);
            if (myTank.getY() > 0) {
                myTank.moveUp();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            myTank.setDirect(3);
            if (myTank.getX() > 0) {
                myTank.moveLife();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            myTank.setDirect(1);
            if (myTank.getX() + 60 < 1000) {
                myTank.moveRight();
            }
        }
        //如果用户按得是j，就发射
        if (e.getKeyCode() == KeyEvent.VK_J) {
            //一颗子弹
//            if (myTank.shot == null || !myTank.shot.isLive) {
//                myTank.ShotEnemyTank();
//            }
            //多颗子弹
            myTank.ShotEnemyTank();
        }
        //让面板重绘
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {//每隔100毫秒重绘区域,子弹就会移动

        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            hitEnemyTank();
            hitMyTank();
            this.repaint();
        }
    }
}
