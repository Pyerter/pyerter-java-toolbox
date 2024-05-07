package pyerter.toolbox.math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TensorShapeTest {


    @Test
    public void testBasicTensorShapeActions() {
        int[] shapeArr = new int[]{ 3, 2, 6, 3};
        int[] shapeArrProds = new int[] { 108, 36, 18, 3};
        int[] shapeArrPeeled1 = new int[]{ 2, 6, 3 };
        int[] shapeArrPeeled2 = new int[] { 6, 3 };
        int[] shapeArrPeeled3 = new int[] { 3 };
        int[] shapeArrPeeled4Plus = new int[0];

        // Construct
        TensorShape shape = new TensorShape(shapeArr);

        // Assert output array is the same
        Assertions.assertArrayEquals(shapeArr, shape.toArray());

        // Assert shape products are correct
        Assertions.assertEquals(shapeArrProds[0], shape.prod());
        Assertions.assertEquals(shapeArrProds[3], shape.prod(3));
        Assertions.assertEquals(shapeArrProds[2], shape.prod(2, 4));
        Assertions.assertEquals(shapeArr[2], shape.prod(2, 3));
        Assertions.assertEquals(shapeArrProds[0], shape.prod(-4));
        Assertions.assertEquals(shapeArrProds[2], shape.prod(-2));

        // Assert product errors
        Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> shape.prod(-5));

        // Get indexes based on shape
        Assertions.assertEquals(0, shape.index());
        Assertions.assertEquals(shapeArrProds[1], shape.index(1));
        Assertions.assertEquals(shapeArrProds[1] * 2, shape.index(2));
        Assertions.assertEquals(0, shape.index(0));
        Assertions.assertEquals(shapeArrProds[2], shape.index(0, 1));
        Assertions.assertEquals(shapeArr[3] + 2, shape.index(0, 0, 1, 2));
        Assertions.assertEquals(shapeArrProds[2] + shapeArr[3] * 2 + 2, shape.index(0, 1, 2, 2));
        Assertions.assertEquals(shapeArrProds[1] * (shapeArr[0] - 1), shape.index(-1));
        int targetIndex = shapeArrProds[1] + shapeArrProds[2] + shapeArrProds[3] * (shapeArr[2] - 2) + (shapeArr[3] - 2); ;
        int givenIndex = shape.index(1, -1, -2, -2);
        Assertions.assertEquals(targetIndex, givenIndex);

        // Assert indexing errors
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, () -> shape.index(0, 2));
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, () -> shape.index(-shapeArr[0] - 1));
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, () -> shape.index(shapeArr[0]));
        Assertions.assertThrowsExactly(IllegalTensorStateException.class, () -> shape.index(0, 0, 0, 0, 0));

        // Try peeling
        TensorShape shapePeeled1 = shape.peel();
        Assertions.assertArrayEquals(shapeArrPeeled1, shapePeeled1.toArray());
        TensorShape shapePeeled2 = shapePeeled1.peel();
        Assertions.assertArrayEquals(shapeArrPeeled2, shapePeeled2.toArray());
        TensorShape shapePeeled3 = shapePeeled2.peel();
        Assertions.assertArrayEquals(shapeArrPeeled3, shapePeeled3.toArray());
        TensorShape shapePeeled4 = shapePeeled3.peel();
        Assertions.assertArrayEquals(shapeArrPeeled4Plus, shapePeeled4.toArray());
        TensorShape shapePeeled5 = shapePeeled4.peel();
        Assertions.assertArrayEquals(shapeArrPeeled4Plus, shapePeeled5.toArray());
    }

}
