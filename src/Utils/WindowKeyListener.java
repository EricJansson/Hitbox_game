package Utils;

import Background.*;
import DataFormats.*;
import Enums.*;
import GameFiles.*;
import GameObjects.*;
import TileMap.*;
import Utils.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class WindowKeyListener implements KeyListener {
    int ii = 0;
    public static boolean space = false;
    public static boolean w = false;
    public static boolean a = false;
    public static boolean s = false;
    public static boolean d = false;
    public static boolean t = false;
    public static boolean y = false;
    public static boolean p = false;
    private boolean spacePressed = false;

    public WindowKeyListener() {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_W) {  // NORTH
            w = true;
        } else if (keyCode == KeyEvent.VK_A) {  // WEST
            a = true;
        } else if (keyCode == KeyEvent.VK_S) {  // SOUTH
            s = true;
        } else if (keyCode == KeyEvent.VK_D) {  // EAST
            d = true;
        } else if (keyCode == KeyEvent.VK_ESCAPE) {
            // gameWindow.dispose(); // Close window
            System.exit(0); // Terminate the application
            // gameWindow.closeWindow();
        } else if (keyCode == KeyEvent.VK_T) {  // Test
            // Move background
            t = true;
            if (ii >= GameModel.allEntities.size() - 1) ii = 0; else ii++;
            CameraView.setTarget(GameModel.allEntities.get(ii));
        } else if (keyCode == KeyEvent.VK_Y) {  // Test
            // Move background
            y = true;
            CameraView.target = null;
            CameraView.goToTile(14,10);
        } else if (keyCode == KeyEvent.VK_P) {  // Test
            // Move background
            p = true;
            GamePanel.hero.getCurrentCoordinates();

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char keyChar = e.getKeyChar();
        if (keyChar == ' ' && !spacePressed) {
            spacePressed = true;
            space = true;
            System.out.println("Space pressed!");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_W) {  // NORTH
            w = false;
        } else if (keyCode == KeyEvent.VK_A) {  // WEST
            a = false;
        } else if (keyCode == KeyEvent.VK_S) {  // SOUTH
            s = false;
        } else if (keyCode == KeyEvent.VK_D) {  // EAST
            d = false;
        } else if (keyCode == KeyEvent.VK_SPACE) {  // EAST
            spacePressed = false;
        } else if (keyCode == KeyEvent.VK_T) {  // Test
            t = false;
        } else if (keyCode == KeyEvent.VK_Y) {  // Test
            y = false;
        } else if (keyCode == KeyEvent.VK_U) {  // Test
            p = false;
        }
    }

}