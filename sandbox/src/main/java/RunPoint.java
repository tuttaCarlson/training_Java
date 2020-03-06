import static java.lang.Math.sqrt;

public class RunPoint {
    public static void main(String[] args) {
        Point A = new Point(3.0,4.0);
        Point B = new Point (4.0,5.0);
        System.out.println(Point.distanceFunction(A,B));
        A.distanceMethod(B);
    }
}