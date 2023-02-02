package TankGame04;

/**
 * @author haohao
 * @version 1.0
 */
public class Tank {
    public boolean islive=true;
    private int x;
    private int y;
    private int direct;//坦克方向
    private int speed = 5;

    //移动方法
    public void moveUp() {
        y -= speed;
    }

    public void moveDown() {
        y += speed;
    }

    public void moveLife() {
        x -= speed;
    }

    public void moveRight() {
        x += speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
