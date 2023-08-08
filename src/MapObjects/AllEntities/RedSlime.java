package MapObjects.AllEntities;

import Background.BackgroundPanel;
import MapObjects.Entity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class RedSlime extends Entity {
    static final String IMG_FILE_NAME = "redSlime";
    static BufferedImage img = null;
    public static final int WIDTH = 46;
    public static final int HEIGHT = 46;
    static final int IMG_OFFSET_X = 0;
    static final int IMG_OFFSET_Y = 6;
    static final int OFFSET_X = (BackgroundPanel.TILE_SIZE / 2) - (WIDTH / 2);
    static final int OFFSET_Y = (BackgroundPanel.TILE_SIZE / 2) - (HEIGHT / 2);

    public RedSlime(double xCor, double yCor) {
        super(xCor + OFFSET_X, yCor + OFFSET_Y, WIDTH, HEIGHT, IMG_FILE_NAME);
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

    public Entity copy() {
        Entity temp = new RedSlime(position.getX(), position.getY());
        temp.velocity.dir.setX(this.velocity.dir.getX()); // Might need - offsetX
        temp.velocity.dir.setY(this.velocity.dir.getY()); // Might need - offsetY
        temp.dir = this.dir;
        return temp;
    }
}
