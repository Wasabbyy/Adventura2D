import javax.swing.*;

public class GamePanel extends JFrame {

    final int originalTileSize = 16;
    final int scale =3;
    final int tileSize = originalTileSize * scale; // 48x48
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int maxScreenWidth = tileSize * maxScreenCol; //768 pixels
    final int maxScreenHeight = tileSize * maxScreenRow; //576 pixels


}
