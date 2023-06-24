import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements ActionListener {
    public static GameModel model;
    public static MainGraphics mainGFX;
    public static Entity hero;
    public static Matrix_hitbox matrix;
    static int WIDTH = GameWindow.width;
    static int HEIGHT = GameWindow.height;
    static final int FRAMES_PER_SECOND = 60;
    static Timer timer;
    public GamePanel() {
        model = new GameModel();
        mainGFX = new MainGraphics(this);
        matrix = new Matrix_hitbox();
        WindowKeyListener keyLstnr = new WindowKeyListener();
        addKeyListener(keyLstnr);
        start();
    }

    public void start() {
        timer = new Timer((1000 / FRAMES_PER_SECOND), this);
        timer.start();
    }

    public static void drawMap(Graphics g) {
        mainGFX.renderAllEntities((Graphics2D) g);
    }

    public void updateModel() {
        model.updateEntities();
    }

    public void checkControls() {
        if (WindowKeyListener.a) {
            GamePanel.hero.accelerate('W');
        }
        if (WindowKeyListener.d) {
            GamePanel.hero.accelerate('E');
        }
        if (WindowKeyListener.w) {
            GamePanel.hero.accelerate('N');
        }
        if (WindowKeyListener.s) {
            GamePanel.hero.accelerate('S');
        }
        if (WindowKeyListener.space) {
            GamePanel.hero.boost();
            System.out.println("BOOST!!");
            WindowKeyListener.space = false;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawMap(g);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == timer) {
            checkControls();
            updateModel();
            repaint();

        }
    }



}
