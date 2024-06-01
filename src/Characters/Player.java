package Characters;

import Core.GamePanel;
import Core.KeyHandler;
import Tiles.TileManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Character {

    GamePanel gamePanel;
    KeyHandler keyH;
    public int screenX;
    public int screenY;

    public Player(GamePanel gamePanel, KeyHandler keyH) {
        this.gamePanel = gamePanel;
        this.keyH = keyH;

        screenX= gamePanel.ScreenWidth/2-(gamePanel.tileSize/2);
        screenY= gamePanel.ScreenHeight/2-(gamePanel.tileSize/2);

        soidArea = new Rectangle(8, 16, 32, 32);

        setDefaultValues();
        getPlayerImage();
    }


    public void setDefaultValues() {
        worldX = 500;
        worldY = 500;
        speed = 4;
        direction = "up";
    }

    public void update() {
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            int dx = 0;
            int dy = 0;

            if (keyH.upPressed) {
                dy = -1;
                direction = "up";
            }
            if (keyH.downPressed) {
                dy = 1;
                direction = "down";
            }
            if (keyH.leftPressed) {
                dx = -1;
                direction = "left";
            }
            if (keyH.rightPressed) {
                dx = 1;
                direction = "right";
            }

            // Normalize the speed for diagonal movement
            if (dx != 0 && dy != 0) {
                dx *= Math.sqrt(2) / 2;
                dy *= Math.sqrt(2) / 2;
            }

            worldX += dx * speed;
            worldY += dy * speed;

            collision = false;
            gamePanel.collision.checkTile(this);

            spriteCounter++;
            if (spriteCounter > 12) {
                spriteNum = (spriteNum == 1) ? 2 : 1;
                spriteCounter = 0;
            }
        }
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/player_up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/player_up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/player_down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/player_down2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/player_right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/player_right2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/player_left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/player_left2.png"));
        } catch (IOException e) {
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
        g2.drawImage(image,screenX,screenY, gamePanel.tileSize,gamePanel.tileSize,null);


    }

    public void lavaDeath() {
        // Implement lavaDeath method if needed
    }
}
