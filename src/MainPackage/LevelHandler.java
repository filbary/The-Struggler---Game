package MainPackage;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class LevelHandler {
    private Handler handler;
    private LevelLoader levelLoader;
    private Shadow shadow;
    private UI ui;
    private Camera camera;
    private boolean drawImgScreen, drawImgLoadingScreen, drawImgEndingSreen;
    private BufferedImageLoader bufferedImageLoader;
    private BufferedImage img, deathImg;
    private String loadingScreenTxt, endingSreenTxt;
    boolean wait;
    long timer;
    public boolean startLauncher;
    private double bestTime;

    public LevelHandler(Handler handler, LevelLoader levelLoader, Shadow shadow, UI ui, Camera camera){
        this.handler = handler;
        this.levelLoader = levelLoader;
        this.shadow = shadow;
        this.ui = ui;
        this.camera = camera;
        this.drawImgScreen = false;
        this.drawImgEndingSreen = false;
        this.drawImgLoadingScreen = false;
        this.bufferedImageLoader = new BufferedImageLoader();
        this.deathImg = bufferedImageLoader.grabImage("/res/youdied.png");
        wait = false;
        timer = 0;
        this.startLauncher = false;
    }

    public void tick(){
        checkGameStatus();
        if (wait){
            try {
                TimeUnit.SECONDS.sleep(2);
                if (drawImgEndingSreen){
                    startLauncher = true;
                    drawImgEndingSreen = false;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            wait = false;
        }
    }

    public void render(Graphics g){
        if (drawImgScreen) {
            g.drawImage(deathImg, (int)camera.getX(), (int)camera.getY(), 1000, 563, null);
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            wait = true;
            drawImgScreen = false;
        }
        else if (drawImgLoadingScreen){
            g.setColor(Color.BLACK);
            g.fillRect((int)camera.getX(), (int)camera.getY(),1000,563);
            g.setColor(Color.WHITE);
            g.drawString(loadingScreenTxt, (int)camera.getX() + 1000/2 - 100, (int)camera.getY() + 563/2);
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            wait = true;
            drawImgLoadingScreen = false;
        }
        else if (drawImgEndingSreen){
            g.setColor(Color.BLACK);
            g.fillRect((int)camera.getX(), (int)camera.getY(),1000,563);
            g.setColor(Color.WHITE);
            g.drawString(endingSreenTxt, (int)camera.getX() + 1000/2 - 100, (int)camera.getY() + 563/2);
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            wait = true;
        }
    }


    private void checkGameStatus(){
        if (handler.player.getHealth() <= 0){
            loadLevel(handler.getCurrentLevel());
            drawImgScreen = true;
            img = deathImg;
        }
        else if (handler.player.getSouls() == 4 && handler.getCurrentLevel() == 1){
            handler.setCurrentLevel(handler.getCurrentLevel() + 1);
            loadLevel(handler.getCurrentLevel());
            drawImgLoadingScreen = true;
            loadingScreenTxt = "What took you so long?";
        }
        else if (handler.player.getSouls() == 4 && handler.getCurrentLevel() == 2){
            if (bestTime > handler.player.getTime()) {
                bestTime = handler.player.getTime();
                writeFile();
            }
            handler.setCurrentLevel(1);
            loadLevel(handler.getCurrentLevel());
            drawImgEndingSreen = true;
            endingSreenTxt = "It's only the beginning...";
        }
    }

    private void writeFile(){
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/TextFiles/SurvivorsDatabase.txt"));
            bufferedWriter.write(Double.toString(bestTime));
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadLevel(int levelNum){
        long tempTime = (long)(handler.player.getTime()*1000 - 2026);
        handler.clear();
        levelLoader.initLevel(levelNum);
        ui.changeFocus(handler.player, camera);
        handler.player.setTimeStart(System.currentTimeMillis() - tempTime);
        shadow.changeFocus(handler.player);
    }


    public double getBestTime() {
        return bestTime;
    }

    public void setBestTime(double bestTime) {
        this.bestTime = bestTime;
    }
}
