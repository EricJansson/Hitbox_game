package GameFiles;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import Background.*;
import DataFormats.*;
import Enums.MovementType;
import GameObjects.*;

public class GameModel {
    double HERO_X_COR = 516;
    double HERO_Y_COR = 320;
    public static ArrayList<Entity> allEntities = new ArrayList<>();
    public static ArrayList<Obstacle> allObstacles = new ArrayList<>();
    public GameModel() {

        createEntity(1, 5, 50, 50, "slime3");
        createEntity(10, 28, 50, 50, "slime3");
        createEntity(11, 3, 50, 50, "slime3");
        createEntity(12, 10, 50, 50, "slime3");
        createEntity(22, 23, 50, 40, "bat");
        createEntity(33, 15, 50, 40, "bat");
        createEntity(22, 7, 50, 50, "slime3");
        createHero(24, 10, 50, 56);

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

    public static Obstacle createObstacle(MovementType[] movingType, GameMatrix matrix) {
        // getYmin() and getYmax() are confusing and needs a refactor.
        // Is it min/max from a coordinate or drawing perspective?
        return createObstacle(movingType, matrix.getXmin(), matrix.getXmax(), matrix.getYmin(), matrix.getYmax());
    }
    public static Obstacle createObstacle(MovementType[] movingType, double xLeft, double xRight, double yDown, double yTop) {
        return new Obstacle(movingType, xLeft, xRight, yDown, yTop);
    }

}
