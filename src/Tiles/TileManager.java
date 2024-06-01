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
    Tile[] tile;

    int[][] mapTileNumber;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tile = new Tile[10];
        getTileImage();
        mapTileNumber = new int[gamePanel.maxScreenCol][gamePanel.maxScreenRow];
        loadMap();
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/lava.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D g2) {
        int tileSize = gamePanel.tileSize;

        for (int row = 0; row < gamePanel.maxScreenRow; row++) {
            for (int col = 0; col < gamePanel.maxScreenCol; col++) {
                int texture = mapTileNumber[col][row];
                int x = col * tileSize;
                int y = row * tileSize;
                g2.drawImage(tile[texture].image, x, y, tileSize, tileSize, null);
            }
        }
    }

    public void loadMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/map/map1.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            for (int row = 0; row < gamePanel.maxScreenRow; row++) {
                String line = br.readLine();
                String[] numbers = line.split(" ");
                for (int col = 0; col < gamePanel.maxScreenCol; col++) {
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNumber[col][row] = num;
                }
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
