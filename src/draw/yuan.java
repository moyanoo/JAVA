package draw;



import javax.swing.*;
import java.awt.*;

/**
 * @author haohao
 * @version 1.0
 * 演示如何画圆
 */
public class yuan extends JFrame{
    private MyPanel mp=null;
    public static void main(String[] args) {
        new yuan();
    }
    public yuan(){
        mp=new MyPanel();
        this.add(mp);
        this.setSize(1000,1000);
        //完全退出程序
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
//定义一个画板类MyPanel继承JPanel画图在面板
class MyPanel extends JPanel{
    //MyPanel对象是一个画板
    //Graphics g 把g理解为一支画笔
    //Graphics提供了很多的绘图方法
    @Override
    public void paint(Graphics g) {
        super.paint(g);//调用父类方法完成初始化
//        g.drawOval(0,0,500,500);
//        //绘制不同的图形
//        //直线
//        g.drawLine(0,0,100,100);
//        //矩形边框
//        g.drawRect(0,0,500,500);
//        //填充矩形
//        g.setColor(Color.cyan);
//        g.fillRect(10,10,100,100);
//        //填充椭圆
//        g.setColor(Color.green);
//        g.fillOval(10,10,100,200);
        //画图片
        //获取图片资源
//        Image image=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/ap.jpg"));
//        g.drawImage(image,0,0,this);
        //给画笔设置颜色和字体
//        g.setColor(Color.red);
//        g.setFont(new Font("隶书",Font.BOLD,50));
//        g.drawString("哦哦哦",300,300);
        g.drawRect(100,100,15,50);
        //填充矩形
        g.setColor(Color.cyan);
        g.fillRect(100,100,15,50);

        g.drawRect(115,110,25,30);
        //填充矩形
        g.setColor(Color.cyan);
        g.fillRect(115,110,25,30);
    }
}