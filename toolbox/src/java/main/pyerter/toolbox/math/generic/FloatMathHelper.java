package pyerter.toolbox.math.generic;

public class FloatMathHelper extends MathHelper<Float> {

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
}
