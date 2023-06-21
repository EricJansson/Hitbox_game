import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MainGraphics {
    public static GamePanel gamePanel;
    public MainGraphics(GamePanel GP) {
        gamePanel = GP;

        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        GP.setBorder(border);
        GP.setBounds(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
        // this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        GP.setBackground(Color.DARK_GRAY);
        GP.setFocusable(true);
    }

    public void renderAllEntities(Graphics2D g2d) {
        for (int i = 0; i< GameModel.allEntities.size(); i++) {
            GameModel.allEntities.get(i).render(g2d);
        }
    }

}
