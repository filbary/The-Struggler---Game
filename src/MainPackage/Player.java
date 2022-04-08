package MainPackage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends GameObject{

    Handler handler;
    private double health;
    private double maxHealth;
    private double damage;
    private int ammo;
    private int souls;
    private int demonSouls;
    private int keys;
    private long lastTimeShot;
    BufferedImage frontImg, backimg, leftImg, rightImg, image;
    private double time;
    private long timeStart;

    public Player(int x, int y, ID id, BufferedImage frontImg, BufferedImage backimg,
                  BufferedImage leftImg, BufferedImage rightImg, Handler handler){
        super(x, y, id);
        this.handler = handler;
        this.maxHealth = 200;
        this.health = maxHealth;
        this.damage = 10;
        this.ammo = 0;
        this.souls = 0;
        this.demonSouls = 0;
        this.keys = 0;
        this.lastTimeShot = 0;
        this.frontImg = frontImg;
        this.backimg = backimg;
        this.leftImg = leftImg;
        this.rightImg = rightImg;
        this.image = frontImg;
        this.time = 0;
        this.timeStart = 0;
    }

    @Override
    public void tick() {
        x += xVel;
        y += yVel;

        time = (double)(System.currentTimeMillis() - timeStart)/1000;
        collision();
        chooseImg();

        if (handler.isUp()) setyVel(-5);
        else if (!handler.isDown()) setyVel(0);

        if (handler.isDown()) setyVel(5);
        else if (!handler.isUp()) setyVel(0);

        if (handler.isLeft()) setxVel(-5);
        else if (!handler.isRight()) setxVel(0);

        if (handler.isRight()) setxVel(5);
        else if (!handler.isLeft()) setxVel(0);
    }

    private void chooseImg(){
        if (handler.isUp())
            image = backimg;
        else if (handler.isDown())
            image = frontImg;
        else if (handler.isLeft())
            image = leftImg;
        else if (handler.isRight())
            image = rightImg;
    }

    private void collision(){
        for (int i=0;i<handler.blocks.size();i++){
            if (handler.blocks.get(i).getBounds().intersects(this.getBounds())){
                x += -xVel;
                y += -yVel;
            }
        }
    }


    public boolean canShoot(){
        if (System.currentTimeMillis() - lastTimeShot > 150){
            lastTimeShot = System.currentTimeMillis();
            return true;
        }
        else
            return false;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, x - 16, y - 16, 32, 32, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x - 16, y - 16, 32, 32);
    }

    public Rectangle getVisibilityBounds(){
        return new Rectangle(x - 200, y - 200, 400, 400);
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }

    public double getDamage(){
        return damage;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public int getSouls() {
        return souls;
    }

    public void setSouls(int souls) {
        this.souls = souls;
    }

    public int getKeys() {
        return keys;
    }

    public void setKeys(int keys) {
        this.keys = keys;
    }

    public int getDemonSouls() {
        return demonSouls;
    }

    public void setDemonSouls(int demonSouls) {
        this.demonSouls = demonSouls;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public long getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(long timeStart) {
        this.timeStart = timeStart;
    }
}
