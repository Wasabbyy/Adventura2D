package Core;

import Characters.Player;
import Tiles.Tile;
import Tiles.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; // 48x48
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int ScreenWidth = tileSize * maxScreenCol; //768 pixels
    public final int ScreenHeight = tileSize * maxScreenRow; //576 pixels

    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = maxWorldCol*tileSize;
    public final int worldHeight = maxWorldRow*tileSize;

    Thread gameThread;

    KeyHandler keyH = new KeyHandler();
    public Collision collision = new Collision(this);


    public Player player= new Player(this,keyH);
    TileManager tile=new TileManager(this);

    //FPS
    int FPS = 60;

    public GamePanel() {
        this.setPreferredSize(new Dimension(ScreenWidth, ScreenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS; // 0.2 sec cca
        double nextDrawTime = System.nanoTime()+drawInterval; //Will do that it wont draw that fast, basically takes


        while (gameThread != null) {

            update();
            repaint();

            try{
                double remainingTime = nextDrawTime- System.nanoTime();
                remainingTime= remainingTime/1000000;
                if(remainingTime<0){
                    remainingTime=0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime+=drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void update(){

        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        tile.draw(g2);
        player.draw(g2);
       g2.dispose();

    }
}
