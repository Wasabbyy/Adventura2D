package Characters;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Character {
    public int worldX;
    public int worldY;
    public int speed;

    public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2, health,healthBlank,healthHalf;

    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea;

    public int solidAreaDeafultX,solidAreaDeafultY;
    public boolean collisionOn=false;
    public boolean dangerousCollisionOn=false;

    public int hearth;

}
