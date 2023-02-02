package TankGame04;

/**
 * @author haohao
 * @version 1.0
 * 炸弹类
 */
public class Boom {
    int x,y;//炸弹坐标
    int life=9;//生命周期
    boolean isLive=true;

    public Boom(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //减少生命值
    public void lifeDown(){
        if (life>0){
            life--;
        }else {
            isLive=false;
        }
    }
}
