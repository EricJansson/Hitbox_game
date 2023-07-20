import java.util.ArrayList;

public class GameModel {
    double HERO_X_COR = 516;
    double HERO_Y_COR = 320;
    static ArrayList<Entity> allEntities = new ArrayList<>();
    public GameModel() {
        // createEntity(100.0, 150.0, 50, 50);
        // createEntity(700f, 300f, 50, 50);
        createEntity(1, 3, 50, 50);
        createEntity(12, 7, 50, 50);
        createEntity(9, 13, 50, 50);
        createEntity(22, 10, 50, 50);
        createHero(HERO_X_COR, HERO_Y_COR); // 800 / 400
        CameraView.setTarget(GamePanel.hero);
    }

    public void updateEntities() {
        for (int i = 0; i< GameModel.allEntities.size(); i++) {
            GameModel.allEntities.get(i).update();
        }
        GamePanel.hero.printPos();
    }

    public static boolean checkAllCollisions(Entity mainEntity) {
        for (Entity entity : allEntities ) {
            if (entity == mainEntity) {
                continue;
            }
            if (mainEntity.hitbox.collisionCheck(entity.hitbox)) {
                System.out.println("Collision detected!!");
                return true;
            }
        }
        return false;
    }

    public void createHero(double xCor, double yCor) {
        Hero hero = new Hero(xCor, yCor);
        allEntities.add(hero);
        GamePanel.hero = hero;
    }

    public Entity createEntity(Vector coordinates, int width, int height) { return createEntity(coordinates.getX(), coordinates.getY(), width, height); }

    public Entity createEntity(double xCor, double yCor, int width, int height) {
        Entity entity = new Entity(xCor, yCor, width, height);
        allEntities.add(entity);
        return entity;
    }

    public Entity createEntity(int xIndex, int yIndex, int width, int height) {
        int offsetX = (BackgroundPanel.TILE_SIZE / 2) - (width / 2);
        int offsetY = (BackgroundPanel.TILE_SIZE / 2) - (height / 2);
        int x = (BackgroundPanel.TILE_SIZE * xIndex) + offsetX;
        int y = (BackgroundPanel.TILE_SIZE * yIndex) + offsetY;;
        Entity entity = new Entity(x, y, width, height);
        allEntities.add(entity);
        return entity;
    }

    public void addEntity() {

    }

}
