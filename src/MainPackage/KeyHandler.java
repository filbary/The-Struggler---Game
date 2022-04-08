package MainPackage;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyHandler extends KeyAdapter {
    Handler handler;

    public KeyHandler(Handler handler){
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) handler.setUp(true);
        else if (key == KeyEvent.VK_S) handler.setDown(true);
        else if (key == KeyEvent.VK_A) handler.setLeft(true);
        else if (key == KeyEvent.VK_D) handler.setRight(true);
    }
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) handler.setUp(false);
        else if (key == KeyEvent.VK_S) handler.setDown(false);
        else if (key == KeyEvent.VK_A) handler.setLeft(false);
        else if (key == KeyEvent.VK_D) handler.setRight(false);
    }
}
