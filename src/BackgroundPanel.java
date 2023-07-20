import TileMap.TileMapData;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class BackgroundPanel {
    static final int TILE_SIZE = 64;
    static public TileMapData tileMapData = null;
    static public BufferedImage fullMap;
    static public BufferedImage imgObstacle = null;
    static public BufferedImage imgBackground = null;
    static public BufferedImage imgDecoration = null;
    static final String FILE_NAME_BACKGROUND = "Layer_1_Background";
    static final String FILE_NAME_OBSTACLE = "Layer_2_Obstacle";
    static final String FILE_NAME_DECORATION = "Layer_3_Decoration";
    static public Vector position;
    static public int height;
    static public int width;

    BackgroundPanel() {
        position = new Vector(0,0);
        imgBackground = setImage(FILE_NAME_BACKGROUND);
        imgObstacle = setImage(FILE_NAME_OBSTACLE);
        imgDecoration = setImage(FILE_NAME_DECORATION);
        tileMapData = new TileMapData();
        tileMapData = TileMapData.readTileMapData("tileset");
        assert tileMapData != null;
        loadBackgroundMap(tileMapData, BackgroundMap.map);
        tileMapData = TileMapData.readTileMapData("obstacles");
        assert tileMapData != null;
        loadBackgroundMap(tileMapData, BackgroundMap.obstacles);
        tileMapData = TileMapData.readTileMapData("decoration");
        assert tileMapData != null;
        loadBackgroundMap(tileMapData, BackgroundMap.decoration);
        height = tileMapData.tileshigh * TILE_SIZE;
        width = tileMapData.tileswide * TILE_SIZE;

        fullMap = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        paintMap(fullMap.createGraphics());
    }


    public void render(Graphics2D g2d) {
        AffineTransform transform = g2d.getTransform();
        transform.translate(position.getX(), position.getY());
        // Apply the translation using the AffineTransform
        g2d.drawImage(fullMap, transform, null);
        g2d.setTransform(transform);
    }

    public BufferedImage getTile(BufferedImage img, int tileIndexX, int tileIndexY) {
        int x = tileIndexX * TILE_SIZE;
        int y = tileIndexY * TILE_SIZE;
        return img.getSubimage(x, y, TILE_SIZE, TILE_SIZE);
    }

    public void displayLayer(Graphics2D g2d, BufferedImage image, ArrayList<ArrayList<int[]>> tileSet) {
        BufferedImage tile;
        for (int yy = 0; yy < tileSet.size(); yy++) {
            for (int xx = 0; xx < tileSet.get(0).size(); xx++) {
                tile = getTile(image, tileSet.get(yy).get(xx)[0], tileSet.get(yy).get(xx)[1]);
                g2d.drawImage(tile, xx * TILE_SIZE, yy * TILE_SIZE, null);
            }
        }
    }

    public void paintMap(Graphics2D g2d) {
        displayLayer(g2d, imgBackground, BackgroundMap.map);
        displayLayer(g2d, imgObstacle, BackgroundMap.obstacles);
        displayLayer(g2d, imgDecoration, BackgroundMap.decoration);
    }

    public void loadBackgroundMap(TileMapData tileData, ArrayList<ArrayList<int[]>> map) {
        int rows = tileData.tileshigh;
        int cols = tileData.tileswide;
        int tempX, tempY;
        int tileNum;
        int[] tileCor;
        for (int yy = 0; yy < rows; yy++) {
            map.add(new ArrayList<>());
            for (int xx = 0; xx < cols; xx++) {
                tileNum = tileData.layers.get(0).tiles.get(xx + (yy * cols)).tile;
                tileCor = TileID.getTileCords(tileNum);
                tempX = tileCor[0];
                tempY = tileCor[1];
                map.get(yy).add(new int[] {tempX, tempY});
            }
        }
        // BackgroundMap.print();
    }

    public static void setBackgroundPos(Vector pos) {
        position = pos;
    }

    private BufferedImage setImage(String filename) {
        return setImage(filename, "png");
    }
    private BufferedImage setImage(String filename, String fileExtension) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(".\\src\\assets\\" + filename + "." + fileExtension));
        } catch (Exception e) {
            System.out.println("setImage() Couldn't read file: " + ".\\src\\assets\\" + filename + "." + fileExtension);
            e.printStackTrace();
            System.exit(0);
        }
        return image;
    }

}
