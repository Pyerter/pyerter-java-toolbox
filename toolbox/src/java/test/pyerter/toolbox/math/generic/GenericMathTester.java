package pyerter.toolbox.math.generic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GenericMathTester {

    @Test
    public void testMathHelperGetter() {
        MathHelper<Integer> math = GenericMath.getHelper(Integer.class);
        Assertions.assertEquals(Integer.class.getName(), math.getDtype().getName());
    }

}
