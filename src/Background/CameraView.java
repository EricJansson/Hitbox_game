package Background;

import DataFormats.*;
import GameFiles.*;
import GameObjects.*;

public class CameraView {
    public static Vector position = new Vector(0,0);
    public static Entity target = null;

    public static void updateCam() {
        if (target != null) {
            followTarget();
        }
        moveCam((int) position.getX(), (int) position.getY());
    }
    public static void moveCam(int x, int y) {
        BackgroundPanel.setBackgroundPos(new Vector(x, y));
    }

    public static void followTarget() {
        if (target != null) {
            double x = (double) (GameWindow.width /2) - (target.position.getX() + (double) target.width / 2);
            double y = (double) (GameWindow.height/2) - (target.position.getY() + (double) target.height / 2);
            setPos(x, y);
        }
    }

    public static void setTarget(Entity newTarget) { target = newTarget; }

    public static void goToTile(int xIndex, int yIndex) {
        double x = (double) (GameWindow.width/2) - (BackgroundPanel.TILE_SIZE * xIndex) - ((double) BackgroundPanel.TILE_SIZE / 2);
        double y = (double) (GameWindow.height/2) - (BackgroundPanel.TILE_SIZE * yIndex) - ((double) BackgroundPanel.TILE_SIZE / 2);
        setPos(x, y);
    }

    public static void setPos(double x, double y) {
        position.setX(x);
        position.setY(y);
    }

}
