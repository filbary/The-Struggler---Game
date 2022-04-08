package MainPackage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AmmoBox extends SpecialObject{

    private Handler handler;
    private int ammo;

    public AmmoBox(int x, int y, ID id, BufferedImage image, Handler handler) {
        super(x, y, id, image);
        this.handler = handler;
        this.ammo = 9;
    }

    @Override
    public void tick() {
        collision();
    }
    private void collision(){
        if (handler.player.getBounds().intersects(this.getBounds())){
            handler.player.setAmmo(handler.player.getAmmo() + ammo);
            handler.specialObjects.remove(this);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, x,y + 16, 32,16, null);
//        g.fillRect(x, y + 16, 32, 16);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y + 16, 32, 16);
    }
}
