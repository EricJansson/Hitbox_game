package GameFiles;

import java.awt.*;
import java.util.ArrayList;
import Background.*;
import DataFormats.*;
import GameObjects.*;

public class GameModel {
    double HERO_X_COR = 516;
    double HERO_Y_COR = 320;
    public static ArrayList<Entity> allEntities = new ArrayList<>();
    public static ArrayList<Obstacle> allObstacles = new ArrayList<>();
    public GameModel() {

        createEntity(1, 3, 50, 50, "slime3");
        createEntity(12, 7, 50, 50, "slime3");
        createEntity(9, 14, 50, 40, "bat");
        createEntity(22, 10, 50, 50, "slime3");
        createHero(14, 10, 50, 56);

        CameraView.setTarget(GamePanel.hero);
    }

    public static void renderAllObstacles(Graphics2D g) {
        for (Obstacle obstacle : allObstacles) {
            obstacle.drawHitbox(g);
        }
    }

    public void updateEntities() {
        for (int i = 0; i< GameModel.allEntities.size(); i++) {
            GameModel.allEntities.get(i).update();
        }
        // GameFiles.GamePanel.hero.printPos();
    }

    /** By using the obstacle with the largest collision area,
     * it will prioritize the collision adaptation to that entity first. */
    public static Obstacle getClosestCollidingObstacle(Entity entity, Obstacle obst1, Obstacle obst2) {
        Obstacle largestCollisionObstacle = obst1; // Init with obst1 for simpler return
        double deltaX1 = GameMatrix.getMatrixCollisionDeltaX(entity, obst1.hitbox);
        double deltaY1 = GameMatrix.getMatrixCollisionDeltaY(entity, obst1.hitbox);
        double area1 = deltaX1 * deltaY1;

        double deltaX2 = GameMatrix.getMatrixCollisionDeltaX(entity, obst2.hitbox);
        double deltaY2 = GameMatrix.getMatrixCollisionDeltaY(entity, obst2.hitbox);
        double area2 = deltaX2 * deltaY2;
        if (area1 < area2) {
            largestCollisionObstacle = obst2;
        }
        return largestCollisionObstacle;
    }

    /** Will find the obstacle with the largest collision area to the entity used as parameter.
     * Returns null of no collision is found. */
    public static Obstacle checkAllObstacleCollisions(Entity entity) {
        Obstacle collidingObstacle = null;
        for (Obstacle obst : allObstacles ) {
            if (entity.hitbox.collisionCheck(obst.hitbox)) {
                if (collidingObstacle != null) {
                    collidingObstacle = getClosestCollidingObstacle(entity, collidingObstacle, obst);
                } else {
                    collidingObstacle = obst;
                }
            }
        }
        return collidingObstacle;
    }


    public static Entity checkAllEntityCollisions(Entity mainEntity) {
        for (Entity entity : allEntities ) {
            if (entity == mainEntity) {
                continue;
            }
            if (mainEntity.hitbox.collisionCheck(entity.hitbox)) {
                return entity;
            }
        }
        return null;
    }

    public void createHero(double xCor, double yCor) {
        Hero hero = new Hero(xCor, yCor);
        allEntities.add(hero);
        GamePanel.hero = hero;
    }

    public void createHero(int xIndex, int yIndex) {createHero(xIndex,yIndex, Hero.WIDTH, Hero.HEIGHT);}
    public void createHero(int xIndex, int yIndex, int width, int height) {
        int offsetX = (BackgroundPanel.TILE_SIZE / 2) - (width / 2);
        int offsetY = (BackgroundPanel.TILE_SIZE / 2) - (height / 2);
        int x = (BackgroundPanel.TILE_SIZE * xIndex) + offsetX;
        int y = (BackgroundPanel.TILE_SIZE * yIndex) + offsetY;;
        Hero hero = new Hero(x, y, width, height);
        allEntities.add(hero);
        GamePanel.hero = hero;
    }

    public Entity createEntity(int xIndex, int yIndex, int width, int height) { return createEntity(xIndex, yIndex, width, height, null); }
    public Entity createEntity(Vector coordinates, int width, int height, String name) { return createEntity(coordinates.getX(), coordinates.getY(), width, height, name); }

    public Entity createEntity(double xCor, double yCor, int width, int height, String name) {
        Entity entity = new Entity(xCor, yCor, width, height, name);
        allEntities.add(entity);
        return entity;
    }

    public Entity createEntity(int xIndex, int yIndex, int width, int height, String name) {
        int offsetX = (BackgroundPanel.TILE_SIZE / 2) - (width / 2);
        int offsetY = (BackgroundPanel.TILE_SIZE / 2) - (height / 2);
        int x = (BackgroundPanel.TILE_SIZE * xIndex) + offsetX;
        int y = (BackgroundPanel.TILE_SIZE * yIndex) + offsetY;;
        Entity entity = new Entity(x, y, width, height, name);
        allEntities.add(entity);
        return entity;
    }

    public void addEntity() {

    }

    public static Obstacle createObstacle(GameMatrix matrix) {
        // getYmin() and getYmax() are confusing and needs a refactor.
        // Is it min/max from a coordinate or drawing perspective?
        return createObstacle(matrix.getXmin(), matrix.getXmax(), matrix.getYmin(), matrix.getYmax());
    }
    public static Obstacle createObstacle(double xLeft, double xRight, double yDown, double yTop) {
        Obstacle newObst = new Obstacle(xLeft, xRight, yDown, yTop);
        allObstacles.add(newObst);
        return newObst;
    }

}
