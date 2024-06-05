package Characters;

import Core.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Inventory {

    Player player;
    GamePanel gamePanel;
    BufferedImage inventoryImage;
    Map<String, BufferedImage> itemImages;
    Map<String, Integer> items;

    public int inventoryX;
    public int inventoryY;

    public Inventory(GamePanel gamePanel, Player player) {
        this.items = new HashMap<>();
        this.player = player;
        this.gamePanel = gamePanel;
        this.itemImages = new HashMap<>();
        loadInventory();
        loadItemImages();
    }

    public void addItems(String item) {
        if (item != null) {
            items.put(item, items.getOrDefault(item, 0) + 1);
        }
    }

    public void loadInventory() {
        try {
            inventoryImage = ImageIO.read(getClass().getResourceAsStream("/player/inventory.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadItemImages() {
        try {
            itemImages.put("Key", ImageIO.read(getClass().getResourceAsStream("/objects/blueheart.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawInventory(Graphics2D g2) {
        inventoryX = 500;
        inventoryY = 20;

        g2.drawImage(inventoryImage, inventoryX, inventoryY, gamePanel.tileSize * 3, gamePanel.tileSize * 3, null);

        drawItems(g2);
    }

    public void drawItems(Graphics2D g2) {
        int itemX = inventoryX + 20;
        int itemY = inventoryY + 20;

        int itemsPerRow = 3;
        int itemSpacing = gamePanel.tileSize;

        int index = 0;
        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            String itemName = entry.getKey();
            int itemCount = entry.getValue();
            BufferedImage itemImage = itemImages.get(itemName);
            if (itemImage != null) {
                g2.drawImage(itemImage, itemX, itemY, gamePanel.tileSize, gamePanel.tileSize, null);
                g2.setColor(Color.WHITE);
                g2.setFont(new Font("Arial", Font.BOLD, 14));
                g2.drawString("x" + itemCount, itemX + gamePanel.tileSize - 15, itemY + gamePanel.tileSize - 5);
            }

            itemX += itemSpacing;
            index++;
            if (index % itemsPerRow == 0) {
                itemX = inventoryX + 10;
                itemY += itemSpacing;
            }
        }
    }
}
