package MapObjects;

import Background.BackgroundPanel;
import Enums.MovementType;
import GameFiles.GamePanel;
import Utils.WindowKeyListener;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import DataFormats.*;

import javax.imageio.ImageIO;

import static Animations.AnimationInstance.resizeImageTo;
import static Background.BackgroundPanel.TILE_SIZE;

public class Hero extends Entity {
    static final String IMG_FILE_NAME = "chicken";
    static BufferedImage img = null;
    public static final int WIDTH = 46;
    public static final int HEIGHT = 46;
    static final int IMG_OFFSET_X = 0;
    static final int IMG_OFFSET_Y = 6;
    static final int OFFSET_X = (BackgroundPanel.TILE_SIZE / 2) - (WIDTH / 2);
    static final int OFFSET_Y = (BackgroundPanel.TILE_SIZE / 2) - (HEIGHT / 2);
    public String animationName = "";
    public Color color = Color.GREEN;
    public double animationSpeed = 1.0;

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
        image = resizeImageTo(image, 60, 60);
        img = image;
        setImage(img);
    }

    @Override
    public void move() {
        super.move();
        // Run animation while moving
        double xSpeed = velocity.dir.getX();
        double ySpeed = velocity.dir.getY();


        if (this.movingType[0] == MovementType.WALKING && (xSpeed != 0 || ySpeed != 0)) {
            if (Math.abs(xSpeed) >= Math.abs(ySpeed)) {
                if (xSpeed > 0) { // Moving right
                    renderer.startAnimationLoop("chickenMoveRight", animationSpeed, 1);
                } else { // Moving left
                    renderer.startAnimationLoop("chickenMoveLeft", animationSpeed, 1);
                }
            } else if (Math.abs(xSpeed) < Math.abs(ySpeed)) {
                if (ySpeed > 0) { // Moving up
                    renderer.startAnimationLoop("chickenMoveDown", animationSpeed, 1);
                } else { // Moving down
                    renderer.startAnimationLoop("chickenMoveUp", animationSpeed, 1);
                }
            }

        } else if (this.movingType[0] == MovementType.SWIMMING && (xSpeed != 0 || ySpeed != 0)) { // If moving, run animation
            if (Math.abs(xSpeed) >= Math.abs(ySpeed)) {
                if (xSpeed > 0) { // Moving right
                    renderer.startAnimationLoop("redSlimeMoveRight", animationSpeed, 1);
                } else { // Moving left
                    renderer.startAnimationLoop("redSlimeMoveLeft", animationSpeed, 1);
                }
            } else if (Math.abs(xSpeed) < Math.abs(ySpeed)) {
                if (ySpeed > 0) { // Moving up
                    renderer.startAnimationLoop("redSlimeMoveDown", animationSpeed, 1);
                } else { // Moving down
                    renderer.startAnimationLoop("redSlimeMoveUp", animationSpeed, 1);
                }
            }


        } else if (this.movingType[0] == MovementType.FLYING) {
            if (xSpeed != 0 || ySpeed != 0) {
                renderer.startAnimationLoop("batFlyingAround", 0.5, 1);
            } else {
                renderer.startAnimationLoop("batFlyingUpDown", 2, 1);
            }
        }


        else {
            System.out.println("Stop animation");
            renderer.stopAnimation();
        }
    }

    @Override
    public void updateMovementType(MovementType[] movingType) {
        super.updateMovementType(movingType);
        if (this.movingType.length > 1) { return; }
        if (this.movingType[0] == MovementType.WALKING) {
            try {
                image = ImageIO.read(new File(".\\src\\assets\\EntityImages\\" + IMG_FILE_NAME + ".png"));
            } catch (Exception e) {
                System.out.println("Entity() init Error!");
                e.printStackTrace();
            }
            image = resizeImageTo(image, 60, 60);
            img = image;
            setImage(img);
        } else if (this.movingType[0] == MovementType.SWIMMING) {
            try {
                image = ImageIO.read(new File(".\\src\\assets\\EntityImages\\redJelly.png"));
            } catch (Exception e) {
                System.out.println("Entity() init Error!");
                e.printStackTrace();
            }
            image = resizeImageTo(image, 60, 60);
            img = image;
            setImage(img);
        } else if (this.movingType[0] == MovementType.FLYING) {

        }
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
