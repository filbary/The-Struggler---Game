package MainPackage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet extends GameObject{

    private Handler handler;
    private int mouseX, mouseY;
    BufferedImage image;

    public Bullet(int x, int y, ID id, BufferedImage image,  Handler handler, int mouseX, int mouseY) {
        super(x, y, id);
        this.handler = handler;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.image = image;
        setBulletVelocity();
    }
    private void setBulletVelocity(){
//        if (Math.sqrt((mouseX - x) * (mouseX - x) + (mouseY - y) * (mouseY - y)) > gun.range){
//            xPointer = (int)(Math.cos(Math.toRadians(rotationAngle)) * gun.range) + x;
//            yPointer = (int)(Math.sin(Math.toRadians(rotationAngle)) * gun.range) + y;
//        }
//        xVel = 10;
//        yVel = 10;
//        xVel = (int)(xVel * (mouseX - x)/Math.sqrt(Math.pow(x - mouseX, 2) + Math.pow(y - mouseY, 2)));
//        yVel = (int)(yVel * (mouseY - y)/Math.sqrt(Math.pow(x - mouseX, 2) + Math.pow(y - mouseY, 2)));

        int deltaX = mouseX - x;
        int deltaY = mouseY - y;
        double angle = Math.atan2(deltaY, deltaX);
        xVel = (int)(Math.cos(angle) * 20);
        yVel = (int)(Math.sin(angle) * 20);
    }
    private void collision(){
        for (int i=0;i<handler.blocks.size();i++){
            Block block = handler.blocks.get(i);
            if (block.getBounds().intersects(this.getBounds())){
                handler.bullets.remove(this);
                break;
            }

        }
    }

    @Override
    public void tick() {
        x += xVel;
        y += yVel;
        collision();
    }


    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x - 4, y - 4, 8, 8);
        g.drawImage(image, x - 4, y - 4, 8, 8, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x - 4, y - 4, 8, 8);
    }
}
