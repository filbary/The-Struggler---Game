package MainPackage;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LauncherKeyHandler extends KeyAdapter {
    Launcher launcher;


    public LauncherKeyHandler(Launcher launcher){
        this.launcher = launcher;
    }

    public void keyPressed(KeyEvent e){
        if (launcher.isOpened()){
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_W) launcher.upPressed = true;
            else if (key == KeyEvent.VK_S) launcher.downPressed = true;
            else if (key == KeyEvent.VK_ENTER) launcher.enterPressed = true;
        }
    }

    public void keyReleased(KeyEvent e){
        if (launcher.isOpened()){
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_W && launcher.upPressed) {
                launcher.setUp(true);
                launcher.upPressed = false;
            }
            else if (key == KeyEvent.VK_S && launcher.downPressed) {
                launcher.setDown(true);
                launcher.downPressed = false;
            }
        }
    }

}
