public class VectorCalculations {

    public static Vector findIntersectionPoint(Vector2D v1, Vector2D v2) {
        return findIntersectionPoint(v1.pos.getX(), v1.pos.getY(), v1.pos.getX() + v1.dir.getX(), v1.pos.getY() + v1.dir.getY(),
                v2.pos.getX(), v2.pos.getY(), v2.pos.getX() + v2.dir.getX(), v2.pos.getY() + v2.dir.getY());
    }
    public static Vector findIntersectionPoint(double startX1, double startY1, double endX1, double endY1,
                                        double startX2, double startY2, double endX2, double endY2) {
        double denominator = (startX1 - endX1) * (startY2 - endY2) - (startY1 - endY1) * (startX2 - endX2);
        if (denominator == 0) {
            System.out.println("Vectors are parallel.");
            return null;
        }
        double intersectionX = ((startX1 * endY1 - startY1 * endX1) * (startX2 - endX2) - (startX1 - endX1) * (startX2 * endY2 - startY2 * endX2)) / denominator;
        double intersectionY = ((startX1 * endY1 - startY1 * endX1) * (startY2 - endY2) - (startY1 - endY1) * (startX2 * endY2 - startY2 * endX2)) / denominator;
        System.out.println("Vectors intersect at (" + intersectionX + ", " + intersectionY + ")");
        return new Vector(intersectionX, intersectionY);
    }

    public static boolean isIntersecting(Vector2D v1, Vector2D v2) {
        Vector intersectPoint = findIntersectionPoint(v1, v2);
        if (intersectPoint == null) { // Paralell
            return false;
        }
        double startX = v1.pos.getX();
        double endX = v1.pos.getX() + v1.dir.getX();

        if (startX < endX) { // If direction is negative -> xMin = pos - dir
            if (intersectPoint.getX() >= startX && intersectPoint.getX() <= endX) {
                System.out.println("Within boundary X: " + startX + ", " + endX + "   CHECK!");
                return true;
            }
        } else {
            if (intersectPoint.getX() >= endX && intersectPoint.getX() <= startX) {
                System.out.println("Within boundary X: " + endX + ", " + startX + "   CHECK!");
                return true;
            }
        }
        System.out.println("NOT Within boundary.");
        return false;
    }

}
