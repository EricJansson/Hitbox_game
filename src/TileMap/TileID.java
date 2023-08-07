package TileMap;

import DataFormats.GameMatrix;
import Enums.MovementType;

import static Background.BackgroundPanel.TILE_SIZE;

public enum TileID {
    T0(new int[]{0, 0}),
    T1(new int[]{1, 0}),
    T2(new int[]{2, 0}),
    T3(new int[]{2, 0}),
    T4(new int[]{3, 0}),
    T5(new int[]{4, 0}),
    T6(new int[]{6, 0}),
    T7(new int[]{7, 0}),
    T8(new int[]{6, 2}),
    T9(new int[]{0, 1}),
    T10(new int[]{1, 1}),
    T11(new int[]{2, 1}),
    T12(new int[]{3, 1}),
    T13(new int[]{2, 3}),
    T14(new int[]{4, 1}),
    T15(new int[]{5, 1}),
    T16(new int[]{6, 1}),
    T22(new int[]{4, 2}),
    T23(new int[]{5, 2}),
    T26(new int[]{4, 4}, new MovementType[]{}, new GameMatrix(0, TILE_SIZE, 0, (7.0/8) * TILE_SIZE)),
    T27(new int[]{5, 4}, new MovementType[]{}, new GameMatrix(0, TILE_SIZE, 0, (7.0/8) * TILE_SIZE)),
    T28(new int[]{4, 0}, new MovementType[]{MovementType.FLYING}, new GameMatrix(0, TILE_SIZE, 0, (7.0/8) * TILE_SIZE)),
    T29(new int[]{5, 0}, new MovementType[]{MovementType.FLYING}, new GameMatrix(0, TILE_SIZE, 0, (7.0/8) * TILE_SIZE)),
    T30(new int[]{0, 1}, new MovementType[]{MovementType.FLYING}, new GameMatrix((1.0/4) * TILE_SIZE, (3.0/4) * TILE_SIZE, 0, (7.0/8) * TILE_SIZE)),
    T31(new int[]{1, 1}, new MovementType[]{MovementType.FLYING}, new GameMatrix((1.0/4) * TILE_SIZE, TILE_SIZE, 0, ((7.0/8) * TILE_SIZE))),
    T32(new int[]{2, 1}, new MovementType[]{MovementType.FLYING}, new GameMatrix(0, TILE_SIZE, 0, ((7.0/8) * TILE_SIZE))),
    T36(new int[]{0, 2}, new MovementType[]{MovementType.FLYING}, new GameMatrix((1.0/4) * TILE_SIZE, (3.0/4) * TILE_SIZE, 0, (7.0/8) * TILE_SIZE)),
    T37(new int[]{0, 3}, new MovementType[]{MovementType.FLYING}, new GameMatrix((1.0/4) * TILE_SIZE, (3.0/4) * TILE_SIZE, 0, (7.0/8) * TILE_SIZE)),
    T38(new int[]{0, 4}, new MovementType[]{MovementType.FLYING}, new GameMatrix((1.0/4) * TILE_SIZE, (3.0/4) * TILE_SIZE, 0, (7.0/8) * TILE_SIZE)),
    T42(new int[]{1, 2}, new MovementType[]{MovementType.FLYING}, new GameMatrix((1.0/4) * TILE_SIZE, TILE_SIZE, 0, TILE_SIZE)),
    T44(new int[]{3, 2}, new MovementType[]{MovementType.FLYING}, new GameMatrix(0, (3.0/4) * TILE_SIZE, 0, TILE_SIZE)),
    T47(new int[]{3, 4}, new MovementType[]{MovementType.FLYING}, new GameMatrix(0, (3.0/4) * TILE_SIZE, 0, (7.0/8) * TILE_SIZE)),
    T48(new int[]{4, 2}, new MovementType[]{MovementType.FLYING}, new GameMatrix(0, (1.0/2) * TILE_SIZE, 0, (7.0/8) * TILE_SIZE), new GameMatrix((3.0/8) * TILE_SIZE, TILE_SIZE, (1.0/4) * TILE_SIZE, (5.0/8) * TILE_SIZE)),
    T49(new int[]{5, 2}, new MovementType[]{MovementType.FLYING}, new GameMatrix((1.0/2) * TILE_SIZE, TILE_SIZE, 0, (7.0/8) * TILE_SIZE), new GameMatrix(0, (5.0/8) * TILE_SIZE, (1.0/4) * TILE_SIZE, (5.0/8) * TILE_SIZE)),
    T50(new int[]{8, 6}),
    T52(new int[]{1, 0}),
    T53(new int[]{3, 0}),
    T54(new int[]{4, 0}),
    T55(new int[]{5, 0}),
    T56(new int[]{6, 0}),
    T57(new int[]{7, 0}),
    T58(new int[]{8, 0}),
    T60(new int[]{1, 1}),
    T61(new int[]{3, 1}),
    T62(new int[]{6, 3}),
    T63(new int[]{5, 1}),
    T64(new int[]{1, 2}),
    T65(new int[]{7, 1}), // or {7, 1}
    T66(new int[]{6, 1}), // or {7, 1}
    T67(new int[]{0, 2}), // or {1, 2}
    T68(new int[]{0, 2}), // or {1, 2}
    T72(new int[]{5, 2}),
    T73(new int[]{6, 2}),
    T74(new int[]{7, 2}),
    T75(new int[]{8, 2}),
    T77(new int[]{1, 3}),
    T80(new int[]{4, 3}),
    T81(new int[]{5, 3}),
    T82(new int[]{6, 3}),
    T83(new int[]{7, 3}),
    T84(new int[]{8, 3}),
    T97(new int[]{0, 6}, new MovementType[]{}, new GameMatrix(0, TILE_SIZE, 0, TILE_SIZE)),
    T98(new int[]{5, 6}, new MovementType[]{}, new GameMatrix(0, TILE_SIZE, 0, TILE_SIZE)),
    T99(new int[]{1, 6}, new MovementType[]{}, new GameMatrix(0, TILE_SIZE, 0, TILE_SIZE)),
    T100(new int[]{2, 6}, new MovementType[]{}, new GameMatrix(0, TILE_SIZE, 0, TILE_SIZE)),
    T101(new int[]{3, 6}, new MovementType[]{}, new GameMatrix(0, TILE_SIZE, 0, TILE_SIZE)),
    T103(new int[]{6, 6}, new MovementType[]{}, new GameMatrix(0, 0, 0, TILE_SIZE), new GameMatrix(TILE_SIZE, TILE_SIZE, 0, TILE_SIZE)), // or {7, 6}
    T104(new int[]{7, 6}, new MovementType[]{}, new GameMatrix(0, 0, 0, TILE_SIZE), new GameMatrix(TILE_SIZE, TILE_SIZE, 0, TILE_SIZE)),
    T105(new int[]{0, 7}, new MovementType[]{}, new GameMatrix(0, 0, 0, TILE_SIZE)),
    T106(new int[]{2, 7}, new MovementType[]{}, new GameMatrix(TILE_SIZE, TILE_SIZE, 0, TILE_SIZE)),
    T107(new int[]{3, 7}, new MovementType[]{}, new GameMatrix(0, TILE_SIZE, 0, 0)),
    T108(new int[]{1, 7}, new MovementType[]{}, new GameMatrix(0, TILE_SIZE, TILE_SIZE, TILE_SIZE)),
    T109(new int[]{4, 7}, new MovementType[]{}, new GameMatrix(0, TILE_SIZE, 0, 0), new GameMatrix(TILE_SIZE, TILE_SIZE, 0, TILE_SIZE)),
    T110(new int[]{5, 7}, new MovementType[]{}, new GameMatrix(0, TILE_SIZE, 0, 0), new GameMatrix(0, 0, 0, TILE_SIZE)),
    T111(new int[]{6, 5}, new MovementType[]{MovementType.SWIMMING, MovementType.FLYING}, new GameMatrix(0, TILE_SIZE, 0, TILE_SIZE)), //  or {7, 5} or {8, 5}
    T112(new int[]{3, 5}, new MovementType[]{MovementType.SWIMMING, MovementType.FLYING}, new GameMatrix(0, TILE_SIZE, 0, TILE_SIZE)),
    T113(new int[]{4, 5}, new MovementType[]{MovementType.SWIMMING, MovementType.FLYING}, new GameMatrix(0, TILE_SIZE, 0, TILE_SIZE)),
    T114(new int[]{6, 7}),
    T115(new int[]{7, 7}),
    T117(new int[]{4, 3}, new MovementType[]{}, new GameMatrix(0, TILE_SIZE, 0, TILE_SIZE - ((double) TILE_SIZE / 8))),
    T118(new int[]{8, 7}, new MovementType[]{}, new GameMatrix(0, 0, 0, TILE_SIZE), new GameMatrix(0, TILE_SIZE, TILE_SIZE, TILE_SIZE)),
    T119(new int[]{0, 8}, new MovementType[]{}, new GameMatrix(0, TILE_SIZE, TILE_SIZE, TILE_SIZE), new GameMatrix(TILE_SIZE, TILE_SIZE, 0, TILE_SIZE)),
    T120(new int[]{0, 3}),
    T121(new int[]{2, 0}, new MovementType[]{MovementType.FLYING}, new GameMatrix(0, TILE_SIZE, 0, (7.0/8) * TILE_SIZE)),
    T122(new int[]{3, 0}, new MovementType[]{MovementType.FLYING}, new GameMatrix(0, TILE_SIZE, 0, (7.0/8) * TILE_SIZE));


    private final int[] position;
    public final MovementType[] movingType;
    private final GameMatrix blockedArea;
    private final GameMatrix blockedArea2;

    // 2 variables for 2 different blockedAreas... EWWWW

    TileID(int[] position) { this(position, new MovementType[]{}, null, null); } // Not an obstacle
    TileID(int[] position, MovementType[] movingTypeArr, GameMatrix matrix) {
        this(position, movingTypeArr, matrix, null);
    }
    TileID(int[] position, MovementType[] movingTypeArr, GameMatrix matrix, GameMatrix matrix2) {
        this.position = position;
        movingType = movingTypeArr;
        blockedArea = matrix;
        blockedArea2 = matrix2;
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

    public static MovementType[] getMovingType(int tileNum) {
        TileID tile = TileID.getTileByNumber(tileNum);
        if (tile == null) {
            System.out.println("getMovingType() ERROR: tile == null");
            System.exit(0);
        }
        return tile.movingType;
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

    public static GameMatrix getTileBlockedArea(int tileNum) {
        TileID tile = getTileByNumber(tileNum);
        if (tile == null) {
            return null; // Undefined tile
        }
        return tile.blockedArea;
    }


    // 2 variables for 2 different blockedAreas... EWWWW
    public static GameMatrix getTileBlockedArea2(int tileNum) {
        TileID tile = getTileByNumber(tileNum);
        if (tile == null) {
            return null; // Undefined tile
        }
        return tile.blockedArea2;
    }

    public static int getTileBlockedAreaCount(int tileNum) {
        int counter = 0;
        TileID tile = getTileByNumber(tileNum);
        if (tile == null) {
            return counter; // Undefined tile
        }
        if (tile.blockedArea != null) {
            counter++;
        }
        if (tile.blockedArea2 != null) {
            counter++;
        }
        return counter;
    }

}
