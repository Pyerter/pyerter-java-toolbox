package pyerter.toolbox.math.generic;

public class DoubleMathHelper extends MathHelper<Double> {

    public static final DoubleMathHelper HELPER = new DoubleMathHelper();
    public static final Double MAX_MINUS_ONE = Double.MAX_VALUE - 1;
    public static final Double MIN_PLUS_ONE = Double.MIN_VALUE + 1;
    public static final Double ZERO_ERROR = 0.0001;

    public DoubleMathHelper() {
        super(Double.class);
    }

    @Override
    public Double add(Double a, Double b) {
        return a + b;
    }

    @Override
    public Double subtract(Double a, Double b) {
        return a - b;
    }

    @Override
    public Double mult(Double a, Double b) {
        return a * b;
    }

    @Override
    public Double divide(Double a, Double b) {
        return a / b;
    }

    @Override
    public Double mod(Double a, Double b) {
        return a % b;
    }

    @Override
    public Double inc(Double a) {
        return ++a;
    }

    @Override
    public Double dec(Double a) {
        return --a;
    }

    @Override
    public Double pow(Double a, Double power) {
        return Math.pow(a, power);
    }

    @Override
    public Double ceil(Double a) {
        return Math.ceil(a);
    }

    @Override
    public Double floor(Double a) {
        return Math.floor(a);
    }

    @Override
    public Double round(Double a) {
        return (double)Math.round(a);
    }
}
