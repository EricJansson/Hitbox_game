
public class CameraView {
    public static Vector position = new Vector(0,0);
    public static Entity target = null;
    public static void updateCam() {
        if (target != null) {
            followTarget();
            moveCam((int) position.getX(), (int) position.getY());
        }
    }
    public static void moveCam(int x, int y) {
        BackgroundPanel.setBackgroundPos(new Vector(x, y));
    }

    public static void followTarget() {
        position.setX((double) (GameWindow.width/2) - (target.position.getX() + (double) target.getImage().getWidth() / 2));
        position.setY((double) (GameWindow.height/2) - (target.position.getY() + (double) target.getImage().getHeight() / 2));
    }

    public static void setTarget(Entity newTarget) { target = newTarget; }
}
