package MapObjects;

import DataFormats.GameMatrix;
import Enums.MovementType;

import java.awt.*;
import java.util.ArrayList;

public class Obstacle {

    public GameMatrix hitbox;
    Color color = Color.magenta;
    public MovementType[] movingType;

    public Obstacle(MovementType[] movingType, double xLeft, double xRight, double yDown, double yUp) {
        this.movingType = movingType;
        hitbox = new GameMatrix(xLeft, xRight, yDown, yUp);
    }

    public static Obstacle getObstacleWithLargestCollision(ArrayList<Obstacle> list) {
        if (list.size() == 0) {
            return null;
        }
        return list.remove(list.size()-1);
    }

    public void drawHitbox(Graphics2D g2d) {
        int inset = 8;
        hitbox.drawMatrix(g2d, color);
        g2d.setColor(Color.DARK_GRAY);
        g2d.setStroke(new BasicStroke(6));
        g2d.drawRect((int) hitbox.getXmin() + inset, (int) hitbox.getYmin() + inset, (int) hitbox.getXmax() - (int) hitbox.getXmin() - (2 * inset), (int) hitbox.getYmax() - (int) hitbox.getYmin() - (2 * inset));
    }
}
