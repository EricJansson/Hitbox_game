package GameFiles;

import GameFiles.GameModel;
import GameFiles.GamePanel;
import GameFiles.GameWindow;

import java.awt.*;

public class MainGraphics {
    public static GamePanel gamePanel;
    public MainGraphics(GamePanel GP) {
        // new Background.CameraView();
        gamePanel = GP;
        // Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        // GP.setBorder(border);
        GP.setBounds(0, 0, GameWindow.width, GameWindow.height);
        GP.setPreferredSize(new Dimension(GameWindow.width, GameWindow.height));
        GP.setBackground(Color.DARK_GRAY);
        GP.setFocusable(true);
    }

    public void renderAllEntities(Graphics2D g2d) {
        for (int i = 0; i< GameModel.allEntities.size(); i++) {
            GameModel.allEntities.get(i).drawHitbox(g2d);
            GameModel.allEntities.get(i).render(g2d);
            GameModel.allEntities.get(i).renderVelocity(g2d);
        }
    }



    public void displayBorderWindow(Graphics2D g2d, int width, int height) { displayBorderWindow(g2d, width, height, Color.LIGHT_GRAY); }
    public void displayBorderWindow(Graphics2D g2d, int width, int height, Color color) {
        int borderSize = 12;
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(borderSize));
        g2d.drawRect((GameWindow.width / 2) - (width / 2), (GameWindow.height / 2) - (height / 2), width, height);
    }

}
