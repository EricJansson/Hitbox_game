import java.awt.*;

public class Vector2D {
    public Vector pos;
    public Vector dir;
    public Vector2D(Vector dir, Vector pos) {
        this.dir = dir;
        this.pos = pos;
    }
    public Vector2D(double dirX, double dirY, double posX, double posY) {
        this.dir = new Vector(dirX, dirY);
        this.pos = new Vector(posX, posY);
    }

    /*
    public void render(Graphics2D g2d) {
        // Set color and thickness of the arrow
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(4));

        int arrowSize = 15;  // Size of the arrowhead
        int arrowAngle = 25; // Angle of the arrowhead

        int arrowLength = (int) Math.sqrt(dir.getX() * dir.getX() + dir.getY() * dir.getY());
        int arrowHeadX = (int) pos.getX() + (int) dir.getX();
        int arrowHeadY = (int) pos.getY() + (int) dir.getY();

        g2d.drawLine((int) pos.getX(), (int) pos.getY(), arrowHeadX, arrowHeadY);

        // Calculate and draw the arrowhead
        double angle = Math.atan2((int) dir.getY(), (int) dir.getX());
        int arrowEndX1 = (int) (arrowHeadX - arrowSize * Math.cos(angle + Math.toRadians(arrowAngle)));
        int arrowEndY1 = (int) (arrowHeadY - arrowSize * Math.sin(angle + Math.toRadians(arrowAngle)));
        int arrowEndX2 = (int) (arrowHeadX - arrowSize * Math.cos(angle - Math.toRadians(arrowAngle)));
        int arrowEndY2 = (int) (arrowHeadY - arrowSize * Math.sin(angle - Math.toRadians(arrowAngle)));

        g2d.drawLine(arrowHeadX, arrowHeadY, arrowEndX1, arrowEndY1);
        g2d.drawLine(arrowHeadX, arrowHeadY, arrowEndX2, arrowEndY2);
    }

     */

}
