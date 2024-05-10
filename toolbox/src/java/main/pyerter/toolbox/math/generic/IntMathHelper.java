package pyerter.toolbox.math.generic;

public class IntMathHelper extends MathHelper<Integer> {

    public static final IntMathHelper HELPER = new IntMathHelper();

    public IntMathHelper() {
        super(Integer.class);
    }

    @Override
    public Integer add(Integer a, Integer b) {
        return a + b;
    }

    @Override
    public Integer subtract(Integer a, Integer b) {
        return a - b;
    }

    @Override
    public Integer mult(Integer a, Integer b) {
        return a * b;
    }

    @Override
    public Integer divide(Integer a, Integer b) {
        return a / b;
    }

    @Override
    public Integer mod(Integer a, Integer b) {
        return a % b;
    }

    @Override
    public Integer inc(Integer a) {
        return ++a;
    }

    @Override
    public Integer dec(Integer a) {
        return --a;
    }

    @Override
    public Integer pow(Integer a, Integer power) {
        int result = 1;
        for (int i = 0; i < power; i++) {
            result *= a;
        }
        return result;
    }
}
