import static java.lang.Math.sqrt;

public class Point {
    double x;
    double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;

    }

    public void distanceMethod(Point p2) {
        double s = (sqrt((this.x - p2.x) * (this.x - p2.x) + (this.y - p2.y) * (this.y - p2.y)));
        System.out.println(s);
    }

    public static double distanceFunction(Point p1, Point p2) {
        return sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
    }
}
