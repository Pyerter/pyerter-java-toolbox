package pyerter.toolbox.math.generic;

public class ShortMathHelper extends MathHelper<Short> {

    public static final long MAX_VALUE_AS_LONG = Short.MAX_VALUE;
    public static final long MIN_VALUE_AS_LONG = Short.MIN_VALUE;

    public static final ShortMathHelper HELPER = new ShortMathHelper();

    public ShortMathHelper() {
        super(Short.class);
    }

    @Override
    public Short add(Short a, Short b) {
        return (short)(a + b);
    }

    @Override
    public Short subtract(Short a, Short b) {
        return (short)(a - b);
    }

    @Override
    public Short mult(Short a, Short b) {
        return (short)(a * b);
    }

    @Override
    public Short divide(Short a, Short b) {
        return (short)(a / b);
    }

    @Override
    public Short mod(Short a, Short b) {
        return (short)(a % b);
    }

    @Override
    public Short inc(Short a) {
        return ++a;
    }

    @Override
    public Short dec(Short a) {
        return --a;
    }

    @Override
    public Short pow(Short a, Short power) {
        int result = 1;
        for (int i = 0; i < power; i++) {
            result *= a;
        }
        return (short)result;
    }

    @Override
    public Short ceil(Double a) {
        long result = (long)Math.ceil(a);
        if (result >= MAX_VALUE_AS_LONG) return Short.MAX_VALUE;
        if (result <= MIN_VALUE_AS_LONG) return Short.MIN_VALUE;
        return (short)result;
    }

    @Override
    public Short floor(Double a) {
        long result = (long)Math.floor(a);
        if (result >= MAX_VALUE_AS_LONG) return Short.MAX_VALUE;
        if (result <= MIN_VALUE_AS_LONG) return Short.MIN_VALUE;
        return (short)result;
    }

    @Override
    public Short round(Double a) {
        long result = Math.round(a);
        if (result >= MAX_VALUE_AS_LONG) return Short.MAX_VALUE;
        if (result <= MIN_VALUE_AS_LONG) return Short.MIN_VALUE;
        return (short)result;
    }
}
