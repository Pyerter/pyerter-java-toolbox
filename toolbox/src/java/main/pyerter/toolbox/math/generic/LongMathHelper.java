package pyerter.toolbox.math.generic;

public class LongMathHelper extends MathHelper<Long> {

    public static final LongMathHelper HELPER = new LongMathHelper();

    public LongMathHelper() {
        super(Long.class);
    }

    @Override
    public Long add(Long a, Long b) {
        return a + b;
    }

    @Override
    public Long subtract(Long a, Long b) {
        return a - b;
    }

    @Override
    public Long mult(Long a, Long b) {
        return a * b;
    }

    @Override
    public Long divide(Long a, Long b) {
        return a / b;
    }

    @Override
    public Long mod(Long a, Long b) {
        return a % b;
    }

    @Override
    public Long inc(Long a) {
        return ++a;
    }

    @Override
    public Long dec(Long a) {
        return --a;
    }

    @Override
    public Long pow(Long a, Long power) {
        long result = 1;
        for (int i = 0; i < power; i++) {
            result *= a;
        }
        return result;
    }

    @Override
    public Long ceil(Double a) {
        return (long)Math.ceil(a);
    }

    @Override
    public Long floor(Double a) {
        return (long)Math.floor(a);
    }

    @Override
    public Long round(Double a) {
        return Math.round(a);
    }
}
