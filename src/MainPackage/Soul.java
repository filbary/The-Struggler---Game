package MainPackage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Soul extends SpecialObject{
    private Handler handler;
    public Soul(int x, int y, ID id, BufferedImage image, Handler handler) {
        super(x, y, id, image);
        this.handler = handler;
    }

    @Override
    public void tick() {
        collision();

    }

    private void collision(){
        if (handler.player.getBounds().intersects(this.getBounds())){
            handler.specialObjects.remove(this);
            handler.player.setSouls(handler.player.getSouls() + 1);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, x, y, 32,32,null);
//        g.fillRect(x + 16, y + 16, 16, 16);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }
}
