import org.testng.annotations.Test;

public class DistanceTests {

    @Test
    public void equalPoints(){
        Point A = new Point(3.0, 4.0);
        Point B = new Point(3.0, 4.0);
        assert Point.distanceFunction(A,B) == 0.0;
    }

    @Test
    public void positivePoints(){
        Point A = new Point(1.0, 2.0);
        Point B = new Point(4.0, 6.0);
        assert Point.distanceFunction(A,B) == 5.0;
    }

    @Test
    public void negativePoints(){
        Point A = new Point(-1.0, -2.0);
        Point B = new Point(-4.0, -6.0);
        assert Point.distanceFunction(A,B) == 5.0;
    }
}
