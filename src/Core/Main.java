package Core;

import Core.GamePanel;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
     JFrame window = new JFrame();
     window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     window.setResizable(false);
     window.setTitle("Super Cool RPG :))");

     GamePanel gamePanel = new GamePanel();
     window.add(gamePanel);
     window.pack();

     window.setLocationRelativeTo(null);
     window.setVisible(true);


     gamePanel.setupGame();
     gamePanel.startGameThread();



    }
}