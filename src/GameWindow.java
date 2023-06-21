import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GameWindow extends JFrame {
    static int width;
    static int height;
    public GameWindow() {
        setTitle("Window Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setLocationRelativeTo(null);
        GamePanel gamePanel = new GamePanel();
        add(gamePanel);
        setVisible(true);
        // Add the key listener to the JFrame
        width = getSize().width;
        height = getSize().height;
        System.out.println(getSize());
    }

    public void closeWindow() {
        int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to quit?",
                "Quit game",
                JOptionPane.YES_NO_OPTION
        );
        if (choice == JOptionPane.YES_OPTION) {
            dispose();
        } else if (choice == JOptionPane.NO_OPTION) {
            System.out.println("Don't quit.");
        }
    }

}