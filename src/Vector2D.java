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

}
