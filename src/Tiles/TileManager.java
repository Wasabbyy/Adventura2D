package Tiles;

import Core.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gamePanel;
    public Tile[] tile;
    public int[][] mapTileNumber;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tile = new Tile[6]; // Adjust size based on the number of tile types
        loadTileImages();
        mapTileNumber = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];
        loadMap("/map/lada2.txt");
    }

    private void loadTileImages() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/lavafloor.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/lavafloor.png"));


            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tile[3].dangerousCollision = true;
            tile[3].collision = true;

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/lava.png"));
            tile[4].dangerousCollision = true;


            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/floor.png"));


            // Load other tile images similarly
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D g2) {
        int tileSize = gamePanel.tileSize;
        int playerWorldX = gamePanel.player.worldX;
        int playerWorldY = gamePanel.player.worldY;
        int playerScreenX = gamePanel.player.screenX;
        int playerScreenY = gamePanel.player.screenY;

        for (int col = 0; col < gamePanel.maxWorldCol; col++) {
            for (int row = 0; row < gamePanel.maxWorldRow; row++) {
                int texture = mapTileNumber[col][row];
                int worldX = col * tileSize;
                int worldY = row * tileSize;

                int screenX = worldX - playerWorldX + playerScreenX;
                int screenY = worldY - playerWorldY + playerScreenY;

                g2.drawImage(tile[texture].image, screenX, screenY, tileSize, tileSize, null);
            }
        }
    }

    private void loadMap(String filename) {
        try (InputStream is = getClass().getResourceAsStream(filename);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

            for (int row = 0; row < gamePanel.maxWorldRow; row++) {
                String line = br.readLine();
                String[] numbers = line.split(" ");
                for (int col = 0; col < gamePanel.maxWorldCol; col++) {
                    mapTileNumber[col][row] = Integer.parseInt(numbers[col]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
