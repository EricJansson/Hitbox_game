import java.awt.*;

public class Hero extends Entity {
    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;

    public Color color = Color.GREEN;

    public Hero() {
        super(50f, 50f, WIDTH, HEIGHT);
    }

    public Hero(double xCor, double yCor) {
        super(xCor, yCor, WIDTH, HEIGHT);
    }


    public void boost() {
        double accBoostValue = MAX_ACCELERATION * 4;
        //if ( velocity.getX() > 0 ) {
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

}
