package MainPackage;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class SpecialObject extends GameObject{
    protected BufferedImage image;

    public SpecialObject(int x, int y, ID id, BufferedImage image) {
        super(x, y, id);
        this.image = image;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();
}
