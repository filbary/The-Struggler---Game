package MainPackage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Door extends Block{

    private Handler handler;
    private boolean isDemonic;
    private BufferedImage image;

    public Door(int x, int y, ID id, BufferedImage image, Handler handler, boolean isDemonic) {
        super(x, y, id, image);
        this.handler = handler;
        this.isDemonic = isDemonic;
        this.image = image;
    }

    @Override
    public void tick() {
        collision();
    }

    private void collision(){
        if (handler.player.getBounds().intersects(this.BiggerBounds())){
            if (isDemonic && handler.player.getDemonSouls() > 0){
                handler.player.setDemonSouls(handler.player.getDemonSouls() - 1);
                handler.blocks.remove(this);
            }
            else if (!isDemonic && handler.player.getKeys() > 0) {
                handler.player.setKeys(handler.player.getKeys() - 1);
                handler.blocks.remove(this);
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, x,y, 32,32,null);
    }


    public Rectangle BiggerBounds(){
        return new Rectangle(x - 16, y - 16, 64, 64);
    }
}
