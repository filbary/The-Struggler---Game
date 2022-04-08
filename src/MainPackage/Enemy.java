package MainPackage;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Enemy extends GameObject{

    protected double health;
    protected double damage;
    protected double attackSpeed;
    protected int attackRange;
    protected Handler handler;
    protected BufferedImage sleepImg, actionImg;
    protected BufferedImage currentImage;
    protected boolean dropsDemonSoul;

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();


    public Enemy(int x, int y, ID id, BufferedImage sleepImg, BufferedImage actionImg, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        this.sleepImg = sleepImg;
        this.actionImg = actionImg;
        this.currentImage = sleepImg;
    }

    protected void checkIfHit(){
        for (int i=0;i<handler.bullets.size();i++){
            Bullet bullet = handler.bullets.get(i);
            if (bullet.getBounds().intersects(this.getBounds())){
                handler.bullets.remove(bullet);
                this.health -= handler.player.getDamage();
                if (health <= 0){
                    if (dropsDemonSoul)
                        handler.player.setDemonSouls(handler.player.getDemonSouls() + 2);
                    handler.enemies.remove(this);
                    int addHealth = Math.min((int)(handler.player.getMaxHealth() - handler.player.getHealth()), 20);
                    handler.player.setHealth(handler.player.getHealth() + addHealth);
                }
            }
        }
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(double attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public int getAttackRange() {
        return attackRange;
    }

    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
    }
}
