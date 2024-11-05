package Characters;

import Core.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Spells {
    private Player player;
    private GamePanel gamePanel;
    private BufferedImage frostbolt;
    private int x, y; // Position of the spell in the world
    private String direction; // Direction of the spell
    public boolean active; // Whether the spell is currently active
    private final int speed = 6; // Speed of the spell
    private long spellCastTime; // Time when the spell was cast

    public Spells(GamePanel gamePanel, Player player) {
        this.gamePanel = gamePanel;
        this.player = player;
        loadSpell();
        active = false; // Initialize spell as inactive
    }

    private void loadSpell() {
        try {
            frostbolt = ImageIO.read(getClass().getResourceAsStream("/objects/firerock.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void castSpell() {
        // Set the spell's position to the player's current position and direction
        this.x = player.worldX;
        this.y = player.worldY;
        this.direction = player.direction;
        this.active = true; // Activate the spell
        this.spellCastTime = System.currentTimeMillis(); // Record the cast time
    }

    public void updateSpell() {
        if (active) {
            // Move the spell based on its direction
            switch (direction) {
                case "up" -> y -= speed;
                case "down" -> y += speed;
                case "left" -> x -= speed;
                case "right" -> x += speed;
            }

            // Check if the spell has been active for more than 2 seconds (2000 ms)
            if (System.currentTimeMillis() - spellCastTime >= 2000) {
                active = false; // Deactivate the spell after 2 seconds
            }

        }
    }

    public void drawSpell(Graphics2D g2) {
        if (active) {
            int screenX = x - player.worldX + player.screenX;
            int screenY = y - player.worldY + player.screenY;
            g2.drawImage(frostbolt, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
        }
    }
}
