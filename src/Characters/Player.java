package Characters;

import Core.GamePanel;
import Core.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Character{

    GamePanel gamePanel;
    KeyHandler keyH;


    public  Player(GamePanel gamePanel, KeyHandler keyH){
        this.gamePanel=gamePanel;
        this.keyH=keyH;
        setDefaultValues();
        getPlayerImage();
    }

    public  void setDefaultValues(){
        x=100;
        y=100;
        speed=4;
        direction="up";
    }

    public void update(){
        if(keyH.upPressed ||keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {


            if (keyH.upPressed) {
                direction = "up";
                y = y - speed;
            }
            if (keyH.downPressed) {
                direction = "down";
                y = y + speed;
            }
            if (keyH.rightPressed) {
                direction = "right";
                x = x + speed;
            }
            if (keyH.leftPressed) {
                direction = "left";
                x = x - speed;
            }

            sproteCounter++;
            if (sproteCounter > 20) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                sproteCounter = 0;
            }
        }

    }
    public void getPlayerImage(){
        try  {
            up1= ImageIO.read(getClass().getResourceAsStream("/player/player_up1.png"));
            up2= ImageIO.read(getClass().getResourceAsStream("/player/player_up2.png"));
            down1= ImageIO.read(getClass().getResourceAsStream("/player/player_down1.png"));
            down2= ImageIO.read(getClass().getResourceAsStream("/player/player_down2.png"));
            right1= ImageIO.read(getClass().getResourceAsStream("/player/player_right1.png"));
            right2= ImageIO.read(getClass().getResourceAsStream("/player/player_right2.png"));
            left1= ImageIO.read(getClass().getResourceAsStream("/player/player_left1.png"));
            left2= ImageIO.read(getClass().getResourceAsStream("/player/player_left2.png"));
    }
    catch (IOException e){
            e.printStackTrace();
    }
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;

        switch (direction){
            case "up":
                if(spriteNum==1){
                image = up1;
                }
                if(spriteNum==2){
                    image=up2;
                }
                break;
            case "down":
                if(spriteNum==1){
                    image = down1;
                }
                if(spriteNum==2){
                    image=down2;
                }
                break;
            case "right":
                if(spriteNum==1){
                    image = right1;
                }
                if(spriteNum==2){
                    image=right2;
                }
                break;
            case "left":
                if(spriteNum==1){
                    image = left1;
                }
                if(spriteNum==2){
                    image=left2;
                }
                break;
        }
        g2.drawImage(image,x,y, gamePanel.tileSize,gamePanel.tileSize,null);


    }
}

