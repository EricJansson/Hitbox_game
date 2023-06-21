import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Objects;

public class Entity {
    public Vector position, velocity, controlVector;
    int width, height, borderSize, inset;
    final double ACCELERATION_CONST = 2.0;
    final double BRAKE_CONST = ACCELERATION_CONST / 3f;
    final double MAX_ACCELERATION = 28.0;
    public Entity() {
        position = new Vector(100.0, 100.0);
        velocity = new Vector(0.0, 0.0);
        width = 50;
        height = 50;
        borderSize = 6;
        inset = borderSize / 2;
    }

    public void calcSlowdown() {
        if (velocity.getX() >= BRAKE_CONST) {
            controlVector.setX(-BRAKE_CONST);
        } else if (velocity.getX() <= -BRAKE_CONST) {
            controlVector.setX(BRAKE_CONST);
        }
        if (velocity.getY() >= BRAKE_CONST) {
            controlVector.setY(-BRAKE_CONST);
        } else if (velocity.getY() <= -BRAKE_CONST) {
            controlVector.setY(BRAKE_CONST);
        }
    }

    public void calcVelocity() {
        velocity = velocity.add(controlVector);
        if (velocity.getY() < BRAKE_CONST && velocity.getY() > -BRAKE_CONST) {
            velocity.setY(0);
        }
        if (velocity.getX() < BRAKE_CONST && velocity.getX() > -BRAKE_CONST) {
            velocity.setX(0);
        }
    }

    public void move() {
        controlVector = new Vector(0,0);
        calcSlowdown();
        calcVelocity();
        position = position.add(velocity);
    }

    public void update() {
        move();
        boolean legalPosition = fieldBoundaryCheck(new Vector(0,0), new Vector(GameWindow.width, GameWindow.height));
        if (!legalPosition) {
            keepWithinBoundary(new Vector(0,0), new Vector(GameWindow.width, GameWindow.height));
        }
    }

    public void accelerate(char dir) {
        double acc = ACCELERATION_CONST;
        switch (dir) {
            case 'W':
                if ( ((velocity.getX() * -1) + acc) > MAX_ACCELERATION ) {
                    acc = 0;
                }
                break;
            case 'E':
                if ( (velocity.getX() + acc) > MAX_ACCELERATION ) {
                    acc = 0;
                }
                break;
            case 'N':
                if ( ((velocity.getY() * -1) + acc) > MAX_ACCELERATION ) {
                    acc = 0;
                }
                break;
            case 'S':
                if ( (velocity.getY() + acc) > MAX_ACCELERATION ) {
                    acc = 0;
                }
                break;
            default:
                System.out.println("Fatal ERROR: accelerate");
                System.exit(0);
        }
        changeSpeed(dir, acc);
    }


    public void changeSpeed(char direction, double speed) {
        Vector newSpeed = new Vector(0.0, 0.0);
        switch (direction) {
            case 'W' -> newSpeed.setX(-1f * speed);
            case 'E' -> newSpeed.setX(speed);
            case 'N' -> newSpeed.setY(-1f * speed);
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
            case 'W' -> velocity.setX(-1f * speed);
            case 'E' -> velocity.setX(speed);
            case 'N' -> velocity.setY(-1f * speed);
            case 'S' -> velocity.setY(speed);
            default -> {
                System.out.println("Fatal ERROR: changeSpeed");
                System.exit(0);
            }
        }
    }
    public void keepWithinBoundary(Vector minVector, Vector maxVector) {
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
    public boolean fieldBoundaryCheck(Vector minVector, Vector maxVector) {
        if ((position.getX() + width) > maxVector.getX()) {
            System.out.println("Too far RIGHT.");
            return false;
        } else if ((position.getY() + height) > maxVector.getY()) {
            System.out.println("Too far DOWN.");
            return false;
        } else if (position.getX() < minVector.getX()) {
            System.out.println("Too far LEFT.");
            return false;
        } else if (position.getY() < minVector.getY()) {
            System.out.println("Too far UP.");
            return false;
        }
        return true;
    }

    public void printPos() {
        String direction = "";
        System.out.print("X: " + position.getX() + ", Y: " + position.getY());
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
        System.out.println(", Velocity Horizontal: " + velocity.getX() + ", Vertical: " + velocity.getY());
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
        g2d.setColor(Color.RED);

        g2d.setStroke(new BasicStroke(borderSize));
        g2d.drawRect((int) position.getX() + inset, (int) position.getY() + inset, width - borderSize, height - borderSize);

        g2d.setTransform(originalTransform);
    }
}
