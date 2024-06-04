package Core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed;
    public boolean downPressed;
    public boolean rightPressed;
    public boolean leftPressed;
    public boolean  inventoryPressed;

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int code = keyEvent.getKeyCode();

        switch (code) {
            case KeyEvent.VK_W:
                upPressed = true;
                break;
            case KeyEvent.VK_A:
                leftPressed = true;
                break;
            case KeyEvent.VK_S:
                downPressed = true;
                break;
            case KeyEvent.VK_D:
                rightPressed = true;
                break;
            case KeyEvent.VK_I:
                inventoryPressed = true;
                break;
            default:
                // Handle other keys if necessary
                break;
        }
    }


    @Override
    public void keyReleased(KeyEvent keyEvent) {

        int code = keyEvent.getKeyCode();

        if(code == KeyEvent.VK_W){
            upPressed =false;

        }
        if(code == KeyEvent.VK_A){
            leftPressed=false;

        }
        if(code == KeyEvent.VK_S){
            downPressed=false;

        }
        if(code == KeyEvent.VK_D){
            rightPressed=false;
        }
        if(code == KeyEvent.VK_I){
            inventoryPressed =false;

        }

    }

    }

