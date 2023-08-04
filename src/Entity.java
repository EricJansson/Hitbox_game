import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;


public class Entity {
    public BufferedImage image = null;
    static final String IMG_FILE_NAME = "slime3";
    public Vector position;
    public Vector2D velocity;
    public Vector controlVector;
    public Direction dir = Direction.NONE;
    public Color color = Color.GREEN;
    GameMatrix hitbox;
    int width, height, borderSize, inset;
    double angle = 0.0;
    final double ACCELERATION_CONST = 2.0;
    final double MAX_ACCELERATION = 7.0;
    final double BRAKE_CONST = ACCELERATION_CONST;

    public Entity() {this(50f, 50f, 50, 50);}
    public Entity(double xCor, double yCor) {this(xCor, yCor, 50, 50, null);}
    public Entity(double xCor, double yCor, int width, int height) {this(xCor, yCor, width, height, null);}
    public Entity(double xCor, double yCor, int width, int height, String imageName) {
        position = new Vector(xCor, yCor);
        velocity = new Vector2D(0.0, 0.0, xCor, yCor);
        if(image == null && imageName != null) {
            try {
                image = ImageIO.read(new File(".\\src\\assets\\" + imageName + ".png"));
            } catch (Exception e) {
                System.out.println("Error!");
                e.printStackTrace();
            }
        }
        this.width = width;
        this.height = height;
        hitbox = new GameMatrix(position.getX(), position.getX() + this.width, position.getY(), position.getY() + height);
        borderSize = 16;
        inset = borderSize / 2;
    }

    public double calcSlowdown(double vel) {
        // Maybe do something less linear as a slowdown formula
        if (Math.abs(vel) > MAX_ACCELERATION + Math.abs(vel)) {
            return BRAKE_CONST * 4;
        }
        return BRAKE_CONST;
    }

    public Vector getSlowdownVector() {
        Vector vector = new Vector(0,0);
        if (velocity.dir.getX() >= BRAKE_CONST) {
            vector.setX(-calcSlowdown(velocity.dir.getX()));
        } else if (velocity.dir.getX() <= -BRAKE_CONST) {
            vector.setX(calcSlowdown(velocity.dir.getX()));
        }
        if (velocity.dir.getY() >= BRAKE_CONST) {
            vector.setY(-calcSlowdown(velocity.dir.getY()));
        } else if (velocity.dir.getY() <= -BRAKE_CONST) {
            vector.setY(calcSlowdown(velocity.dir.getY()));
        }
        return vector;
    }


    public void calcVelocity(Vector vector) {
        velocity.dir = velocity.dir.add(vector);
        if (velocity.dir.getY() < BRAKE_CONST && velocity.dir.getY() > -BRAKE_CONST) {  // if speed is VERY low => set to 0
            velocity.dir.setY(0);
        }
        if (velocity.dir.getX() < BRAKE_CONST && velocity.dir.getX() > -BRAKE_CONST) {
            velocity.dir.setX(0);
        }
    }


    public void obstacleCheck() {
        double deltaX, deltaY;
        int counter = 0;
        Entity tempEntity = this;
        Vector position = this.position.add(velocity.dir);
        tempEntity.hitbox.updateMatrix(position.getX(), position.getX() + width, position.getY(), position.getY() + height);

        Obstacle collidingObst = GameModel.checkAllObstacleCollisions(tempEntity);
        // Incase of colliding with multiple obstacles, a loop is needed
        while (collidingObst != null) {
            deltaX = GameMatrix.getMatrixCollisionDeltaX(this, collidingObst.hitbox);
            deltaY = GameMatrix.getMatrixCollisionDeltaY(this, collidingObst.hitbox);
            double vectorAndDeltaXRatio = Math.abs(deltaX / velocity.dir.getX());
            double vectorAndDeltaYRatio = Math.abs(deltaY / velocity.dir.getY());

            if (vectorAndDeltaXRatio == vectorAndDeltaYRatio) { // if hitting a corner...
                if (Math.abs(velocity.dir.getX()) > Math.abs(velocity.dir.getY())) { // faster horizontal speed
                    vectorAndDeltaXRatio = vectorAndDeltaYRatio + 1;
                    System.out.println("   Faster HORIZONTAL");
                } else if (Math.abs(velocity.dir.getX()) < Math.abs(velocity.dir.getY())) { // faster vertical speed
                    vectorAndDeltaYRatio = vectorAndDeltaXRatio + 1;
                    System.out.println("   Faster VERTICAL");
                } else { // choose random wall to adjust to
                    System.out.println("         RANDOM DIR (NOT GOOD)          ");
                    vectorAndDeltaXRatio = Math.random();
                    vectorAndDeltaYRatio = Math.random();
                }
            }
            // This if case will decide whether to adjust to the horizontal or vertical wall
            if (vectorAndDeltaXRatio < vectorAndDeltaYRatio) {
                if (dir == Direction.SOUTHEAST || dir == Direction.EAST || dir == Direction.NORTHEAST) {
                    velocity.dir.setX(velocity.dir.getX() - deltaX);
                } else if (dir == Direction.SOUTHWEST || dir == Direction.WEST || dir == Direction.NORTHWEST) {
                    velocity.dir.setX(velocity.dir.getX() + deltaX);
                }
            } else { // else if (vectorAndDeltaXRatio > vectorAndDeltaYRatio)
                if (dir == Direction.SOUTHWEST || dir == Direction.SOUTH || dir == Direction.SOUTHEAST) {
                    velocity.dir.setY(velocity.dir.getY() - deltaY);
                } else if (dir == Direction.NORTHWEST || dir == Direction.NORTH || dir == Direction.NORTHEAST) {
                    velocity.dir.setY(velocity.dir.getY() + deltaY);
                }
            }
            position = this.position.add(velocity.dir);
            tempEntity = this;
            tempEntity.hitbox.updateMatrix(position.getX(), position.getX() + width, position.getY(), position.getY() + height);
            collidingObst = GameModel.checkAllObstacleCollisions(tempEntity);
            counter++;
            if (counter > 10) {
                System.out.println("obstacleCheck() ERROR: INF loop ");
                System.exit(0);
            }
        }
    }


    public void move() {
        color = Color.GREEN; // Set to green by defualt

        controlVector = getSlowdownVector();
        calcVelocity(controlVector);
        dir = Direction.getDir(velocity.dir);
        obstacleCheck();
        position = position.add(velocity.dir);
        hitbox.updateMatrix(position.getX(), position.getX() + width, position.getY(), position.getY() + height);
        if (GameModel.checkAllEntityCollisions(this) != null) {
            color = Color.RED;
        }
        controlVector = new Vector(0,0);
    }

    public void update() {
        move();
        updateVelocityPos();
        fieldBoundaryCheck(new Vector(0,0), new Vector(Field.width, Field.height));
    }

    public void updateVelocityPos() {
        velocity.pos.setX(position.getX() + (width / 2f));
        velocity.pos.setY(position.getY() + (height / 2f));
    }

    public void accelerate(char dir) {
        // Since slowdown always run, this won't force it to adapt to acceleration
        double acc = ACCELERATION_CONST + BRAKE_CONST;
        if (dir == 'W' || dir == 'E') {
            if ( (Math.abs(velocity.dir.getX()) + acc) > MAX_ACCELERATION ) {
                acc = MAX_ACCELERATION + BRAKE_CONST - Math.abs(velocity.dir.getX());
            }
        } else if (dir == 'N' || dir == 'S') {
            if ( (Math.abs(velocity.dir.getY()) + acc) > MAX_ACCELERATION ) {
                acc = MAX_ACCELERATION + BRAKE_CONST - Math.abs(velocity.dir.getY());
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
        velocity.dir = velocity.dir.add(newSpeed);
    }
    public void setSpeed(char direction, double speed) {
        switch (direction) {
            case 'W' -> velocity.dir.setX(-speed);
            case 'E' -> velocity.dir.setX(speed);
            case 'N' -> velocity.dir.setY(-speed);
            case 'S' -> velocity.dir.setY(speed);
            default -> {
                System.out.println("Fatal ERROR: changeSpeed");
                System.exit(0);
            }
        }
    }

    public void wallLoop(Vector minVector, Vector maxVector) {
        double xSign = Math.signum(velocity.dir.getX());
        double ySign = Math.signum(velocity.dir.getY());
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
            velocity.dir.setX(0);
        }
        if ((position.getY() + height) > maxVector.getY()) {
            position.setY(maxVector.getY() - height);
            velocity.dir.setY(0);
        }
        if (position.getX() < minVector.getX()) {
            position.setX(minVector.getX());
            velocity.dir.setX(0);
        }
        if (position.getY() < minVector.getY()) {
            position.setY(minVector.getY());
            velocity.dir.setY(0);
        }
    }
    public void fieldBoundaryCheck(Vector minVector, Vector maxVector) {
        if (Field.HARDWALL) {
            wallBoundary(minVector, maxVector);
        } else {
            wallLoop(minVector, maxVector);
        }
    }

    public void printPos() {
        String direction = "";
        System.out.printf("X: %.1f, Y: %.1f", position.getX(), position.getY());
        if (velocity.dir.getY() > 0) {
            direction += "South";
        } else if (velocity.dir.getY() < 0) {
            direction += "North";
        }
        if (velocity.dir.getX() > 0) {
            direction += "East";
        } else if (velocity.dir.getX() < 0) {
            direction += "West";
        }
        if (direction.equals("")) {
            direction = "None";
        }
        System.out.print(", Movement direction: " + direction);
        System.out.printf(", Velocity Horizontal: %.1f, Vertical: %.1f\n", velocity.dir.getX(), velocity.dir.getY());
        // System.out.println(", Velocity Horizontal: " + velocity.getX() + ", Vertical: " + velocity.getY());
    }

    public void render(Graphics2D g2d) {
        if (image == null) return; // If no image defined, don't render
        AffineTransform affTrans = new AffineTransform();
        AffineTransform originalTransform = g2d.getTransform();

        // Get the rounded position and orientation of the vehicle
        int roundedX = (int) Math.round(position.getX());
        int roundedY = (int) Math.round(position.getY());
        // angle ++;
        // if (angle >= 360) angle = 0;

        int imgWidth = getImage().getWidth();
        int imgHeight = getImage().getHeight();
        // Rotate the graphics
        // g2d.rotate(Math.round(angle), roundedX + (float) (width / 2), roundedY + (float) (height / 2));

        // Set the translation to the correct position
        affTrans.translate(roundedX - (float) (imgWidth / 2) + (float) (width / 2), roundedY - (float) (imgHeight / 2) + (float) (height / 2));
        // Apply the translation using the AffineTransform
        g2d.drawImage(getImage(), affTrans, null);
        g2d.setTransform(originalTransform);

    }


    public void renderVelocity(Graphics2D g2d) {
        // Set color and thickness of the arrow
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(6));

        int arrowSize = 20;  // Size of the arrowhead
        int arrowAngle = 30; // Angle of the arrowhead

        int arrowLength = (int) Math.sqrt(velocity.dir.getX() * velocity.dir.getX() + velocity.dir.getY() * velocity.dir.getY());
        int arrowHeadX = (int) velocity.pos.getX() + (int) velocity.dir.getX() * 4;
        int arrowHeadY = (int) velocity.pos.getY() + (int) velocity.dir.getY() * 4;

        if (velocity.dir.getX() == 0 && velocity.dir.getY() == 0) {
            return;
        }
        g2d.drawLine((int) velocity.pos.getX(), (int) velocity.pos.getY(), arrowHeadX, arrowHeadY);

        // Calculate and draw the arrowhead
        double angle = Math.atan2((int) velocity.dir.getY(), (int) velocity.dir.getX());
        int arrowEndX1 = (int) (arrowHeadX - arrowSize * Math.cos(angle + Math.toRadians(arrowAngle)));
        int arrowEndY1 = (int) (arrowHeadY - arrowSize * Math.sin(angle + Math.toRadians(arrowAngle)));
        int arrowEndX2 = (int) (arrowHeadX - arrowSize * Math.cos(angle - Math.toRadians(arrowAngle)));
        int arrowEndY2 = (int) (arrowHeadY - arrowSize * Math.sin(angle - Math.toRadians(arrowAngle)));

        g2d.drawLine(arrowHeadX, arrowHeadY, arrowEndX1, arrowEndY1);
        g2d.drawLine(arrowHeadX, arrowHeadY, arrowEndX2, arrowEndY2);
    }


    public BufferedImage getImage() { return image; }

    public void drawHitbox(Graphics2D g2d) {
        hitbox.drawMatrix(g2d, color);
        g2d.setColor(Color.DARK_GRAY);
        g2d.setStroke(new BasicStroke(6));
        g2d.drawRect((int) position.getX() + inset, (int) position.getY() + inset, width - borderSize, height - borderSize);
    }
}
