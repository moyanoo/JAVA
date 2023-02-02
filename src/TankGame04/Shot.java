package TankGame04;

/**
 * @author haohao
 * @version 1.0
 * 射击子弹
 */
public class Shot implements Runnable {
    int x;//子弹x
    int y;//子弹y
    int direct = 0;//方向
    int speed = 6;//速度
    boolean isLive = true;//子弹是否存活

    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;

    }

    @Override
    public void run() {
        while (true) {
            //休眠
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //根据方向改变xy坐标
            switch (direct) {
                case 0://上
                    y -= speed;
                    break;
                case 1://右
                    x += speed;
                    break;
                case 2://下
                    y += speed;
                    break;
                case 3://左
                    x -= speed;
                    break;
            }
            System.out.println("坐标:" + x + "," + y);
            //当子弹碰到画板边界时就应该销毁
            //当子弹碰到敌人坦克时也应结束线程
            if (!(x >= 0 && x <= 1000 && y >= 0 && y <= 750&&isLive)) {
                isLive = false;
                break;
            }

        }
    }
}
