public enum Direction {
    EAST,
    WEST,
    NORTH,
    SOUTH,
    NORTHEAST,
    SOUTHEAST,
    NORTHWEST,
    SOUTHWEST,
    VERTICAL,
    HORIZONTAL,
    NONE;



    public static Direction getAxisDir(Vector vector) {
        Direction dir = Direction.NONE; // if this is returned => Vector is a dot
        if (vector.getX() != 0) {
            dir = Direction.HORIZONTAL;
        } else if (vector.getY() != 0) {
            dir = Direction.VERTICAL;
        }
        return dir;
    }

    public static Direction getDir(Vector vector) {
        Direction horizontal = Direction.NONE, vertical = Direction.NONE;
        if (vector.getX() > 0) {
            horizontal = Direction.EAST;
        } else if (vector.getX() < 0) {
            horizontal = Direction.WEST;
        }
        if (vector.getY() < 0) {
            vertical = Direction.NORTH;
        } else if (vector.getY() > 0) {
            vertical = Direction.SOUTH;
        }
        return addDirs(horizontal, vertical);
    }

    public static Direction addDirs(Direction horizontal, Direction vertical) {
        // Return single direction
        if (horizontal == Direction.NONE) {
            return vertical;
        } else if (vertical == Direction.NONE) {
            return horizontal;
        }

        if (vertical == Direction.NORTH) {
            if (horizontal == Direction.EAST) {
                return Direction.NORTHEAST;
            } else {
                return Direction.NORTHWEST;
            }
        } else if (vertical == Direction.SOUTH) {
            if (horizontal == Direction.EAST) {
                return Direction.SOUTHEAST;
            } else {
                return Direction.SOUTHWEST;
            }
        }
        System.out.println("Can't addDirs(). ERROR");
        System.exit(0);
        return null;
    }
}
