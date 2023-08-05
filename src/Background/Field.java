package Background;

import Background.BackgroundPanel;

public class Field {
    public static int width = 1000;
    public static int height = 624;
    public BackgroundPanel background;
    public static final boolean HARDWALL = true;
    public Field() {
        background = new BackgroundPanel();
        width = background.width;
        height = background.height;
    }
    public Field(int width, int height) {
        this.width = width;
        this.height = height;
        background = new BackgroundPanel();
    }

    /*
    This class could be used to store tileset codes
    Example:
        TileSet = [
            1, 1, 2, 1, 1, 1, 2, 2, 4,
            1, 1, 3, 1, 1, 1, 2, 2, 3,
            1, 1, 2, 1, 1, 1, 3, 3, 3
        ]
    #1 could be grass, #2 could be water, etc.
     */
}
