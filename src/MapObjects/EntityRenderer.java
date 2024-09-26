package MapObjects;

import Animations.EntityAnimation;
import GameFiles.GamePanel;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class EntityRenderer {
    public Entity entity;
    public EntityAnimation animation = null;
    public int currentAnimationFrameIndex = -1;
    public Color color = Color.GREEN;
    int borderSize = 16;
    int inset = borderSize / 2;

    public BufferedImage image;
    public boolean loopAnimation;

    public EntityRenderer(Entity entity) {
        this.entity = entity;
    }

    public void startAnimation(String animInstanceName) { startAnimation(animInstanceName, 0.0, 0); }
    public void startAnimation(String animInstanceName, double fullAnimationRunTimeSeconds) { startAnimation(animInstanceName, fullAnimationRunTimeSeconds, 0); }
    public void startAnimation(String animInstanceName, int priority) { startAnimation(animInstanceName, 0.0, priority); }
    public void startAnimation(String animInstanceName, double fullAnimationRunTimeSeconds, int priority) {
        if (this.animation != null && this.animation.priority > priority) {
            return;
        }
        stopAnimation(); // Reset previous animation settings
        this.animation = new EntityAnimation(animInstanceName, fullAnimationRunTimeSeconds, priority);
        // this.animation.instance.resizeSubImageFrameList(entity.width, entity.height);
        this.animation.instance.resizeSubImageFrameList(64, 64);
        this.loopAnimation = false;
        animation.start(GamePanel.currentTime);
    }

    public void startAnimationLoop(String animInstanceName) { startAnimationLoop(animInstanceName, 0.0, 0); }
    public void startAnimationLoop(String animInstanceName, double fullAnimationRunTimeSeconds) { startAnimationLoop(animInstanceName, fullAnimationRunTimeSeconds, 0); }
    public void startAnimationLoop(String animInstanceName, int priority) { startAnimationLoop(animInstanceName, 0.0, priority); }
    public void startAnimationLoop(String animInstanceName, double fullAnimationRunTimeSeconds, int priority) {
        if (this.animation != null && (this.animation.instance.animationName == animInstanceName || this.animation.priority > priority)) {
            return;
        }
        this.animation = new EntityAnimation(animInstanceName, fullAnimationRunTimeSeconds, priority);
        // this.animation.instance.resizeSubImageFrameList(entity.width, entity.height);
        this.animation.instance.resizeSubImageFrameList(64, 64);
        this.loopAnimation = true;
        this.animation.start(GamePanel.currentTime);
    }

    public void stopAnimation() { stopAnimation("all"); }
    public void stopAnimation(String animInstanceName) {
        if (this.animation != null && (animation.instance.animationName == animInstanceName || animInstanceName == "all")) {
            animation.cancel();
            animation = null;
            loopAnimation = false;
        }
    }

    /**
     * This runs before the model to clear all expired animations
     */
    public void updateAnimationStatus(long currentTime) {
        if (animation == null) { return; }
        if (loopAnimation) {
            animation.animationStartTime = currentTime;
            currentAnimationFrameIndex = 0;
        } else {
            animation = null;
        }
    }

    public void updateCurrentAnimationFrame() {
        if (animation == null) { return; }
        long timeSinceStartOfAnimation = GamePanel.currentTime - animation.animationStartTime;
        this.currentAnimationFrameIndex = animation.instance.getInstanceFrame(timeSinceStartOfAnimation);
    }

    public BufferedImage getImage() { return image; }

    public BufferedImage getRenderImage() {
        if (animation != null) { // There exists an active animation
            BufferedImage animationImage = animation.instance.subImageFrames.get(this.currentAnimationFrameIndex);
            if (animationImage == null) { // The animation has not ended
                System.out.println("FATAL ERROR: getRenderImage() - animation == null");
                System.exit(1);
            }
            return animationImage;
        }
        return image;
    }


    public void render(Graphics2D g2d) {
        if (image == null) return; // If no image defined, don't render
        AffineTransform affTrans = new AffineTransform();
        AffineTransform originalTransform = g2d.getTransform();
        BufferedImage imageToRender = getRenderImage();

        // Get the rounded position and orientation of the vehicle
        int roundedX = (int) Math.round(entity.position.getX());
        int roundedY = (int) Math.round(entity.position.getY());
        // angle ++;
        // if (angle >= 360) angle = 0;

        int imgWidth = imageToRender.getWidth();
        int imgHeight = imageToRender.getHeight();
        // Rotate the graphics
        // g2d.rotate(Math.round(angle), roundedX + (float) (width / 2), roundedY + (float) (height / 2));

        // Set the translation to the correct position
        affTrans.translate(roundedX - (float) (imgWidth / 2) + (float) (entity.width / 2) - entity.imageOffsetX, roundedY - (float) (imgHeight / 2) + (float) (entity.height / 2) - entity.imageOffsetY);
        // Apply the translation using the AffineTransform
        g2d.drawImage(imageToRender, affTrans, null);
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
