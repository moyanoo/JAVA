package event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author haohao
 * @version 1.0
 * 演示小球通过键盘控制上下左右移动
 */
public class BallMove extends JFrame{
    MyPanel01 mp=null;
    public static void main(String[] args) {
        BallMove ballMove = new BallMove();
    }
    //构造器
    public BallMove(){
        mp=new MyPanel01();
        this.add(mp);
        this.setSize(400,400);
        //窗口JFrame，对象可以监听键盘事件，即可以监听面板上发生的事件
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
//面板，画出小球
//KeyListener是监听器，可以监听键盘的事件
class MyPanel01 extends JPanel implements KeyListener {
    //为了让小球可以移动，把他的右上角坐标设置为变量
    int x=10;
    int y=10;
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillOval(x,y,20,20);
    }
    //有字符输出时该方法就会触发
    @Override
    public void keyTyped(KeyEvent e) {

    }
    //当某个建按下，该方法会被触发
    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println((char) e.getKeyCode()+"被触发");

        //根据用户按下的不同建，来处理小球的移动（上下左右）
        if(e.getKeyCode()==KeyEvent.VK_S){
            y+=3;
        } else if (e.getKeyCode()==KeyEvent.VK_W) {
            y-=3;
        } else if (e.getKeyCode()==KeyEvent.VK_A) {
            x-=3;
        } else if (e.getKeyCode()==KeyEvent.VK_D) {
            x+=3;
        }
        //让面板重绘
        this.repaint();
    }
    //当某个建松开了，该方法会触发
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
