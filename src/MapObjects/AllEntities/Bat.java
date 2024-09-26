package MapObjects.AllEntities;

import Background.BackgroundPanel;
import MapObjects.Entity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Bat extends Entity {
    static final String IMG_FILE_NAME = "bat";
    static BufferedImage img = null;
    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;
    static final int IMG_OFFSET_X = 0;
    static final int IMG_OFFSET_Y = 0;

    static final int offsetX = (BackgroundPanel.TILE_SIZE / 2) - (WIDTH / 2);
    static final int offsetY = (BackgroundPanel.TILE_SIZE / 2) - (HEIGHT / 2);

    public Bat(double xCor, double yCor) {
        super(xCor + offsetX, yCor + offsetY, WIDTH, HEIGHT, IMG_FILE_NAME);
        imageOffsetX = IMG_OFFSET_X;
        imageOffsetY = IMG_OFFSET_Y;
        if(image == null) {
            try {
                image = ImageIO.read(new File(".\\src\\assets\\EntityImages\\" + IMG_FILE_NAME + ".png"));
            } catch (Exception e) {
                System.out.println("Entity() init Error!");
                e.printStackTrace();
            }
        }
        img = image;
        setImage(img);
    }

    public void move() {
        super.move();
        // Always run animation
        renderer.startAnimationLoop("batFlyingUpDown", 1);
    }

    public Entity copy() {
        Entity temp = new Bat(position.getX(), position.getY());
        temp.velocity.dir.setX(this.velocity.dir.getX()); // Might need - offsetX
        temp.velocity.dir.setY(this.velocity.dir.getY()); // Might need - offsetY
        temp.dir = this.dir;
        return temp;
    }

}