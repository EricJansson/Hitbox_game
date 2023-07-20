import java.util.Arrays;

public enum TileID {
    T0(new int[]{8, 1}),
    T50(new int[]{0, 0}),

    T52(new int[]{1, 0}),
    T53(new int[]{3, 0}),
    T54(new int[]{4, 0}),
    T55(new int[]{5, 0}),
    T56(new int[]{6, 0}),
    T57(new int[]{7, 0}),
    T58(new int[]{8, 0}),
    T60(new int[]{1, 1}),
    T62(new int[]{6, 3}),
    T66(new int[]{6, 1}), // or {7, 1}
    T67(new int[]{0, 2}), // or {1, 2}
    T72(new int[]{5, 2}),
    T73(new int[]{6, 2}),
    T74(new int[]{7, 2}),
    T75(new int[]{8, 2}),
    T77(new int[]{1, 3}),
    T81(new int[]{5, 3}),
    T82(new int[]{6, 3}),
    T83(new int[]{7, 3}),
    T97(new int[]{0, 6}),
    T98(new int[]{5, 6}),
    T101(new int[]{3, 6}),
    T103(new int[]{6, 6}), // or {7, 6}
    T104(new int[]{7, 6}),
    T105(new int[]{0, 7}),
    T106(new int[]{2, 7}),
    T107(new int[]{3, 7}),
    T109(new int[]{4, 7}),
    T110(new int[]{5, 7}),
    T111(new int[]{6, 5}), //  or {7, 5} or {8, 5}
    T112(new int[]{3, 5}),
    T113(new int[]{4, 5}),
    T114(new int[]{6, 7}),
    T115(new int[]{7, 7}),
    T117(new int[]{4, 3});

    private int[] position;

    TileID(int[] position) {
        this.position = position;
    }

    public int getX() {
        return position[0];
    }

    public int getY() {
        return position[1];
    }

    public String getName() {
        return name();
    }

    public static TileID getTileByNumber(int tileNumber) {
        String tileName = "T" + tileNumber;
        TileID[] tiles = TileID.values();
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i].name().equals(tileName)) {
                return tiles[i];
            }
        }
        return null;
        // System.out.println("TileID - getTileByNumber(): ERROR");
        // throw new IllegalArgumentException("Tile not found for number: " + tileNumber);
    }

    public static int[] getTileCords(int tileNum) {
        TileID tile = getTileByNumber(tileNum);
        if (tile == null) {
            return new int[]{0, 0}; // Transparent tile
        }
        // System.out.println("[" + tile.getX() + ", " + tile.getY() + "]");
        return new int[]{tile.getX(), tile.getY()};
    }

}
