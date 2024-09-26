package MapObjects;

import Animations.AllAnimationData;
import Animations.AnimationInstance;
import Animations.EntityAnimation;
import Background.Field;
import Enums.Direction;
import Enums.MovementType;
import GameFiles.GameModel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import DataFormats.*;
import GameFiles.GamePanel;


public abstract class Entity {
    public MovementType[] movingType = new MovementType[]{MovementType.WALKING};
    public String IMG_FILE_NAME;
    public Vector position;
    public Vector2D velocity;
    public Vector controlVector;
    public Direction dir = Direction.NONE;
    public GameMatrix hitbox;
    public BufferedImage image = null;
    public boolean newlySpawned;
    public int width;
    public int height;
    public int imageOffsetX;
    public int imageOffsetY;
    public double acceleration_val = 2.0;
    public double max_acc = 7.0;
    public double brake_val = acceleration_val;
    public EntityRenderer renderer;

    public Entity(int width, int height) {
        renderer = new EntityRenderer(this);
        this.width = width;
        this.height = height;
        newlySpawned = true;
    }

    public Entity(double xCor, double yCor, int width, int height, String imageName) {
        position = new Vector(xCor, yCor);
        velocity = new Vector2D(0.0, 0.0, xCor, yCor);
        if(image == null && imageName != null) {
            try {
                image = ImageIO.read(new File(".\\src\\assets\\EntityImages\\" + imageName + ".png"));
            } catch (Exception e) {
                System.out.println("Entity() init Error!");
                e.printStackTrace();
            }
        }
        renderer = new EntityRenderer(this);
        this.width = width;
        this.height = height;
        hitbox = new GameMatrix(position.getX(), position.getX() + this.width, position.getY(), position.getY() + height);
        newlySpawned = true;
    }


    public double calcSlowdown(double vel) {
        // Maybe do something less linear as a slowdown formula
        if (Math.abs(vel) > max_acc + Math.abs(vel)) {
            return brake_val * 4;
        }
        return brake_val;
    }

    public Vector getSlowdownVector() {
        Vector vector = new Vector(0,0);
        if (velocity.dir.getX() >= brake_val) {
            vector.setX(-calcSlowdown(velocity.dir.getX()));
        } else if (velocity.dir.getX() <= -brake_val) {
            vector.setX(calcSlowdown(velocity.dir.getX()));
        }
        if (velocity.dir.getY() >= brake_val) {
            vector.setY(-calcSlowdown(velocity.dir.getY()));
        } else if (velocity.dir.getY() <= -brake_val) {
            vector.setY(calcSlowdown(velocity.dir.getY()));
        }
        return vector;
    }


    public void calcVelocity(Vector vector) {
        velocity.dir = velocity.dir.add(vector);
        if (velocity.dir.getY() < brake_val && velocity.dir.getY() > -brake_val) {  // if speed is VERY low => set to 0
            velocity.dir.setY(0);
        }
        if (velocity.dir.getX() < brake_val && velocity.dir.getX() > -brake_val) {
            velocity.dir.setX(0);
        }
    }

    public boolean spawnCollisionCheck(Obstacle obst) {
        if (obst != null) { // if newlySpawned && collision -> ignore collision to avoid crash
            return true;
        }
        System.out.println("No longer Spawn-Colliding.");
        // if newlySpawned && no collision -> make it collide with obstacles like normal
        newlySpawned = false;
        return true;
    }

    public void obstacleCollisionCheck() {
        double deltaX, deltaY;
        int counter = 0;
        Entity tempEntity = this.copy();
        Vector position = this.position.add(velocity.dir);
        tempEntity.hitbox.updateMatrix(position.getX(), position.getX() + width, position.getY(), position.getY() + height);

        // ArrayList<Obstacle> allObstacles = new ArrayList<>();
        ArrayList<Obstacle> allObstacles = GameModel.createCollisionArrayList(tempEntity);
        Obstacle collidingObst = Obstacle.getObstacleWithLargestCollision(allObstacles);



        if (newlySpawned && spawnCollisionCheck(collidingObst)) return; // If newlySpawned => don't adapt to collision

        // Incase of colliding with multiple obstacles, a loop is needed
        while (collidingObst != null) {
            // if matching movementType -> collision allowed (Skip calculations below)
            if (MovementType.hasMatchingTypes(collidingObst.movingType, this.movingType)) {
                System.out.println("Skip collision!");
                collidingObst = Obstacle.getObstacleWithLargestCollision(allObstacles);
                continue;
            }

            deltaX = GameMatrix.getMatrixCollisionDeltaX(tempEntity, collidingObst.hitbox);
            deltaY = GameMatrix.getMatrixCollisionDeltaY(tempEntity, collidingObst.hitbox);
            if (deltaX == 0 && deltaY == 0) {
                collidingObst = Obstacle.getObstacleWithLargestCollision(allObstacles);
                continue;
            }
            double vectorAndDeltaXRatio = Math.abs(deltaX / velocity.dir.getX());
            double vectorAndDeltaYRatio = Math.abs(deltaY / velocity.dir.getY());

            if (vectorAndDeltaXRatio == vectorAndDeltaYRatio) { // if hitting a corner...
                if (Math.abs(velocity.dir.getX()) > Math.abs(velocity.dir.getY())) { // faster horizontal speed
                    vectorAndDeltaXRatio = vectorAndDeltaYRatio + 1;
                } else if (Math.abs(velocity.dir.getX()) < Math.abs(velocity.dir.getY())) { // faster vertical speed
                    vectorAndDeltaYRatio = vectorAndDeltaXRatio + 1;
                } else { // choose random wall to adjust to
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
            } else if (vectorAndDeltaXRatio > vectorAndDeltaYRatio) {
                if (dir == Direction.SOUTHWEST || dir == Direction.SOUTH || dir == Direction.SOUTHEAST) {
                    velocity.dir.setY(velocity.dir.getY() - deltaY);
                } else if (dir == Direction.NORTHWEST || dir == Direction.NORTH || dir == Direction.NORTHEAST) {
                    velocity.dir.setY(velocity.dir.getY() + deltaY);
                }
            }
            position = this.position.add(velocity.dir);
            tempEntity = this.copy();
            tempEntity.hitbox.updateMatrix(position.getX(), position.getX() + width, position.getY(), position.getY() + height);
            collidingObst = Obstacle.getObstacleWithLargestCollision(allObstacles);
            counter++;
            if (counter > 20) {
                System.out.println("obstacleCheck() ERROR: INF loop ");
                System.exit(0);
            }
        }
    }


    public void move() {
        renderer.color = Color.GREEN; // Set to green by defualt

        controlVector = getSlowdownVector();
        calcVelocity(controlVector);
        dir = Direction.getDir(velocity.dir);
        obstacleCollisionCheck();
        position = position.add(velocity.dir);
        hitbox.updateMatrix(position.getX(), position.getX() + width, position.getY(), position.getY() + height);
        if (GameModel.checkAllEntityCollisions(this) != null) {
            renderer.color = Color.RED;
        }
        controlVector = new Vector(0,0);
    }

    public void updateMovementType(MovementType[] movingType) {
        if (MovementType.isMatching(this.movingType, movingType)) { return; }
        this.movingType = movingType.clone();
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
        double acc = acceleration_val + brake_val;
        if (dir == 'W' || dir == 'E') {
            if ( (Math.abs(velocity.dir.getX()) + acc) > max_acc ) {
                acc = max_acc + brake_val - Math.abs(velocity.dir.getX());
            }
        } else if (dir == 'N' || dir == 'S') {
            if ( (Math.abs(velocity.dir.getY()) + acc) > max_acc ) {
                acc = max_acc + brake_val - Math.abs(velocity.dir.getY());
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
        System.out.printf("X: %.1f, Y: %.1f", position.getX(), position.getY());
        System.out.print(", Movement direction: " + dir);
        System.out.printf(", Velocity Horizontal: %.1f, Vertical: %.1f\n", velocity.dir.getX(), velocity.dir.getY());
        // System.out.println(", Velocity Horizontal: " + velocity.getX() + ", Vertical: " + velocity.getY());
    }

    public Entity copy() {
        Entity temp = GameModel.createEntity(position.getX(), position.getY(), getClass().getName());
        temp.velocity.dir.setX(this.velocity.dir.getX());
        temp.velocity.dir.setY(this.velocity.dir.getY());
        temp.dir = this.dir;
        return temp;
    }

    public void setImage(BufferedImage img) { renderer.image = img; }

}
