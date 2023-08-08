package MapObjects;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class EntityRenderer {
    public Entity entity;
    public Color color = Color.GREEN;
    int borderSize = 16;
    int inset = borderSize / 2;

    public BufferedImage image;


    public EntityRenderer(Entity entity) {
        this.entity = entity;
    }

    public BufferedImage getImage() { return image; }


    public void render(Graphics2D g2d) {
        if (image == null) return; // If no image defined, don't render
        AffineTransform affTrans = new AffineTransform();
        AffineTransform originalTransform = g2d.getTransform();

        // Get the rounded position and orientation of the vehicle
        int roundedX = (int) Math.round(entity.position.getX());
        int roundedY = (int) Math.round(entity.position.getY());
        // angle ++;
        // if (angle >= 360) angle = 0;

        int imgWidth = getImage().getWidth();
        int imgHeight = getImage().getHeight();
        // Rotate the graphics
        // g2d.rotate(Math.round(angle), roundedX + (float) (width / 2), roundedY + (float) (height / 2));

        // Set the translation to the correct position
        affTrans.translate(roundedX - (float) (imgWidth / 2) + (float) (entity.width / 2) - entity.imageOffsetX, roundedY - (float) (imgHeight / 2) + (float) (entity.height / 2) - entity.imageOffsetY);
        // Apply the translation using the AffineTransform
        g2d.drawImage(getImage(), affTrans, null);
        g2d.setTransform(originalTransform);

    }


    public void renderVelocity(Graphics2D g2d) {
        // Set color and thickness of the arrow
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(6));

        int arrowSize = 20;  // Size of the arrowhead
        int arrowAngle = 30; // Angle of the arrowhead

        int arrowLength = (int) Math.sqrt(entity.velocity.dir.getX() * entity.velocity.dir.getX() + entity.velocity.dir.getY() * entity.velocity.dir.getY());
        int arrowHeadX = (int) entity.velocity.pos.getX() + (int) entity.velocity.dir.getX() * 4;
        int arrowHeadY = (int) entity.velocity.pos.getY() + (int) entity.velocity.dir.getY() * 4;

        if (entity.velocity.dir.getX() == 0 && entity.velocity.dir.getY() == 0) {
            return;
        }
        g2d.drawLine((int) entity.velocity.pos.getX(), (int) entity.velocity.pos.getY(), arrowHeadX, arrowHeadY);

        // Calculate and draw the arrowhead
        double angle = Math.atan2((int) entity.velocity.dir.getY(), (int) entity.velocity.dir.getX());
        int arrowEndX1 = (int) (arrowHeadX - arrowSize * Math.cos(angle + Math.toRadians(arrowAngle)));
        int arrowEndY1 = (int) (arrowHeadY - arrowSize * Math.sin(angle + Math.toRadians(arrowAngle)));
        int arrowEndX2 = (int) (arrowHeadX - arrowSize * Math.cos(angle - Math.toRadians(arrowAngle)));
        int arrowEndY2 = (int) (arrowHeadY - arrowSize * Math.sin(angle - Math.toRadians(arrowAngle)));

        g2d.drawLine(arrowHeadX, arrowHeadY, arrowEndX1, arrowEndY1);
        g2d.drawLine(arrowHeadX, arrowHeadY, arrowEndX2, arrowEndY2);
    }


    public void drawHitbox(Graphics2D g2d) {
        entity.hitbox.drawMatrix(g2d, color);
        g2d.setColor(Color.DARK_GRAY);
        g2d.setStroke(new BasicStroke(6));
        g2d.drawRect((int) entity.position.getX() + inset, (int) entity.position.getY() + inset, entity.width - borderSize, entity.height - borderSize);
    }
}
