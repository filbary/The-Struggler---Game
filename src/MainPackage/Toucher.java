package MainPackage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Toucher extends Enemy{

    private Random random;
    private int wait;

    public Toucher(int x, int y, ID id, BufferedImage sleepImg, BufferedImage actionImg, Handler handler) {
        super(x, y, id,sleepImg, actionImg, handler);
        this.xVel = 0;
        this.yVel = 0;
        this.random = new Random();
        this.wait = 0;
        this.health = 30;
        this.damage = 2;
        this.dropsDemonSoul = false;
    }

    @Override
    public void tick() {
        x += xVel;
        y += yVel;
        collision();
        randomizeVelocity();
        dealDamage();
        checkIfHit();
    }


    @Override
    public void render(Graphics g) {
        g.drawImage(currentImage, x,y,48,48,null);
    }


    private void randomizeVelocity(){
        wait = random.nextInt(50);
        if (wait == 0){
            xVel = random.nextInt(8) - 4;
            yVel = random.nextInt(8) - 4;
        }
    }

    private void collision(){
        for (int i=0;i<handler.blocks.size();i++){

            if (this.getBiggerBounds().intersects(handler.blocks.get(i).getBounds())){
                x += -5 * xVel;
                y += -5 * yVel;
                xVel *= -1;
                yVel *= -1;
            }

        }
    }

    private void dealDamage(){
        if (handler.player.getBounds().intersects(this.getVisibilityBounds())){
            currentImage = actionImg;
        }
        else {
            currentImage = sleepImg;
        }
        if (handler.player.getBounds().intersects(this.getBounds())){
            handler.player.setHealth(handler.player.getHealth() - this.damage);
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 48, 48);
    }
    public Rectangle getBiggerBounds(){
        return new Rectangle(x - 16, y - 16, 64, 64);
    }
    private Rectangle getVisibilityBounds(){
        return new Rectangle(x - 24,y - 24,96,96);
    }
}
