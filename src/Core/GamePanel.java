package Core;

import Characters.Inventory;
import Characters.Player;
import Tiles.TileManager;
import Object.SuperObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

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

    public boolean endGame = false;
    public final int worldWidth = maxWorldCol * tileSize;
    public final int worldHeight = maxWorldRow * tileSize;



    BufferedImage endScreen;

    Thread gameThread;
    Inventory inventory;

    KeyHandler keyH = new KeyHandler();
    public Collision collisionChecker = new Collision(this);
    public DangerousCollision dangerousCollision = new DangerousCollision(this);

    public Player player = new Player(this, keyH,inventory);
    public TileManager tileManager = new TileManager(this);
    public ItemPlacer itemPlacer = new ItemPlacer(this);

    public SuperObject obj[] = new SuperObject[20];




    //FPS
    int FPS = 60;

    public GamePanel() {
        this.setPreferredSize(new Dimension(ScreenWidth, ScreenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){
        itemPlacer.setObject();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS; // 0.2 sec cca
        double nextDrawTime = System.nanoTime() + drawInterval; //Will do that it wont draw that fast, basically takes

        while (gameThread != null && player.hearth != 0) {
            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        endGame = true;
        repaint(); // Trigger a final repaint to display the end game screen
    }

    public void setEndGame(Graphics2D g2) {
        try {
            endScreen = ImageIO.read(getClass().getResourceAsStream("/screens/end.png"));
            g2.drawImage(endScreen, 0, 0, ScreenWidth, ScreenHeight, null); // Draw the end screen image correctly
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;


        tileManager.draw(g2);
        for (int i=0; i< obj.length; i++){
            if(obj[i] != null){
                obj [i].draw(g2,this);
            }
        }
        player.draw(g2);

        if (endGame) {
            setEndGame(g2);
        }

        g2.dispose();
    }
}
