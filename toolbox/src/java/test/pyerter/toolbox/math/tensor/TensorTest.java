package pyerter.toolbox.math.tensor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TensorTest {

    @Test
    public void testBasicTensorActions() {
        int[] arrShape = new int[]{ 3, 2, 6, 3 };
        int[] arrShapeProds = new int[] { 108, 36, 18, 3};

        // Construct
        Tensor<Integer> tensor = new Tensor<>(Integer.class, 3, 2, 6, 3);
        // Initial properties
        Assertions.assertArrayEquals(arrShape, tensor.shape.toArray());
        Assertions.assertEquals(arrShape.length, tensor.dimensions());
        Assertions.assertEquals(arrShapeProds[0], tensor.elements());


    }

}
