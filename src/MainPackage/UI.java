package MainPackage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    private Player player;
    private Camera camera;
    private BufferedImageLoader bufferedImageLoader;
    private BufferedImage uiBarImg, num0, num1, num2, num3, num4, num5, num6, num7, num8, num9;
    private BufferedImage ammoImg1num, ammoImg2num;
    private BufferedImage soulsImgnum;


    public UI(Player player, Camera camera){
        this.player = player;
        this.camera = camera;
        bufferedImageLoader = new BufferedImageLoader();
        loadImages();
    }

    public void loadImages(){
        this.uiBarImg = bufferedImageLoader.grabImage("/res/uiBar.png");
        this.num0 = bufferedImageLoader.grabImage("/res/0_img.png");
        this.num1 = bufferedImageLoader.grabImage("/res/1_img.png");
        this.num2 = bufferedImageLoader.grabImage("/res/2_img.png");
        this.num3 = bufferedImageLoader.grabImage("/res/3_img.png");
        this.num4 = bufferedImageLoader.grabImage("/res/4_img.png");
        this.num5 = bufferedImageLoader.grabImage("/res/5_img.png");
        this.num6 = bufferedImageLoader.grabImage("/res/6_img.png");
        this.num7 = bufferedImageLoader.grabImage("/res/7_img.png");
        this.num8 = bufferedImageLoader.grabImage("/res/8_img.png");
        this.num9 = bufferedImageLoader.grabImage("/res/9_img.png");
        this.ammoImg1num = num0;
        this.ammoImg2num = num0;
        this.soulsImgnum = num0;
    }

    public void tick(){
        updateAmmoImage(player.getAmmo());
        updateSoulsImage(player.getSouls());
    }

    public void render(Graphics g){
        g.setColor(new Color(255, 0,0));
        g.fillRect((int)camera.getX() + 64, (int)camera.getY() + 10, (int)(player.getHealth()/ player.getMaxHealth() * 188), 50);
        g.drawImage(uiBarImg, (int)camera.getX(), (int)camera.getY(),1000, 72,null);
        g.drawImage(soulsImgnum, (int)camera.getX() + 556, (int)camera.getY() + 20, 24, 40, null);
        g.drawImage(ammoImg1num, (int)camera.getX() + 930, (int)camera.getY() + 20, 24, 40, null);
        g.drawImage(ammoImg2num, (int)camera.getX() + 954, (int)camera.getY() + 20, 24, 40, null);
        g.setColor(Color.WHITE);
        g.setFont(g.getFont().deriveFont(Font.BOLD, 16F));
        g.drawString(Double.toString(player.getTime()), (int)camera.getX() + 910, (int)camera.getY() + 90);
    }
    private void updateAmmoImage(int currentAmmo){
            ammoImg1num = chooseNumImg((currentAmmo - currentAmmo%10)/10);
            ammoImg2num = chooseNumImg(currentAmmo%10);
    }
    private void updateSoulsImage(int currentSouls){
        soulsImgnum = chooseNumImg(currentSouls);
    }
    private BufferedImage chooseNumImg(int num){
        if (num == 0)
            return num0;
        else if (num == 1)
            return num1;
        else if (num == 2)
            return num2;
        else if (num == 3)
            return num3;
        else if (num == 4)
            return num4;
        else if (num == 5)
            return num5;
        else if (num == 6)
            return num6;
        else if (num == 7)
            return num7;
        else if (num == 8)
            return num8;
        else
            return num9;
    }

    public void changeFocus(Player player, Camera camera){
        this.player = player;
        this.camera = camera;
    }

}
