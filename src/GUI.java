import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    GUI(){
    }
    public void creatingFrame() {
        ImageIcon imageOfLoli = new ImageIcon("graphic/lolipop.jfif");

        JLabel label = new JLabel();
        label.setText("Welcome to Lolieland <33");
        label.setIcon(imageOfLoli);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.CENTER);
        label.setForeground(new Color(255,105,180));
        label.setFont(new Font("MV Boli", Font.BOLD,70));

        JFrame frame1 = new JFrame();
        frame1.setTitle("Intro screen");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setResizable(false);
        frame1.setSize(1000,1000);
        frame1.setVisible(true);
        frame1.add(label);

        ImageIcon image = new ImageIcon("graphic/mainpage.jpg");
        frame1.setIconImage(image.getImage());
    }
}
