package MainPackage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Key extends SpecialObject{
    private Handler handler;
    public Key(int x, int y, ID id, BufferedImage image, Handler handler) {
        super(x, y, id, image);
        this.handler = handler;
    }

    @Override
    public void tick() {
        if (handler.player.getBounds().intersects(this.getBounds())){
            handler.player.setKeys(handler.player.getKeys() + 1);
            handler.specialObjects.remove(this);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, x, y + 8, 16, 32, null);
//        g.fillRect(x + 8, y, 16,32);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x + 8, y, 16, 32);
    }
}
