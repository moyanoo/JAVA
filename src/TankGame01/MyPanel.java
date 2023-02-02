package TankGame01;

import javax.swing.*;
import java.awt.*;

/**
 * @author haohao
 * @version 1.0
 * 绘图区域
 */
public class MyPanel extends JPanel {
    //定义我的坦克
    MyTank myTank=null;
    public MyPanel(){
        myTank=new MyTank(100,100);//初始化
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,1000,750);
        drawTank(MyTank.getX(),MyTank.getY(),g,0,0);
    }
    //编写方法画出坦克


    /**
     *
     * @param x  x坐标
     * @param y  y坐标
     * @param g  画笔
     * @param direct   坦克方向
     * @param type     坦克类型
     */
    public void drawTank(int x,int y,Graphics g,int direct,int type){
        //不同类型的坦克给不同的颜色
        switch(type){
            case 0://自己的坦克
                g.setColor(Color.cyan);
                break;
            case 1://敌人的坦克
                g.setColor(Color.yellow);
                break;
        }
        //根据方向绘制坦克
        switch(direct){
            case 0:
                g.fill3DRect(x,y,10,60,false);
                g.fill3DRect(x+30,y,10,60,false);
                g.fill3DRect(x+10,y+10,20,40,false);
                g.fillOval(x+10,y+20,20,20);
                g.drawLine(x+20,y,x+20,y+30);

                break;
            default:
                System.out.println("暂时没处理");
        }
    }
}
