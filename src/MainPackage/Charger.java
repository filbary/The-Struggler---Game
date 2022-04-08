package MainPackage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Charger extends Enemy{
    double maxHealth;
    boolean playerSeen, charging, hunting;
    Random random;
    int wait;
    double rotationAngle;
    BufferedImage chargingImg, image;
    long timer;
    public Charger(int x, int y, ID id, BufferedImage sleepImg, BufferedImage actionImg, BufferedImage chargingImg, Handler handler) {
        super(x, y, id, sleepImg, actionImg, handler);
        this.playerSeen = false;
        this.charging = false;
        this.hunting = false;
        this.health = 200;
        this.random = new Random();
        this.chargingImg = chargingImg;
        this.image = sleepImg;
        this.rotationAngle = 0;
        this.maxHealth = health;
        this.dropsDemonSoul = true;
    }

    @Override
    public void tick() {
        rotationAngle = Math.toDegrees(Math.atan2(yVel, xVel));
        if (hunting) {
            x += xVel;
            y += yVel;
            collision();
            moveIfNotCharging();
            charge();
            dealDamage();
            checkIfHit();
        }
        else {
            waitForPrey();
        }
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.rotate(Math.toRadians(rotationAngle + 90), x, y);
        g.drawImage(image, x - 24, y - 24, 64, 64, null);
        g.setColor(new Color(91, 9, 9));
        g2.rotate(Math.toRadians(- (rotationAngle + 90)), x, y);
//        g.drawOval(x - 160, y - 160, 320, 320);
    }

    private void waitForPrey(){
        if (handler.player.getBounds().intersects(this.getVisibilityBounds()) && !playerSeen){
            timer = System.currentTimeMillis();
            playerSeen = true;
        }
        else if (playerSeen && System.currentTimeMillis() - timer > 2000 && System.currentTimeMillis() - timer < 3000){
            image = actionImg;
            health = maxHealth;
        }
        else if (playerSeen && System.currentTimeMillis() - timer > 3000){
            image = chargingImg;
            health = maxHealth;
            hunting = true;
            timer = System.currentTimeMillis();
        }
    }

    private void moveIfNotCharging(){
        if (!charging){
            image = actionImg;
            wait = random.nextInt(20);
            if (wait == 0){
                xVel = random.nextInt(8) - 4;
                yVel = random.nextInt(8) - 4;
            }
        }
    }

    private boolean checkIfCanCharge(){
        if (handler.player.getBounds().intersects(this.getVisibilityBounds())){
            return true;
        }
        else
            return false;
    }

    private void charge(){
        if (checkIfCanCharge() && !charging){ // && !charging
            image = chargingImg;
            double distance = Math.abs(Math.sqrt(Math.pow((x  - handler.player.getX()), 2)
                    + Math.pow((y  - handler.player.getY()), 2)));
            if(distance != 0) {
                xVel = (handler.player.getX() - x) / distance * 7;
                yVel = (handler.player.getY() - y) / distance * 7;
            }
            else{
                xVel = 0;
                yVel = 0;
            }
            charging = true;
        }
    }

    private void dealDamage(){
        if (handler.player.getBounds().intersects(this.getBounds())){
            handler.player.setHealth(handler.player.getHealth() - 3);
            if (System.currentTimeMillis() - timer < 400){
                image = actionImg;
            }
            else if (System.currentTimeMillis() - timer > 400 && System.currentTimeMillis() - timer < 800){
                image = chargingImg;
            }
            else {
                image = actionImg;
                timer = System.currentTimeMillis();
            }
        }
    }

    private void collision(){
        for (int i=0;i<handler.blocks.size();i++){

            if (this.getBiggerBounds().intersects(handler.blocks.get(i).getBounds())){
                x += -6 * xVel;
                y += -6 * yVel;
                xVel = 0;
                yVel = 0;
                charging = false;
            }

        }
    }


    @Override
    public Rectangle getBounds() {
        return new Rectangle(x - 24, y - 24, 48, 48);
    }

    private Rectangle getBiggerBounds(){
        return new Rectangle(x - 32, y - 32, 64, 64);
    }

    private Rectangle getVisibilityBounds(){
        return new Rectangle(x - 128, y - 128, 256, 256);
    }
}
