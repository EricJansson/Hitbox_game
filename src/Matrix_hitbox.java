import org.ejml.simple.SimpleMatrix;

public class Matrix_hitbox {
    public GameMatrix matrixA;
    public GameMatrix matrixB;
    public GameMatrix matrixC;
    public GameMatrix matrixD;
    public GameMatrix matrixE;
    public Matrix_hitbox() {
        // Create two matrices
        // matrixA = new GameMatrix(1.0, 5.0, 7.0, 8.0);
        // matrixB = new GameMatrix(4.0, 7.0, 4.0, 9.0);
        matrixC = new GameMatrix(2.0, 5.0, 3.0, 5.0);
        matrixD = new GameMatrix(7.0, 8.0, 3.0, 5.0);
        matrixE = new GameMatrix(2.0, 4.0, 1.0, 2.0);
        matrixA = new GameMatrix(7.0, 8.0, 3.0, 5.0);
        matrixB = new GameMatrix(0.0, 40.0, 1.0, 20.0);

        // Perform matrix addition
        // SimpleMatrix result = matrix1.matrix.plus(matrix2.matrix);
        // GameMatrix test = GameMatrix.typeCast(result);
        // test.displayMatrix();

        GameMatrix test1 = matrixB.minus(matrixD);
        GameMatrix test2 = matrixD.minus(matrixB);
        GameMatrix test3 = matrixA.minus(matrixB);
        GameMatrix test4 = matrixB.minus(matrixA);
        GameMatrix test5 = matrixC.minus(matrixB);
        GameMatrix test6 = matrixB.minus(matrixC);
        GameMatrix test7 = matrixA.minus(matrixD);
        GameMatrix test8 = matrixD.minus(matrixA);


        GameMatrix test9 = matrixA.minus(matrixB.mirrorMatrix());
        GameMatrix test10 = matrixB.minus(matrixA.mirrorMatrix());
        GameMatrix test11 = matrixB.minus(matrixC.mirrorMatrix());
        GameMatrix test12 = matrixC.minus(matrixB.mirrorMatrix());
        GameMatrix test13 = matrixC.minus(matrixD.mirrorMatrix());
        GameMatrix test14 = matrixD.minus(matrixC.mirrorMatrix());
        GameMatrix test15 = matrixE.minus(matrixA.mirrorMatrix());
        GameMatrix test16 = matrixA.minus(matrixE.mirrorMatrix());




        System.out.print("\n\n\n\n");

        System.out.println("A - B: SHOULD intersect!!");
        matrixA.collisionCheck(matrixB);
        test9.displayMatrix(true);

        System.out.println("B - A: SHOULD intersect!!");
        matrixB.collisionCheck(matrixA);
        test10.displayMatrix(true);

        System.out.println("B - C: SHOULD intersect!!");
        matrixB.collisionCheck(matrixC);
        test11.displayMatrix(true);

        System.out.println("C - B: SHOULD intersect!!");
        matrixC.collisionCheck(matrixB);
        test12.displayMatrix(true);

        System.out.println("C - D: Should NOT intersect...");
        matrixC.collisionCheck(matrixD);
        test13.displayMatrix(true);

        System.out.println("D - C: Should NOT intersect...");
        matrixD.collisionCheck(matrixC);
        test14.displayMatrix(true);

        System.out.println("E - A: Should NOT intersect...");
        matrixE.collisionCheck(matrixA);
        test15.displayMatrix(true);

        System.out.println("A - E: Should NOT intersect...");
        matrixA.collisionCheck(matrixE);
        test16.displayMatrix(true);





        System.out.print("\n\n\n\n");

        System.out.println("B - D: Should NOT intersect...");
        test1.displayMatrix(true);

        System.out.println("D - B: Should NOT intersect...");
        test2.displayMatrix(true);

        System.out.println("A - D: Should NOT intersect...");
        test7.displayMatrix(true);

        System.out.println("D - A: Should NOT intersect...");
        test8.displayMatrix(true);

        System.out.println("A - B: SHOULD intersect!!");
        test3.displayMatrix(true);

        System.out.println("B - A: SHOULD intersect!!");
        test4.displayMatrix(true);

        System.out.println("C - B: SHOULD intersect!!");
        test5.displayMatrix(true);

        System.out.println("B - C: SHOULD intersect!!");
        test6.displayMatrix(true);

    }

}
