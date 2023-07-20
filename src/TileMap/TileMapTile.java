package TileMap;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TileMapTile {
    public int tile;
    @JsonIgnore
    public int rot = 0;
    public int y;
    @JsonIgnore
    public boolean flipX = false;
    public int index;
    public int x;

    // Constructor
    public TileMapTile() {}
    public TileMapTile(int tile, int y, int index, int x) {
        this.tile = tile;
        this.rot = 0;
        this.y = y;
        this.flipX = false;
        this.index = index;
        this.x = x;
    }

    // print() method for debugging purposes
    public String print() {
        String msg = "Tile{" +
                "tile=" + tile +
                ", rot=" + rot +
                ", y=" + y +
                ", flipX=" + flipX +
                ", index=" + index +
                ", x=" + x +
                '}';
        System.out.println(msg);
        return msg;
    }
}
