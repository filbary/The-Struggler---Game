package MainPackage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Block extends GameObject{
    BufferedImage image;

    public Block(int x, int y, ID id, BufferedImage image) {
        super(x, y, id);
        this.image = image;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x,y,32,32);
        g.drawImage(image, x,y,32,32,null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y,32,32);
    }
}
