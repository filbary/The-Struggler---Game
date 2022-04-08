package MainPackage;

import java.awt.image.BufferedImage;

public class LevelLoader {
    Handler handler;
    BufferedImage image, toucherClosedImg, toucherOpenedImg, chargerSleepImg, chargerAngryImg, chargerFuriousImg,
            stoneBlockImg, doorImg, demonicDoorImg, ammoBoxImg, soulImg, keyImg, playerFrontImg, playerBackImg,
            playerLeftImg, playerRightImg, bulletImg;
    BufferedImageLoader bufferedImageLoader;
    public LevelLoader(Handler handler){
        this.handler = handler;
        this.bufferedImageLoader = new BufferedImageLoader();
        loadImages();
    }
    private void grabArea(int areaNr){
        if (areaNr == 1){
            image = bufferedImageLoader.grabImage("/res/map.png");
        }
        else if (areaNr == 2){
            image = bufferedImageLoader.grabImage("/res/mapMansion.png");
        }
        else return;
    }
    private void loadImages(){
        toucherClosedImg = bufferedImageLoader.grabImage("/res/toucherClosed.png");
        toucherOpenedImg = bufferedImageLoader.grabImage("/res/toucherOpened.png");
        chargerSleepImg = bufferedImageLoader.grabImage("/res/chargerCalm.png");
        chargerAngryImg = bufferedImageLoader.grabImage("/res/chargerAngry.png");
        chargerFuriousImg = bufferedImageLoader.grabImage("/res/chargerFurious.png");
        stoneBlockImg = bufferedImageLoader.grabImage("/res/stoneBlock.png");
        doorImg = bufferedImageLoader.grabImage("/res/door.png");
        ammoBoxImg = bufferedImageLoader.grabImage("/res/ammoBox.png");
        soulImg = bufferedImageLoader.grabImage("/res/soul.png");
        keyImg = bufferedImageLoader.grabImage("/res/key.png");
        playerFrontImg = bufferedImageLoader.grabImage("/res/playerFrontImg.png");
        playerBackImg = bufferedImageLoader.grabImage("/res/playerBack.png");
        playerLeftImg = bufferedImageLoader.grabImage("/res/playerLeft.png");
        playerRightImg = bufferedImageLoader.grabImage("/res/playerRight.png");
        demonicDoorImg = bufferedImageLoader.grabImage("/res/demonDoor.png");
        bulletImg = bufferedImageLoader.grabImage("/res/bullet.png");

    }
    public void initLevel(int areaNr){
        grabArea(areaNr);
        System.out.println("asidnaismda");
        int widthImg = image.getWidth();
        int heightImg = image.getHeight();
        for (int i=0; i<widthImg; i++){
            for (int j=0; j<heightImg; j++){
                int pix = image.getRGB(i, j);
                int red = (pix>>16) & 0xff;
                int green = (pix>>8) & 0xff;
                int blue = (pix) & 0xff;

                if (red == 255 && green == 255 && blue == 255){
                    handler.addObject(new Block(i*32, j*32 + 72, ID.Block, stoneBlockImg));
                }
                else if (red == 0 && green == 0 && blue == 255){
                    handler.addObject(new Player(i*32, j*32 + 72, ID.Player, playerFrontImg, playerBackImg,
                                                playerLeftImg, playerRightImg, handler));
                }
                else if (red == 0 && green == 255 && blue == 0){
                    handler.addObject(new Toucher(i*32, j*32 + 72, ID.Enemy, toucherClosedImg, toucherOpenedImg, handler));
                }
                else if (red == 91 && green == 9 && blue == 9){
                    handler.addObject(new Charger(i*32, j*32 + 72, ID.Enemy, chargerSleepImg, chargerAngryImg, chargerFuriousImg, handler));
                }
                else if (red == 255 && green == 0 && blue == 0){
                    handler.addObject(new AmmoBox(i*32, j*32 + 72, ID.AmmoBox, ammoBoxImg, handler));
                }
                else if (red == 255 && green == 255 && blue == 0){
                    handler.addObject(new Soul(i*32, j*32 + 72, ID.Soul, soulImg, handler));
                }
                else if (red == 0 && green == 255 && blue == 255){
                    handler.addObject(new Door(i*32, j*32 + 72, ID.Door, doorImg, handler, false));
                }
                else if (red == 0 && green == 100 && blue == 255){
                    handler.addObject(new Door(i*32, j*32 + 72, ID.Door, demonicDoorImg, handler, true));
                }
                else if (red == 255 && green == 0 && blue == 255){
                    handler.addObject(new Key(i*32, j*32 + 72, ID.Key, keyImg, handler));
                }
            }
        }
    }

}
