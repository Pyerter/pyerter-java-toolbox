package pyerter.toolbox.math.generic;

public class FloatMathHelper extends MathHelper<Float> {

    public static final Double MAX_VALUE_AS_DOUBLE = Double.MAX_VALUE;
    public static final Double MIN_VALUE_AS_DOUBLE = Double.MIN_VALUE;

    public static final FloatMathHelper HELPER = new FloatMathHelper();

    public FloatMathHelper() {
        super(Float.class);
    }

    @Override
    public Float add(Float a, Float b) {
        return a + b;
    }

    @Override
    public Float subtract(Float a, Float b) {
        return a - b;
    }

    @Override
    public Float mult(Float a, Float b) {
        return a * b;
    }

    @Override
    public Float divide(Float a, Float b) {
        return a / b;
    }

    @Override
    public Float mod(Float a, Float b) {
        return a % b;
    }

    @Override
    public Float inc(Float a) {
        return ++a;
    }

    @Override
    public Float dec(Float a) {
        return --a;
    }

    @Override
    public Float pow(Float a, Float power) {
        return (float)Math.pow(a, power);
    }

    @Override
    public Float ceil(Double a) {
        double result = Math.ceil(a);
        if (result >= MAX_VALUE_AS_DOUBLE) return Float.MAX_VALUE;
        if (result <= MIN_VALUE_AS_DOUBLE) return Float.MIN_VALUE;
        return (float)result;
    }

    @Override
    public Float floor(Double a) {
        double result = Math.floor(a);
        if (result >= MAX_VALUE_AS_DOUBLE) return Float.MAX_VALUE;
        if (result <= MIN_VALUE_AS_DOUBLE) return Float.MIN_VALUE;
        return (float)result;
    }

    @Override
    public Float round(Double a) {
        double result = Math.round(a);
        if (result >= MAX_VALUE_AS_DOUBLE) return Float.MAX_VALUE;
        if (result <= MIN_VALUE_AS_DOUBLE) return Float.MIN_VALUE;
        return (float)result;
    }
}
