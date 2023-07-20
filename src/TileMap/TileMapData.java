package TileMap;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TileMapData {
    public int tileshigh;
    public int tileheight;
    public int tileswide;
    public int tilewidth;
    public ArrayList<TileMapLayer> layers;

    public TileMapData() {
        layers = new ArrayList<>();
    }

    // Method to read JSON data and parse it into TileMap.TileMapData
    public static TileMapData readTileMapData(String fileName) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            TileMapData tileMapData = objectMapper.readValue(new File(".\\src\\assets\\" + fileName + ".json"), TileMapData.class);
            System.out.println();
            return tileMapData;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("TileMapData - readTileMapData: ERROR");
            return null; // or throw a custom exception or handle the error accordingly
        }
    }

    public ArrayList<TileMapTile> getTiles() {
        return layers.get(0).tiles;
    }

    // print() method for debugging purposes
    public String print() {
        String msg = "TileMap.TileMapData{" +
                "tileshigh=" + tileshigh +
                ", tileheight=" + tileheight +
                ", tileswide=" + tileswide +
                ", tilewidth=" + tilewidth +
                '}';
        System.out.println(msg);
        return msg;
    }

    public void printTiles() {
        for (int i= 0; i < layers.get(0).tiles.size(); i++) {
            layers.get(0).tiles.get(i).print();
            if (i > 3) {
                System.out.println("Force stop printing Json-tiles...");
                break;
            }
        }
    }



}
