import java.awt.*;

public class Obstacle {

    GameMatrix hitbox;
    Color color = Color.magenta;
    public Obstacle(double xLeft, double xRight, double yDown, double yUp) {
        hitbox = new GameMatrix(xLeft, xRight, yDown, yUp);
    }


    public void drawHitbox(Graphics2D g2d) {
        int inset = 8;
        hitbox.drawMatrix(g2d, color);
        g2d.setColor(Color.DARK_GRAY);
        g2d.setStroke(new BasicStroke(6));
        g2d.drawRect((int) hitbox.getXmin() + inset, (int) hitbox.getYmin() + inset, (int) hitbox.getXmax() - (int) hitbox.getXmin() - (2 * inset), (int) hitbox.getYmax() - (int) hitbox.getYmin() - (2 * inset));
    }
}
