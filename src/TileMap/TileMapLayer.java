package TileMap;

import java.util.ArrayList;

public class TileMapLayer {
    public String name;
    public int number;
    public ArrayList<TileMapTile> tiles;

    public TileMapLayer() {
        tiles = new ArrayList<>();
    }
}
