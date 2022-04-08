package MainPackage;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Launcher {
    private GamePanel gamePanel;
    private boolean opened;
    private String titleTxt, startTxt, boardTxt, exitTxt;
    private boolean up, down;
    public boolean upPressed, downPressed, enterPressed;
    private int option;
    private String bestTime;

    public Launcher(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        this.opened = true;
        this.titleTxt = "THE STRUGGLER";
        this.startTxt = "New game";
        this.boardTxt = "Survivors";
        this.exitTxt = "Exit";
        this.up = false;
        this.down = false;
        this.option = 1;
        this.upPressed = false;
        this.downPressed = false;
        this.enterPressed = false;
        readFile();
    }


    public void tick(){
        changePointer();
        chooseOption();
    }
    public void render(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0,0,1000,563);

        g.drawImage(gamePanel.getLevelLoader().playerFrontImg, 500 - 64, 80, 128, 128, null);
        g.setFont(g.getFont().deriveFont(Font.BOLD, 60F));
        g.setColor(Color.GRAY);
        g.drawString(titleTxt, getXForCenteredTxt(titleTxt, g) + 3, 300 + 3);
        g.setColor(Color.WHITE);
        g.drawString(titleTxt, getXForCenteredTxt(titleTxt, g), 300);
        g.setFont(g.getFont().deriveFont(Font.BOLD, 30F));
        g.drawString(startTxt, getXForCenteredTxt(startTxt, g), 380);
        g.drawString(boardTxt, getXForCenteredTxt(boardTxt, g), 420);
        g.drawString(exitTxt, getXForCenteredTxt(exitTxt, g), 460);

        g.setFont(g.getFont().deriveFont(Font.BOLD, 20F));
        g.drawString(bestTime, getXForCenteredTxt(bestTime, g), 500); // best score

        g.setFont(g.getFont().deriveFont(Font.BOLD, 30F));
        g.setColor(new Color(0, 246, 255));
        if (option == 1){
            g.drawString(startTxt, getXForCenteredTxt(startTxt, g), 380);
        }
        else if (option == 2){
            g.drawString(boardTxt, getXForCenteredTxt(boardTxt, g), 420);
        }
        else {
            g.drawString(exitTxt, getXForCenteredTxt(exitTxt, g), 460);
        }

    }

    private void chooseOption(){
        if (enterPressed){
            if (option == 1){
                opened = false;
                gamePanel.getHandler().startTimer();
            }
            else if (option == 2){

            }
            else if (option == 3){
                System.exit(0);
            }
            enterPressed = false;
        }
    }

    private void readFile(){

        try {
            File txtFile = new File("src/TextFiles/SurvivorsDatabase.txt");
            Scanner myReader = new Scanner(txtFile);
            bestTime = myReader.nextLine();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        gamePanel.getLevelHandler().setBestTime(Double.parseDouble(bestTime));
    }

    private void changePointer(){
        if (up){
            option-=1;
            up = false;
        }
        else if (down){
            option+=1;
            down = false;
        }
        if (option == 0)
            option = 3;
        else if (option == 4)
            option = 1;
    }

    private int getXForCenteredTxt(String txt, Graphics g){
        int length = (int)g.getFontMetrics().getStringBounds(txt, g).getWidth();
        return 1000/2 - length/2;
    }

    public boolean isOpened(){
        return opened;
    }

    public void setOpened(boolean opened){
        this.opened = opened;
        readFile();
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
}
