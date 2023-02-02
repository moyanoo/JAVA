package TankGame03;

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
public class MyPanel extends JPanel implements KeyListener,Runnable {
    //定义我的坦克
    MyTank myTank = null;
    //定义敌人坦克，放入到Vector
    Vector<EnemyTank> enemyTanks = new Vector<>();
    int enemyTanksize = 3;

    public MyPanel() {
        myTank = new MyTank(100, 100);//初始化自己的
        //初始化敌人的
        for (int i = 0; i < enemyTanksize; i++) {
            EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 0);
            enemyTank.setDirect(2);
            enemyTanks.add(enemyTank);
        }

        myTank.setSpeed(4);//坦克的速度
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);
        //画出自己的坦克
        drawTank(myTank.getX(), myTank.getY(), g, myTank.getDirect(), 0);
        //画出射击的子弹
        if (myTank.shot != null && myTank.shot.isLive != false) {
            g.draw3DRect(myTank.shot.x,myTank.shot.y,3,3,false);
        }
        //画出敌人的坦克，遍历Vector
        for (int i = 0; i < enemyTanks.size(); i++) {
            //取出坦克
            EnemyTank enemyTank = enemyTanks.get(i);
            drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 1);
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
                g.setColor(Color.cyan);
                break;
            case 1://敌人的坦克
                g.setColor(Color.yellow);
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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //处理wasd按下的事件
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_S) {
            myTank.setDirect(2);
            myTank.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            myTank.setDirect(0);
            myTank.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            myTank.setDirect(3);
            myTank.moveLife();
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            myTank.setDirect(1);
            myTank.moveRight();
        }
        //如果用户按得是j，就发射
        if (e.getKeyCode() == KeyEvent.VK_J) {
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
            this.repaint();
        }
    }
}
