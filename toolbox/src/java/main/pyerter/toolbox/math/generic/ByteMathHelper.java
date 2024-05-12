package pyerter.toolbox.math.generic;

public class ByteMathHelper extends MathHelper<Byte> {

    public static final ByteMathHelper HELPER = new ByteMathHelper();

    public ByteMathHelper() {
        super(Byte.class);
    }

    @Override
    public Byte add(Byte a, Byte b) {
        return (byte)(a + b);
    }

    @Override
    public Byte subtract(Byte a, Byte b) {
        return (byte)(a - b);
    }

    @Override
    public Byte mult(Byte a, Byte b) {
        return (byte)(a * b);
    }

    @Override
    public Byte divide(Byte a, Byte b) {
        return (byte)(a / b);
    }

    @Override
    public Byte mod(Byte a, Byte b) {
        return (byte)(a % b);
    }

    @Override
    public Byte inc(Byte a) {
        return ++a;
    }

    @Override
    public Byte dec(Byte a) {
        return --a;
    }

    @Override
    public Byte pow(Byte a, Byte power) {
        int result = 1;
        for (int i = 0; i < power; i++) {
            result *= a;
        }
        return (byte)result;
    }
}
