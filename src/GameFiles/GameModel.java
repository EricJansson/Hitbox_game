package GameFiles;

import java.util.ArrayList;

import Background.*;
import DataFormats.*;
import Enums.MovementType;
import MapObjects.*;
import MapObjects.AllEntities.*;

public class GameModel {
    double HERO_X_COR = 516;
    double HERO_Y_COR = 320;
    public static ArrayList<Entity> allEntities = new ArrayList<>();
    public static ArrayList<Obstacle> allObstacles = new ArrayList<>();
    public GameModel() {
        addEntity(1, 5, "RedSlime");
        addEntity(10, 28, "RedSlime");
        addEntity(11, 3, "RedSlime");
        addEntity(12, 10, "RedSlime");
        addEntity(22, 23, "Bat");
        addEntity(33, 15, "Bat");
        addEntity(22, 7, "RedSlime");
        addEntity(31, 5,"RedSlime");
        createHero(24, 10);

        CameraView.setTarget(GamePanel.hero);
    }


    public void updateEntities() {
        for (int i = 0; i< GameModel.allEntities.size(); i++) {
            GameModel.allEntities.get(i).update();
        }
        // GameFiles.GamePanel.hero.printPos();
    }

    public static double getCollidingArea(Entity entity, Obstacle obst) {
        double deltaX = GameMatrix.getMatrixCollisionDeltaX(entity, obst.hitbox);
        double deltaY = GameMatrix.getMatrixCollisionDeltaY(entity, obst.hitbox);
        return deltaX * deltaY;
    }

    /**
     * Desc: Adds obstacle in the correct order
     * in the list based on the collision area
     */
    public static void addToSortedArrayList(ArrayList<Obstacle> list, Entity entity, Obstacle obst) {
        double obstToAddArea = getCollidingArea(entity, obst);
        for (int i = 0; i < list.size(); i++) {
            double listItemArea = getCollidingArea(entity, list.get(i));
            if (obstToAddArea < listItemArea) {
                list.add(i, obst);
                return;
            }
        }
        list.add(obst);
    }

    /** Desc: Creates an ArrayList with obstacles in area sorted order
     * (largest collision area last) */
    public static ArrayList<Obstacle> createCollisionArrayList(Entity entity) {
        ArrayList<Obstacle> obstList = new ArrayList<>();
        for (Obstacle obst : allObstacles ) {
            if (entity.hitbox.collisionCheck(obst.hitbox)) {
                addToSortedArrayList(obstList, entity, obst);
            }
        }
        return obstList;
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

    public void createHero(int xIndex, int yIndex) {
        double x = (BackgroundPanel.TILE_SIZE * xIndex);
        double y = (BackgroundPanel.TILE_SIZE * yIndex);
        createHero(x, y);
    }


    public Entity createEntity(Vector coordinates, String name) {
        return createEntity(coordinates.getX(), coordinates.getY(), name);
    }
    public static Entity createEntity(double xCor, double yCor, String name) {
        Entity newEntity;
        if ("Bat".equalsIgnoreCase(name)) {
            newEntity = new Bat(xCor ,yCor);
        } else if ("RedSlime".equalsIgnoreCase(name)) {
            newEntity = new RedSlime(xCor, yCor);
        } else if ("MapObjects.Hero".equalsIgnoreCase(name)) {
            newEntity = new Hero(xCor, yCor);
        } else {
            throw new IllegalArgumentException("Invalid entity type: " + name);
        }
        return newEntity;
    }

    public void addEntity(Vector coordinates, String name) {
        addEntity(coordinates.getX(), coordinates.getY(), name);
    }
    public void addEntity(int xIndex, int yIndex, String name) {
        double x = (BackgroundPanel.TILE_SIZE * xIndex);
        double y = (BackgroundPanel.TILE_SIZE * yIndex);
        addEntity(x, y, name);
    }
    public void addEntity(double xCor, double yCor, String name) {
        Entity newEntity = createEntity(xCor, yCor, name);
        allEntities.add(newEntity);
    }


    public static Obstacle createObstacle(MovementType[] movingType, GameMatrix matrix) {
        // getYmin() and getYmax() are confusing and needs a refactor.
        // Is it min/max from a coordinate or drawing perspective?
        return createObstacle(movingType, matrix.getXmin(), matrix.getXmax(), matrix.getYmin(), matrix.getYmax());
    }
    public static Obstacle createObstacle(MovementType[] movingType, double xLeft, double xRight, double yDown, double yTop) {
        return new Obstacle(movingType, xLeft, xRight, yDown, yTop);
    }

}
