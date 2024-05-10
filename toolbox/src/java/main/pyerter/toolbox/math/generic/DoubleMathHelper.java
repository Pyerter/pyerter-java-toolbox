package pyerter.toolbox.math.generic;

public class DoubleMathHelper extends MathHelper<Double> {

    public static final DoubleMathHelper HELPER = new DoubleMathHelper();

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
}
