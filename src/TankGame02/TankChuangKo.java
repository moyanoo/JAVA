package TankGame02;

import javax.swing.*;

/**
 * @author haohao
 * @version 1.0
 */
public class TankChuangKo extends JFrame {
    //定义MyPanel
    MyPanel mp=null;
    public static void main(String[] args) {
        TankChuangKo tankChuangKo = new TankChuangKo();

    }
    public TankChuangKo(){
        mp=new MyPanel();
        this.add(mp);
        this.setSize(1000,750);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
