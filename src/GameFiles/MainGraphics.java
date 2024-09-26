package GameFiles;

import Animations.AnimationInstance;
import Animations.AnimationTemplate;

import java.awt.*;
import java.util.ArrayList;

public class MainGraphics {
    public static GamePanel gamePanel;

    // Hold the animation images and its data
    public static ArrayList<AnimationTemplate> animationTemplateList = new ArrayList<>();

    // Holds the actual playable animation with time intervals and sub-image order selection
    public static ArrayList<AnimationInstance> animationList = new ArrayList<>();

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
            GameModel.allEntities.get(i).renderer.updateCurrentAnimationFrame();
            // GameModel.allEntities.get(i).renderer.drawHitbox(g2d);
            GameModel.allEntities.get(i).renderer.render(g2d);
            // GameModel.allEntities.get(i).renderer.renderVelocity(g2d);
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
