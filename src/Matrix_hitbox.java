import org.ejml.simple.SimpleMatrix;

public class Matrix_hitbox {
    public GameMatrix matrix1;
    public GameMatrix matrix2;
    public Matrix_hitbox() {
        // Create two matrices
        matrix1 = new GameMatrix(2, 2);
        matrix2 = new GameMatrix(2, 2);

        // Set values in the matrices
        matrix1.set(0, 0, 1.0);
        matrix1.set(0, 1, 2.0);
        matrix1.set(1, 0, 3.0);
        matrix1.set(1, 1, 4.0);

        matrix2.set(0, 0, 5.0);
        matrix2.set(0, 1, 6.0);
        matrix2.set(1, 0, 7.0);
        matrix2.set(1, 1, 8.0);

        matrix1.displayMatrix();
        matrix2.displayMatrix();

        // Perform matrix addition
        SimpleMatrix result = matrix1.matrix.plus(matrix2.matrix);
        GameMatrix test = GameMatrix.typeCast(result);
        test.displayMatrix();

        GameMatrix test2 = matrix1.plus(matrix2);
        test2.displayMatrix();
    }

}
