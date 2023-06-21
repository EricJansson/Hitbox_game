import java.awt.*;
import java.util.ArrayList;

public class GameModel {
    static ArrayList<Entity> allEntities = new ArrayList<>();
    public GameModel() {
        Entity hero = new Entity();
        allEntities.add(hero);
        GamePanel.hero = hero;
    }

    public void updateEntities() {
        for (int i = 0; i< GameModel.allEntities.size(); i++) {
            GameModel.allEntities.get(i).update();
        }
        // GamePanel.hero.printPos();
    }

    public void createEntity() {

    }

    public void addEntity() {

    }

}
