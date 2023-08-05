package GameObjects;

import Utils.WindowKeyListener;

import java.awt.*;
import DataFormats.*;

import static Background.BackgroundPanel.TILE_SIZE;

public class Hero extends Entity {
    static final String IMG_FILE_NAME = "slime2";
    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;

    public Color color = Color.GREEN;

    public Hero() {
        this(50f, 50f);
    }

    public Hero(double xCor, double yCor) {
        super(xCor, yCor, WIDTH, HEIGHT, IMG_FILE_NAME);
        hitbox = new GameMatrix(position.getX(), position.getX() + width, position.getY(), position.getY() + height);
    }

    public Hero(double xCor, double yCor, int width, int height) {
        super(xCor, yCor, width, height, IMG_FILE_NAME);
        hitbox = new GameMatrix(position.getX(), position.getX() + width, position.getY(), position.getY() + height);
    }

    /**
     * Will increase speed above maxSpeed. Because you can't accelerate when curSpeed > maxSpeed,
     * that means it will slowly decrease the curSpeed over a short period of time.
     */
    public void boost() {
        double accBoostValue = MAX_ACCELERATION * 4;
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
        double accBoostValue = MAX_ACCELERATION * 4;
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
