import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class WindowKeyListener implements KeyListener {
    static boolean space = false;
    static boolean w = false;
    static boolean a = false;
    static boolean s = false;
    static boolean d = false;
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
        }
    }

}