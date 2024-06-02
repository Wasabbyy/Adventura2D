package Characters;

import Core.GamePanel;
import Core.KeyHandler;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;

public class Player extends Character {

    private final GamePanel gamePanel;
    private final KeyHandler keyH;
    public int screenX;
    public int screenY;
    private long lastDamageTime; // Variable to track the last damage time
    private static final long DAMAGE_DELAY = 500; // 0.5 second delay in milliseconds

    public Player(GamePanel gamePanel, KeyHandler keyH) {
        this.gamePanel = gamePanel;
        this.keyH = keyH;

        screenX = gamePanel.ScreenWidth / 2 - gamePanel.tileSize / 2;
        screenY = gamePanel.ScreenHeight / 2 - gamePanel.tileSize / 2;

        soidArea = new Rectangle(8, 16, 32, 32);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = 1130;
        worldY = 1000;
        speed = 4;
        direction = "up";
        hearth = 4;
        lastDamageTime = System.currentTimeMillis(); // Initialize the damage time
    }

    public void update() {
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {

            int dx = 0, dy = 0;

            if (keyH.upPressed) direction = "up";
            if (keyH.downPressed) direction = "down";
            if (keyH.leftPressed) direction = "left";
            if (keyH.rightPressed) direction = "right";

            collisionOn = false;
            dangerousCollisionOn = false;
            gamePanel.collisionChecker.checkTile(this);
            gamePanel.dangerousCollision.checkTile(this);
            lavaDamage();

            if (!collisionOn) {
                switch (direction) {
                    case "up":
                        dy = -1;
                        break;
                    case "down":
                        dy = 1;
                        break;
                    case "left":
                        dx = -1;
                        break;
                    case "right":
                        dx = 1;
                        break;
                }
                if (dx != 0 && dy != 0) {
                    dx *= Math.sqrt(2) / 2;
                    dy *= Math.sqrt(2) / 2;
                }

                worldX += dx * speed;
                worldY += dy * speed;
            }

            spriteCounter++;
            if (spriteCounter > 12) {
                spriteNum = (spriteNum == 1) ? 2 : 1;
                spriteCounter = 0;
            }
        }
    }

    private void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/player_up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/player_up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/player_down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/player_down2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/player_right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/player_right2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/player_left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/player_left2.png"));

            health = ImageIO.read(getClass().getResourceAsStream("/player/heart.png"));
            healthBlank = ImageIO.read(getClass().getResourceAsStream("/player/heart_blank.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = switch (direction) {
            case "up" -> (spriteNum == 1) ? up1 : up2;
            case "down" -> (spriteNum == 1) ? down1 : down2;
            case "right" -> (spriteNum == 1) ? right1 : right2;
            case "left" -> (spriteNum == 1) ? left1 : left2;
            default -> null;
        };

        g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);

        if (hearth == 4) {
            g2.drawImage(health, 0, 0, gamePanel.tileSize, gamePanel.tileSize, null);
            g2.drawImage(health, 1 * gamePanel.tileSize, 0, gamePanel.tileSize, gamePanel.tileSize, null);
            g2.drawImage(health, 2 * gamePanel.tileSize, 0, gamePanel.tileSize, gamePanel.tileSize, null);
            g2.drawImage(health, 3 * gamePanel.tileSize, 0, gamePanel.tileSize, gamePanel.tileSize, null);
        }
        if (hearth == 3) {
            g2.drawImage(health, 0, 0, gamePanel.tileSize, gamePanel.tileSize, null);
            g2.drawImage(health, 1 * gamePanel.tileSize, 0, gamePanel.tileSize, gamePanel.tileSize, null);
            g2.drawImage(health, 2 * gamePanel.tileSize, 0, gamePanel.tileSize, gamePanel.tileSize, null);
            g2.drawImage(healthBlank, 3 * gamePanel.tileSize, 0, gamePanel.tileSize, gamePanel.tileSize, null);
        }
        if (hearth == 2) {
            g2.drawImage(health, 0, 0, gamePanel.tileSize, gamePanel.tileSize, null);
            g2.drawImage(health, 1 * gamePanel.tileSize, 0, gamePanel.tileSize, gamePanel.tileSize, null);
            g2.drawImage(healthBlank, 2 * gamePanel.tileSize, 0, gamePanel.tileSize, gamePanel.tileSize, null);
            g2.drawImage(healthBlank, 3 * gamePanel.tileSize, 0, gamePanel.tileSize, gamePanel.tileSize, null);
        }
        if (hearth == 1) {
            g2.drawImage(health, 0, 0, gamePanel.tileSize, gamePanel.tileSize, null);
            g2.drawImage(healthBlank, 1 * gamePanel.tileSize, 0, gamePanel.tileSize, gamePanel.tileSize, null);
            g2.drawImage(healthBlank, 2 * gamePanel.tileSize, 0, gamePanel.tileSize, gamePanel.tileSize, null);
            g2.drawImage(healthBlank, 3 * gamePanel.tileSize, 0, gamePanel.tileSize, gamePanel.tileSize, null);
        }
        if (hearth == 0) {
            g2.drawImage(healthBlank, 0, 0, gamePanel.tileSize, gamePanel.tileSize, null);
            g2.drawImage(healthBlank, 1 * gamePanel.tileSize, 0, gamePanel.tileSize, gamePanel.tileSize, null);
            g2.drawImage(healthBlank, 2 * gamePanel.tileSize, 0, gamePanel.tileSize, gamePanel.tileSize, null);
            g2.drawImage(healthBlank, 3 * gamePanel.tileSize, 0, gamePanel.tileSize, gamePanel.tileSize, null);
        }
    }

    public void lavaDamage() {
        long currentTime = System.currentTimeMillis();
        if (dangerousCollisionOn) {
            if (currentTime - lastDamageTime >= DAMAGE_DELAY) {
                hearth -= 1;
                lastDamageTime = currentTime; // Update the last damage time
            }
        } else {
            // Update the lastDamageTime to the current time when the player leaves the lava
            lastDamageTime = currentTime;
        }
    }
}
