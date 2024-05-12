package pyerter.toolbox.math.generic;

public class ByteMathHelper extends MathHelper<Byte> {

    public static final long MAX_VALUE_AS_LONG = Byte.MAX_VALUE;
    public static final long MIN_VALUE_AS_LONG = Byte.MIN_VALUE;

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

    @Override
    public Byte ceil(Double a) {
        long result = (long)Math.ceil(a);
        if (result >= MAX_VALUE_AS_LONG) return Byte.MAX_VALUE;
        if (result <= MIN_VALUE_AS_LONG) return Byte.MIN_VALUE;
        return (byte)result;
    }

    @Override
    public Byte floor(Double a) {
        long result = (long)Math.floor(a);
        if (result >= MAX_VALUE_AS_LONG) return Byte.MAX_VALUE;
        if (result <= MIN_VALUE_AS_LONG) return Byte.MIN_VALUE;
        return (byte)result;
    }

    @Override
    public Byte round(Double a) {
        long result = Math.round(a);
        if (result >= MAX_VALUE_AS_LONG) return Byte.MAX_VALUE;
        if (result <= MIN_VALUE_AS_LONG) return Byte.MIN_VALUE;
        return (byte)result;
    }
}
