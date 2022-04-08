package MainPackage;

import java.awt.*;

public abstract class GameObject {

    protected int x;
    protected int y;
    protected double xVel = 0;
    protected double yVel = 0;
    protected ID id;

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    public GameObject(int x, int y, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
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

    public double getxVel() {
        return xVel;
    }

    public void setxVel(double xVel) {
        this.xVel = xVel;
    }

    public double getyVel() {
        return yVel;
    }

    public void setyVel(double yVel) {
        this.yVel = yVel;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}
