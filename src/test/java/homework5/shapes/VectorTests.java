package homework5.shapes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VectorTests {
    @Test
    public void startVectorFromTest() {

        Vector vector = new Vector(
                new Point(6, 6),
                new Point(10, 10)
        );

        Point start = new Point(0, 0);

        Vector newVector = vector.startFrom(start);

        Assertions.assertEquals(0, newVector.pos1.x);
        Assertions.assertEquals(0, newVector.pos1.y);
        Assertions.assertEquals(4, newVector.pos2.x);
        Assertions.assertEquals(4, newVector.pos2.y);
    }

    @Test
    public void scalarSumTest() {
        Vector a = new Vector(new Point(1, 1), new Point(4, 4));
        Vector b = new Vector(new Point(2,2), new Point(2,3));
        Vector actual = a.scalarSum(b);
        Assertions.assertEquals(1, actual.pos1.x);
        Assertions.assertEquals(1, actual.pos1.y);
        Assertions.assertEquals(4, actual.pos2.x);
        Assertions.assertEquals(5, actual.pos2.y);
    }
}
