package MainPackage;

import java.awt.*;
import java.util.LinkedList;

public class Handler {
    LinkedList<Block> blocks = new LinkedList<>();
    LinkedList<Bullet> bullets = new LinkedList<>();
    LinkedList<Enemy> enemies = new LinkedList<>();
    LinkedList<SpecialObject> specialObjects = new LinkedList<>();
    Player player;


    private int currentLevel = 1;



    private boolean isUp = false, isDown = false, isLeft = false, isRight = false;

    public void tick(){
        for (int i=0;i<blocks.size();i++){
            if(blocks.get(i).getId() == ID.Door) {
                blocks.get(i).tick();
            }
        }
        player.tick();
        for (int i=0;i<bullets.size();i++){
            bullets.get(i).tick();
        }
        for (int i=0;i<enemies.size();i++){
            enemies.get(i).tick();
        }
        for (int i=0;i<specialObjects.size();i++){
            specialObjects.get(i).tick();
        }
    }
    public void render(Graphics g){
        for (int i=0;i<blocks.size();i++){
            if (player.getVisibilityBounds().contains(blocks.get(i).getBounds()))
                blocks.get(i).render(g);
        }
        for (int i=0;i<specialObjects.size();i++){
            if (player.getVisibilityBounds().contains(specialObjects.get(i).getBounds()))
                specialObjects.get(i).render(g);
        }
        for (int i=0;i<bullets.size();i++){
            if (player.getVisibilityBounds().contains(bullets.get(i).getBounds()))
                bullets.get(i).render(g);
        }
        player.render(g);
        for (int i=0;i<enemies.size();i++){
            if (player.getVisibilityBounds().contains(enemies.get(i).getBounds()))
                enemies.get(i).render(g);
        }
    }
    public void addObject(Block block){
        blocks.add(block);
    }
    public void addObject(Bullet bullet){
        bullets.add(bullet);
    }
    public void addObject(Enemy enemy) {
        enemies.add(enemy);
    }
    public void addObject(Player player){
        this.player = player;
    }
    public void addObject(SpecialObject specialObject){
        this.specialObjects.add(specialObject);
    }

    public void clear(){
        blocks = new LinkedList<>();
        bullets = new LinkedList<>();
        enemies = new LinkedList<>();
        specialObjects = new LinkedList<>();
        player = null;
    }

    public void startTimer(){
        player.setTimeStart(System.currentTimeMillis());
    }


    public boolean isUp() {
        return isUp;
    }

    public void setUp(boolean up) {
        isUp = up;
    }

    public boolean isDown() {
        return isDown;
    }

    public void setDown(boolean down) {
        isDown = down;
    }

    public boolean isLeft() {
        return isLeft;
    }

    public void setLeft(boolean left) {
        isLeft = left;
    }

    public boolean isRight() {
        return isRight;
    }

    public void setRight(boolean right) {
        isRight = right;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

}
