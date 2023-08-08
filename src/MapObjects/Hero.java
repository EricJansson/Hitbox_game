package MapObjects;

import Background.BackgroundPanel;
import Utils.WindowKeyListener;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import DataFormats.*;

import javax.imageio.ImageIO;

import static Background.BackgroundPanel.TILE_SIZE;

public class Hero extends Entity {
    static final String IMG_FILE_NAME = "slime";
    static BufferedImage img = null;
    public static final int WIDTH = 46;
    public static final int HEIGHT = 46;
    static final int IMG_OFFSET_X = 0;
    static final int IMG_OFFSET_Y = 6;
    static final int OFFSET_X = (BackgroundPanel.TILE_SIZE / 2) - (WIDTH / 2);
    static final int OFFSET_Y = (BackgroundPanel.TILE_SIZE / 2) - (HEIGHT / 2);


    public Color color = Color.GREEN;

    public Hero() {
        this(64f, 64f);
    }

    public Hero(double xCor, double yCor) {
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


    /**
     * Will increase speed above maxSpeed. Because you can't accelerate when curSpeed > maxSpeed,
     * that means it will slowly decrease the curSpeed over a short period of time.
     */
    public void boost() {
        double accBoostValue = max_acc * 4;
        if (WindowKeyListener.d && !WindowKeyListener.a) {
            setSpeed('E', accBoostValue);
        } else if (WindowKeyListener.a && !WindowKeyListener.d) {
            setSpeed('W', accBoostValue);
        }
        if (WindowKeyListener.s && !WindowKeyListener.w) {
            setSpeed('S', accBoostValue);
        } else if (WindowKeyListener.w && !WindowKeyListener.s) {
            setSpeed('N', accBoostValue);
        }
    }
    public void singleDirectionBoost() {
        double accBoostValue = max_acc * 4;
        //if ( velocity.getX() > 0 ) {
        if (WindowKeyListener.d && !(WindowKeyListener.a || WindowKeyListener.s || WindowKeyListener.w)) {
            setSpeed('E', accBoostValue);
        } else if (WindowKeyListener.a && !(WindowKeyListener.w || WindowKeyListener.s || WindowKeyListener.d)) {
            setSpeed('W', accBoostValue);
        } else if (WindowKeyListener.s && !(WindowKeyListener.w || WindowKeyListener.a || WindowKeyListener.d)) {
            setSpeed('S', accBoostValue);
        } else if (WindowKeyListener.w && !(WindowKeyListener.a || WindowKeyListener.s || WindowKeyListener.d)) {
            setSpeed('N', accBoostValue);
        }
    }

    public void getCurrentCoordinates() {
        System.out.printf("X: %.1f, Y: %.1f", position.getX(), position.getY());
        System.out.print(", Movement direction: " + dir);
        System.out.printf(", Velocity Horizontal: %.1f, Vertical: %.1f\n", velocity.dir.getX(), velocity.dir.getY());
        System.out.println("X-index: " + (int) (position.getX() / TILE_SIZE) + "   Y-index: " + (int) (position.getY() / TILE_SIZE));
    }

}
