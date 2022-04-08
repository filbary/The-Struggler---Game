package MainPackage;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {
    private Handler handler;
    private Camera camera;
    private UI ui;
    private LevelLoader levelLoader;

    private int mouseX, mouseY;
    public MouseHandler(Handler handler, Camera camera, UI ui, LevelLoader levelLoader){
        this.handler = handler;
        this.camera = camera;
        this.ui = ui;
        this.levelLoader = levelLoader;
    }
    public void mousePressed(MouseEvent e) {
        mouseX = (e.getX() + (int)camera.getX());
        mouseY = (e.getY() + (int)camera.getY());

        if (handler.player.getAmmo() > 0 && handler.player.canShoot()) {
            handler.addObject(new Bullet(handler.player.x,
                    handler.player.y, ID.Bullet, levelLoader.bulletImg, handler, mouseX, mouseY));
            handler.player.setAmmo(handler.player.getAmmo() - 1);
        }
    }
}
