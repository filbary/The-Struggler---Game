package MainPackage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Shadow {
    private int x;
    private int y;
    private Player player;
    BufferedImage image;
    public Shadow(Player player){
        this.player = player;
        this.image = new BufferedImageLoader().grabImage("/res/shadow.png");
    }
    public void render(Graphics g){
        g.drawImage(image, (int)player.getX() - 1000/2, (int)player.getY() - 563/2, 1000, 563, null);
    }
    public void changeFocus(Player player){
        this.player = player;
    }
}
