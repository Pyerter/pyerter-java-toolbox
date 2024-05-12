package pyerter.toolbox.math.generic;

public class IntMathHelper extends MathHelper<Integer> {

    public static final long MAX_VALUE_AS_LONG = Integer.MAX_VALUE;
    public static final long MIN_VALUE_AS_LONG = Integer.MIN_VALUE;

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

    @Override
    public Integer ceil(Double a) {
        long result = (long)Math.ceil(a);
        if (result >= MAX_VALUE_AS_LONG) return Integer.MAX_VALUE;
        if (result <= MIN_VALUE_AS_LONG) return Integer.MIN_VALUE;
        return (int)result;
    }

    @Override
    public Integer floor(Double a) {
        long result = (long)Math.floor(a);
        if (result >= MAX_VALUE_AS_LONG) return Integer.MAX_VALUE;
        if (result <= MIN_VALUE_AS_LONG) return Integer.MIN_VALUE;
        return (int)result;
    }

    @Override
    public Integer round(Double a) {
        long result = Math.round(a);
        if (result >= MAX_VALUE_AS_LONG) return Integer.MAX_VALUE;
        if (result <= MIN_VALUE_AS_LONG) return Integer.MIN_VALUE;
        return (int)result;
    }
}
