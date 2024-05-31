package Tiles;

import Core.GamePanel;

import javax.imageio.IIOException;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class TileManager {

    GamePanel gamePanel;
    Tile[]tile;

    public TileManager(GamePanel gamePanel){

        this.gamePanel=gamePanel;
        tile = new Tile[10];
        getTileImage();

    }

    public void getTileImage(){
        try{
            tile[0]= new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/lava.png"));

            tile[1]= new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
        }

        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D g2){

        g2.drawImage(tile[0].image,0,0,gamePanel.tileSize,gamePanel.tileSize,null);
        g2.drawImage(tile[0].image,48,0,gamePanel.tileSize,gamePanel.tileSize,null);
        g2.drawImage(tile[0].image,96,0,gamePanel.tileSize,gamePanel.tileSize,null);
        g2.drawImage(tile[0].image,144,0,gamePanel.tileSize,gamePanel.tileSize,null);
        g2.drawImage(tile[0].image,192,0,gamePanel.tileSize,gamePanel.tileSize,null);
        g2.drawImage(tile[0].image,96,0,gamePanel.tileSize,gamePanel.tileSize,null);
        g2.drawImage(tile[1].image,48,0,gamePanel.tileSize,gamePanel.tileSize,null);
    }
}
