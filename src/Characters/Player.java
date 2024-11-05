package Characters;

import Core.GamePanel;
import Core.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Character {

    public Inventory inventory;
    public Spells spells;
    private long lastInventoryTime; // Variable to track the last time inventory was shown
    private static final long INVENTORY_COOLDOWN = 500; // 0.5 second cooldown in milliseconds

    private final GamePanel gamePanel;
    private final KeyHandler keyH;
    boolean drawInventory = false;
    public int screenX;
    public int screenY;
    private long lastDamageTime; // Variable to track the last damage time
    private static final long DAMAGE_DELAY = 500; // 0.5 second delay in milliseconds

    public Player(GamePanel gamePanel, KeyHandler keyH, Inventory inventory,Spells spells) {
        this.inventory = inventory != null ? inventory : new Inventory(gamePanel, this);
        this.spells=new Spells(gamePanel,this);
        this.gamePanel = gamePanel;
        this.keyH = keyH;

        screenX = gamePanel.ScreenWidth / 2 - gamePanel.tileSize / 2;
        screenY = gamePanel.ScreenHeight / 2 - gamePanel.tileSize / 2;

        solidArea = new Rectangle(8, 16, 32, 32);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = 1130;
        worldY = 1000;
        speed = 4;
        direction = "up";
        hearth = 6;
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDeafultX = 8;
        solidAreaDeafultY = 16;
        lastDamageTime = System.currentTimeMillis(); // Initialize the damage time
    }

    public void update() {
        long currentTime = System.currentTimeMillis();
        boolean inventoryPressedWithCooldown = keyH.inventoryPressed && (currentTime - lastInventoryTime >= INVENTORY_COOLDOWN);

        if (keyH.castPressed && !spells.active) {
            spells.castSpell(); // Cast the spell when the cast key is pressed and spell is inactive
            keyH.castPressed=false;
        }

        spells.updateSpell(); // Update the spell's position each frame

        if (inventoryPressedWithCooldown) {
            drawInventory = !drawInventory; // Toggle the inventory display
            lastInventoryTime = currentTime; // Update the last inventory time
        }

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
            int objIndex = gamePanel.collisionChecker.checkObject(this, true);
            pickUp(objIndex);
            lavaDamage();

            if (!collisionOn) {
                switch (direction) {
                    case "up" -> dy = -1;
                    case "down" -> dy = 1;
                    case "left" -> dx = -1;
                    case "right" -> dx = 1;
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

            health = ImageIO.read(getClass().getResourceAsStream("/player/hearth.png"));
            healthBlank = ImageIO.read(getClass().getResourceAsStream("/player/hearthempty.png"));
            healthHalf = ImageIO.read(getClass().getResourceAsStream("/player/hearthhalf.png"));
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
        drawHearth(g2);

        if (drawInventory) {
            inventory.drawInventory(g2);
        }

        spells.drawSpell(g2); // Draw the spell if active
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

    public void drawHearth(Graphics2D g2) {
        for (int i = 0; i < hearth; i++) {
            if (hearth % 2 == 1) {
                if (i + 1 == hearth) {
                    g2.drawImage(healthHalf, i / 2 * gamePanel.tileSize, 0, gamePanel.tileSize, gamePanel.tileSize, null);
                } else {
                    g2.drawImage(health, i / 2 * gamePanel.tileSize, 0, gamePanel.tileSize, gamePanel.tileSize, null);
                }
            } else {
                g2.drawImage(health, i / 2 * gamePanel.tileSize, 0, gamePanel.tileSize, gamePanel.tileSize, null);
                i++;
            }
        }
    }

    public void pickUp(int i) {
        if (i != 999) {
            String objectName = gamePanel.obj[i].name;

            switch (objectName) {
                case "Firerock":
                    inventory.addItems("Firerock");
                    gamePanel.obj[i] = null;
                    break;
            }
        }
    }
}
