import java.awt.*;
import java.awt.geom.AffineTransform;

public class Entity {
    public Vector position;
    public Vector velocity;
    public Vector controlVector;
    public Color color = Color.GREEN;
    GameMatrix hitbox;
    int width, height, borderSize, inset;
    final double ACCELERATION_CONST = 2.4;
    final double MAX_ACCELERATION = 20.0;
    final double BRAKE_CONST = ACCELERATION_CONST / 2f;
    public final boolean HARDWALL = false;

    public Entity() {this(50f, 50f, 50, 50);}
    public Entity(double xCor, double yCor) {this(xCor, yCor, 50, 50);}
    public Entity(double xCor, double yCor, int width, int height) {
        position = new Vector(xCor, yCor);
        velocity = new Vector(0.0, 0.0);
        this.width = width;
        this.height = height;
        hitbox = new GameMatrix(position.getX(), position.getX() + this.width, position.getY(), position.getY() + height);
        borderSize = 16;
        inset = borderSize / 2;
    }

    public double calcSlowdown(double vel) {
        // Maybe do something less linear as a slowdown formula
        if (Math.abs(vel) > MAX_ACCELERATION) {
            return BRAKE_CONST * 4;
        }
        return BRAKE_CONST;
    }

    public Vector getSlowdownVector() {
        Vector vector = new Vector(0,0);
        if (velocity.getX() >= BRAKE_CONST) {
            vector.setX(-calcSlowdown(velocity.getX()));
        } else if (velocity.getX() <= -BRAKE_CONST) {
            vector.setX(calcSlowdown(velocity.getX()));
        }
        if (velocity.getY() >= BRAKE_CONST) {
            vector.setY(-calcSlowdown(velocity.getY()));
        } else if (velocity.getY() <= -BRAKE_CONST) {
            vector.setY(calcSlowdown(velocity.getY()));
        }
        return vector;
    }


    public void calcVelocity() {
        velocity = velocity.add(controlVector);
        if (velocity.getY() < BRAKE_CONST && velocity.getY() > -BRAKE_CONST) {  // if speed is VERY low => set to 0
            velocity.setY(0);
        }
        if (velocity.getX() < BRAKE_CONST && velocity.getX() > -BRAKE_CONST) {
            velocity.setX(0);
        }
    }

    public void move() {
        controlVector = getSlowdownVector();
        calcVelocity();
        position = position.add(velocity);
        if (GameModel.checkAllCollisions(this)) {
            color = Color.RED;
        } else {
            color = Color.GREEN;
        }
        hitbox.updateMatrix(position.getX(), position.getX() + width, position.getY(), position.getY() + height);
        controlVector = new Vector(0,0);
    }

    public void update() {
        move();
        fieldBoundaryCheck(new Vector(0,0), new Vector(GameWindow.width, GameWindow.height));
    }

    public void accelerate(char dir) {
        // Since slowdown always run, this won't force it to adapt to acceleration
        double acc = ACCELERATION_CONST + BRAKE_CONST;
        if (dir == 'W' || dir == 'E') {
            if ( (Math.abs(velocity.getX()) + acc) > MAX_ACCELERATION ) {
                acc = MAX_ACCELERATION + BRAKE_CONST - Math.abs(velocity.getX());
            }
        } else if (dir == 'N' || dir == 'S') {
            if ( Math.abs(velocity.getY()) > MAX_ACCELERATION ) {
                acc = MAX_ACCELERATION + BRAKE_CONST - Math.abs(velocity.getY());
            }
        } else {
            System.out.println("Fatal ERROR: accelerate");
            System.exit(0);
        }
        if (acc < 0) {  // when boosting this function will try to compensate to negative acceleration
            acc = 0;
        }
        changeSpeed(dir, acc);
    }


    public void changeSpeed(char direction, double speed) {
        Vector newSpeed = new Vector(0.0, 0.0);
        switch (direction) {
            case 'W' -> newSpeed.setX(-speed);
            case 'E' -> newSpeed.setX(speed);
            case 'N' -> newSpeed.setY(-speed);
            case 'S' -> newSpeed.setY(speed);
            default -> {
                System.out.println("Fatal ERROR: changeSpeed");
                System.exit(0);
            }
        }
        velocity = velocity.add(newSpeed);
    }
    public void setSpeed(char direction, double speed) {
        switch (direction) {
            case 'W' -> velocity.setX(-speed);
            case 'E' -> velocity.setX(speed);
            case 'N' -> velocity.setY(-speed);
            case 'S' -> velocity.setY(speed);
            default -> {
                System.out.println("Fatal ERROR: changeSpeed");
                System.exit(0);
            }
        }
    }

    public void wallLoop(Vector minVector, Vector maxVector) {
        double xSign = Math.signum(velocity.getX());
        double ySign = Math.signum(velocity.getY());
        if (position.getX() > maxVector.getX() && xSign >= 0) {
            position.setX(minVector.getX() - width);
        }
        if (position.getY() > maxVector.getY() && ySign >= 0) {
            position.setY(minVector.getY() - height);
        }
        if ( (position.getX() + width) < minVector.getX() && xSign <= 0) {
            position.setX(maxVector.getX());
        }
        if ( (position.getY() + height) < minVector.getY() && ySign <= 0) {
            position.setY(maxVector.getY());
        }
    }
    public void wallBoundary(Vector minVector, Vector maxVector) {
        if ((position.getX() + width) > maxVector.getX()) {
            position.setX(maxVector.getX() - width);
            velocity.setX(0);
        }
        if ((position.getY() + height) > maxVector.getY()) {
            position.setY(maxVector.getY() - height);
            velocity.setY(0);
        }
        if (position.getX() < minVector.getX()) {
            position.setX(minVector.getX());
            velocity.setX(0);
        }
        if (position.getY() < minVector.getY()) {
            position.setY(minVector.getY());
            velocity.setY(0);
        }
    }
    public void fieldBoundaryCheck(Vector minVector, Vector maxVector) {
        if (HARDWALL) {
            wallBoundary(minVector, maxVector);
        } else {
            wallLoop(minVector, maxVector);
        }
    }

    public void printPos() {
        String direction = "";
        System.out.printf("X: %.1f, Y: %.1f", position.getX(), position.getY());
        if (velocity.getY() > 0) {
            direction += "South";
        } else if (velocity.getY() < 0) {
            direction += "North";
        }
        if (velocity.getX() > 0) {
            direction += "East";
        } else if (velocity.getX() < 0) {
            direction += "West";
        }
        if (direction.equals("")) {
            direction = "None";
        }
        System.out.print(", Movement direction: " + direction);
        System.out.printf(", Velocity Horizontal: %.1f, Vertical: %.1f\n", velocity.getX(), velocity.getY());
        // System.out.println(", Velocity Horizontal: " + velocity.getX() + ", Vertical: " + velocity.getY());
    }

    public void render(Graphics2D g2d) {
        AffineTransform affTrans = new AffineTransform();
        AffineTransform originalTransform = g2d.getTransform();

        // Get the rounded position and orientation of the vehicle
        // int roundedX = Math.round(vehicle.getX());
        // int roundedY = Math.round(vehicle.getY());
        // float angle = vehicle.getCurrentAngle();

        // Rotate the graphics
        // g2d.rotate(angle, roundedX, roundedY);

        // Set the translation to the rounded position
        // affTrans.translate(roundedX - vehicle.getWidth() / 2, roundedY - vehicle.getLength() / 2);

        // Apply the translation using the AffineTransform
        // g2d.drawImage(vehicle.getImage(), affTrans, null);
        g2d.setColor(color);

        g2d.setStroke(new BasicStroke(borderSize));
        g2d.drawRect((int) position.getX() + inset, (int) position.getY() + inset, width - borderSize, height - borderSize);

        g2d.setTransform(originalTransform);
    }
}
