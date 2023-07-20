package TileMap;

public enum TileID {
    T0(new int[]{0, 0}),
    T1(new int[]{1, 0}),
    T2(new int[]{2, 0}),
    T3(new int[]{2, 0}),
    T4(new int[]{3, 0}),
    T5(new int[]{4, 0}),
    T8(new int[]{6, 2}),
    T9(new int[]{0, 1}),
    T10(new int[]{1, 1}),
    T11(new int[]{2, 1}),
    T12(new int[]{3, 1}),
    T13(new int[]{7, 1}),
    T14(new int[]{4, 1}),
    T15(new int[]{5, 1}),
    T16(new int[]{6, 1}),
    T22(new int[]{4, 2}),
    T23(new int[]{5, 2}),
    T26(new int[]{4, 4}),
    T27(new int[]{5, 4}),
    T28(new int[]{4, 0}),
    T29(new int[]{5, 0}),
    T30(new int[]{0, 1}),
    T31(new int[]{1, 1}),
    T32(new int[]{2, 1}),
    T37(new int[]{0, 3}),
    T38(new int[]{0, 4}),
    T42(new int[]{1, 2}),
    T44(new int[]{3, 2}),
    T47(new int[]{3, 4}),
    T48(new int[]{4, 2}),
    T49(new int[]{5, 2}),
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
        // System.out.println("TileMap.TileID - getTileByNumber(): ERROR");
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
