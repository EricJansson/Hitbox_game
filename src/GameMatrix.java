import org.ejml.simple.SimpleMatrix;

import java.awt.*;


public class GameMatrix {
    SimpleMatrix matrix;

    public GameMatrix(double xLeft, double xRight, double yDown, double yUp) {
        this.matrix = new SimpleMatrix(2, 2);
        matrix.set(0, xLeft);
        matrix.set(1, xRight);
        matrix.set(2, yDown);
        matrix.set(3, yUp);
    }
    public GameMatrix(int numRows, int numCols) {
        this.matrix = new SimpleMatrix(numRows, numCols);
    }
    public GameMatrix(SimpleMatrix matrix) {
        this.matrix = new SimpleMatrix(matrix);
    }
    public GameMatrix(GameMatrix matrix) {
        this.matrix = new SimpleMatrix(matrix.matrix);
    }
    public GameMatrix() {

    }

    public void displayRow(int row) {
        System.out.print("Row(" + row + "): ");
        for (int ii = 0; ii < this.matrix.getNumCols(); ii++) {
            System.out.print(this.matrix.get(row, ii) + " ");
        }
        System.out.println();
    }

    public void displayMatrix() { this.displayMatrix(false);}

    public void displayMatrix(boolean detailed) {
        System.out.println("Matrix:");
        if (!detailed) {
            System.out.printf("%.1f  %.1f\n", matrix.get(0), matrix.get(1));
            System.out.printf("%.1f  %.1f\n", matrix.get(2), matrix.get(3));
            return;
        }
        if (matrix.getNumRows() * matrix.getNumCols() != 4) {
            System.out.println("ERROR: displayMatrix() - Wrong matrix format");
            System.exit(0);
        }
        System.out.printf("Row(0)    xLeft:   %.1f   xRight:  %.1f\n", matrix.get(0), matrix.get(1));
        System.out.printf("Row(1)    yDown:   %.1f   yUp:     %.1f\n", matrix.get(2), matrix.get(3));
    }

    public void drawMatrix(Graphics2D g2d, Color color) {
        int borderSize = 12;
        int inset = borderSize / 2;
        int x, y, width, height;
        x = (int) getXmin() + inset;
        y = (int) getYmin() + inset;
        width = (int) (getXmax() - getXmin()) - borderSize;
        height = (int) (getYmax() - getYmin()) - borderSize;
        if (width < 4) {
            width = 4;
            x -= borderSize / 2;
        }
        if (height < 4) {
            height = 4;
            y -= borderSize / 2;
        }
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(borderSize));
        g2d.drawRect(x, y, width, height);
        // g2d.drawRect((int) position.getX() + inset, (int) position.getY() + inset, width - borderSize, height - borderSize);
        // g2d.drawRect((int) getXmin(), (int) getYmax(), (int) (getXmax() - getXmin()), (int) (getYmax() - getYmin()));

    }

    public GameMatrix mirrorMatrix() {
        return new GameMatrix(getXmax(), getXmin(), getYmax(), getYmin());
    }

    public static double getMatrixCollisionDeltaX(Entity entity, GameMatrix matrix2) {
        double deltaX = 0.0;
        if (entity.dir == Direction.SOUTHEAST || entity.dir == Direction.EAST || entity.dir == Direction.NORTHEAST) {
            deltaX = entity.hitbox.getXmax() - matrix2.getXmin();
        } else if (entity.dir == Direction.SOUTHWEST || entity.dir == Direction.WEST || entity.dir == Direction.NORTHWEST) {
            deltaX = matrix2.getXmax() - entity.hitbox.getXmin();
        } else if (entity.dir == Direction.NORTH || entity.dir == Direction.SOUTH) {
            deltaX = Math.max(matrix2.getXmax() - entity.hitbox.getXmin(), entity.hitbox.getXmax() - matrix2.getXmin());
        }
        return deltaX;
    }

    public static double getMatrixCollisionDeltaY(Entity entity, GameMatrix matrix2) {
        double deltaY = 0.0;
        if (entity.dir == Direction.SOUTHWEST || entity.dir == Direction.SOUTH || entity.dir == Direction.SOUTHEAST) {
            deltaY = entity.hitbox.getYmax() - matrix2.getYmin();
        } else if (entity.dir == Direction.NORTHWEST || entity.dir == Direction.NORTH || entity.dir == Direction.NORTHEAST) {
            deltaY = matrix2.getYmax() - entity.hitbox.getYmin();
        } else if (entity.dir == Direction.WEST || entity.dir == Direction.EAST) {
            deltaY = Math.max(entity.hitbox.getYmax() - matrix2.getYmin(), matrix2.getYmax() - entity.hitbox.getYmin());
        }
        return deltaY;
    }

    public boolean collisionCheck(SimpleMatrix matrix) {
        GameMatrix tempMatrix = new GameMatrix(matrix);
        return collisionCheck(tempMatrix);
    }
    public boolean collisionCheck(GameMatrix matrix2) {
        if (getXmin() >= matrix2.getXmax() || getXmax() <= matrix2.getXmin()) {
            return false;
        } else if (getYmin() >= matrix2.getYmax() || getYmax() <= matrix2.getYmin()) {
            return false;
        }
        return true;
    }

    public void updateMatrix(double xLeft, double xRight, double yDown, double yUp) {
        matrix.set(0, xLeft);
        matrix.set(1, xRight);
        matrix.set(2, yDown);
        matrix.set(3, yUp);
    }


    // Getters
    public double getXmin() {return this.matrix.get(0);}
    public double getXmax() {return this.matrix.get(1);}
    public double getYmin() {return this.matrix.get(2);}
    public double getYmax() {return this.matrix.get(3);}


    // REMADE SimpleMatrix functions
    public void set(int row, int col, double val) {
        this.matrix.set(row, col, val);
    }
    public void set(int index, double val) {
        this.matrix.set(index, val);
    }

    public GameMatrix plus(GameMatrix matrix) {
        GameMatrix tempMatrix = new GameMatrix(this.matrix);
        tempMatrix.matrix = tempMatrix.matrix.plus(matrix.matrix);
        return tempMatrix;
    }

    public GameMatrix plus(SimpleMatrix matrix) {
        GameMatrix tempMatrix = new GameMatrix(this.matrix);
        tempMatrix.matrix = tempMatrix.matrix.plus(matrix);
        return tempMatrix;
    }

    public GameMatrix minus(GameMatrix matrix) {
        GameMatrix tempMatrix = new GameMatrix(this.matrix);
        tempMatrix.matrix = tempMatrix.matrix.minus(matrix.matrix);
        return tempMatrix;
    }

    public GameMatrix minus(SimpleMatrix matrix) {
        GameMatrix tempMatrix = new GameMatrix(this.matrix);
        tempMatrix.matrix = tempMatrix.matrix.minus(matrix);
        return tempMatrix;
    }

    public static GameMatrix typeCast(SimpleMatrix matrix) {
        return new GameMatrix(matrix);
    }

}
