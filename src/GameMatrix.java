import org.ejml.simple.SimpleMatrix;


public class GameMatrix {
    SimpleMatrix matrix;

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

    public void displayMatrix() {
        System.out.println("Matrix:");
        for (int row = 0; row < this.matrix.getNumRows(); row++) {
            displayRow(row);
        }
    }

    public void set(int row, int col, double val) {
        this.matrix.set(row, col, val);
    }


    public GameMatrix plus(GameMatrix matrix) {
        GameMatrix tempMatrix = new GameMatrix(this.matrix);
        tempMatrix.matrix.plus(matrix.matrix);
        return tempMatrix;
    }


    public static GameMatrix typeCast(SimpleMatrix matrix) {
        return new GameMatrix(matrix);
    }

}
