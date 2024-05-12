package pyerter.toolbox.math.generic;

import java.util.Iterator;

public class GenericMath {

    @SuppressWarnings("unchecked")
    public static <T extends Number> MathHelper<T> getHelper(Class<T> dtype) throws GenericMathException {
        return switch (dtype.getName()) {
            case "java.lang.Byte" -> (MathHelper<T>) ByteMathHelper.HELPER;
            case "java.lang.Short" -> (MathHelper<T>) ShortMathHelper.HELPER;
            case "java.lang.Integer" -> (MathHelper<T>) IntMathHelper.HELPER;
            case "java.lang.Long" -> (MathHelper<T>) LongMathHelper.HELPER;
            case "java.lang.Float" -> (MathHelper<T>) FloatMathHelper.HELPER;
            case "java.lang.Double" -> (MathHelper<T>) DoubleMathHelper.HELPER;
            default -> throw new GenericMathException();
        };
    }

    @SuppressWarnings("unchecked")
    public static <T extends Number> T add(T a, T b) throws GenericMathException {
        return switch (a) {
            case Byte c -> (T)ByteMathHelper.HELPER.add((byte)a, (byte)b);
            case Short c -> (T)ShortMathHelper.HELPER.add((short)a, (short)b);
            case Integer c -> (T)IntMathHelper.HELPER.add((int)a, (int)b);
            case Long c -> (T)LongMathHelper.HELPER.add((long)a, (long)b);
            case Float c -> (T)FloatMathHelper.HELPER.add((float)a, (float)b);
            case Double c -> (T)DoubleMathHelper.HELPER.add((double)a, (double)b);
            default -> throw new GenericMathException();
        };
    }

    @SuppressWarnings("unchecked")
    public static <T extends Number> T subtract(T a, T b) throws GenericMathException {
        return switch (a) {
            case Byte c -> (T)ByteMathHelper.HELPER.subtract((byte)a, (byte)b);
            case Short c -> (T)ShortMathHelper.HELPER.subtract((short)a, (short)b);
            case Integer c -> (T)IntMathHelper.HELPER.subtract((int)a, (int)b);
            case Long c -> (T)LongMathHelper.HELPER.subtract((long)a, (long)b);
            case Float c -> (T)FloatMathHelper.HELPER.subtract((float)a, (float)b);
            case Double c -> (T)DoubleMathHelper.HELPER.subtract((double)a, (double)b);
            default -> throw new GenericMathException();
        };
    }

    @SuppressWarnings("unchecked")
    public static <T extends Number> T mult(T a, T b) throws GenericMathException {
        return switch (a) {
            case Byte c -> (T)ByteMathHelper.HELPER.mult((byte)a, (byte)b);
            case Short c -> (T)ShortMathHelper.HELPER.mult((short)a, (short)b);
            case Integer c -> (T)IntMathHelper.HELPER.mult((int)a, (int)b);
            case Long c -> (T)LongMathHelper.HELPER.mult((long)a, (long)b);
            case Float c -> (T)FloatMathHelper.HELPER.mult((float)a, (float)b);
            case Double c -> (T)DoubleMathHelper.HELPER.mult((double)a, (double)b);
            default -> throw new GenericMathException();
        };
    }

    @SuppressWarnings("unchecked")
    public static <T extends Number> T divide(T a, T b) throws GenericMathException {
        return switch (a) {
            case Byte c -> (T)ByteMathHelper.HELPER.divide((byte)a, (byte)b);
            case Short c -> (T)ShortMathHelper.HELPER.divide((short)a, (short)b);
            case Integer c -> (T)IntMathHelper.HELPER.divide((int)a, (int)b);
            case Long c -> (T)LongMathHelper.HELPER.divide((long)a, (long)b);
            case Float c -> (T)FloatMathHelper.HELPER.divide((float)a, (float)b);
            case Double c -> (T)DoubleMathHelper.HELPER.divide((double)a, (double)b);
            default -> throw new GenericMathException();
        };
    }

    @SuppressWarnings("unchecked")
    public static <T extends Number> T mod(T a, T b) throws GenericMathException {
        return switch (a) {
            case Byte c -> (T)ByteMathHelper.HELPER.mod((byte)a, (byte)b);
            case Short c -> (T)ShortMathHelper.HELPER.mod((short)a, (short)b);
            case Integer c -> (T)IntMathHelper.HELPER.mod((int)a, (int)b);
            case Long c -> (T)LongMathHelper.HELPER.mod((long)a, (long)b);
            case Float c -> (T)FloatMathHelper.HELPER.mod((float)a, (float)b);
            case Double c -> (T)DoubleMathHelper.HELPER.mod((double)a, (double)b);
            default -> throw new GenericMathException();
        };
    }

    @SuppressWarnings("unchecked")
    public static <T extends Number> T inc(T a) throws GenericMathException {
        return switch (a) {
            case Byte c -> (T)ByteMathHelper.HELPER.inc((byte)a);
            case Short c -> (T)ShortMathHelper.HELPER.inc((short)a);
            case Integer c -> (T)IntMathHelper.HELPER.inc((int)a);
            case Long c -> (T)LongMathHelper.HELPER.inc((long)a);
            case Float c -> (T)FloatMathHelper.HELPER.inc((float)a);
            case Double c -> (T)DoubleMathHelper.HELPER.inc((double)a);
            default -> throw new GenericMathException();
        };
    }

    @SuppressWarnings("unchecked")
    public static <T extends Number> T dec(T a) throws GenericMathException {
        return switch (a) {
            case Byte c -> (T)ByteMathHelper.HELPER.dec((byte)a);
            case Short c -> (T)ShortMathHelper.HELPER.dec((short)a);
            case Integer c -> (T)IntMathHelper.HELPER.dec((int)a);
            case Long c -> (T)LongMathHelper.HELPER.dec((long)a);
            case Float c -> (T)FloatMathHelper.HELPER.dec((float)a);
            case Double c -> (T)DoubleMathHelper.HELPER.dec((double)a);
            default -> throw new GenericMathException();
        };
    }

    @SuppressWarnings("unchecked")
    public static <T extends Number> T pow(T a, T power) throws GenericMathException {
        return switch (a) {
            case Byte c -> (T)ByteMathHelper.HELPER.pow((byte)a, (byte)power);
            case Short c -> (T)ShortMathHelper.HELPER.pow((short)a, (short)power);
            case Integer c -> (T)IntMathHelper.HELPER.pow((int)a, (int)power);
            case Long c -> (T)LongMathHelper.HELPER.pow((long)a, (long)power);
            case Float c -> (T)FloatMathHelper.HELPER.pow((float)a, (float)power);
            case Double c -> (T)DoubleMathHelper.HELPER.pow((double)a, (double)power);
            default -> throw new GenericMathException();
        };
    }

}
