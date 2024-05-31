import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 16;
    final int scale = 3;
    final int tileSize = originalTileSize * scale; // 48x48
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int ScreenWidth = tileSize * maxScreenCol; //768 pixels
    final int ScreenHeight = tileSize * maxScreenRow; //576 pixels

    Thread gameThread;

    KeyHandler keyH = new KeyHandler();

    //FPS
    int FPS = 60;

    //player position
    int playerX = 100;
    int playerY = 100;

    int playerspeed=4;

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
        if(keyH.upPressed){
            playerY=playerY-playerspeed;
        }
        if(keyH.downPressed){
            playerY=playerY+playerspeed;
        }
        if(keyH.rightPressed){
            playerX=playerX+playerspeed;
        }
        if(keyH.leftPressed){
            playerX=playerX-playerspeed;
        }

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.WHITE);
        g2.fillRect(playerX,playerY,tileSize,tileSize);

    }
}
