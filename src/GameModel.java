import java.util.ArrayList;

public class GameModel {
    double HERO_X_COR = 516;
    double HERO_Y_COR = 320;
    static ArrayList<Entity> allEntities = new ArrayList<>();
    public GameModel() {
        createEntity(100, 150, 50, 50);
        createEntity(700, 300, 50, 50);
        createEntity(600, 800, 50, 50);
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

    public Entity createEntity(double xCor, double yCor, int width, int height) {
        Entity entity = new Entity(xCor, yCor, width, height);
        allEntities.add(entity);
        return entity;
    }

    public void addEntity() {

    }

}
