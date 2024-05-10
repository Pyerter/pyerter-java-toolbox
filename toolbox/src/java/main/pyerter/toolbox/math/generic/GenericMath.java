package pyerter.toolbox.math.generic;

import java.util.Iterator;

public class GenericMath {

    @SuppressWarnings("unchecked")
    public static <T extends Number> MathHelper<T> getHelper(Class<T> dtype) {
        String intClassName = Integer.class.getName();
        return switch (dtype.getName()) {
            case "java.lang.Integer" -> (MathHelper<T>) IntMathHelper.HELPER;
            case "java.lang.Float" -> (MathHelper<T>) FloatMathHelper.HELPER;
            default -> (MathHelper<T>) DoubleMathHelper.HELPER;
        };
    }

    @SuppressWarnings("unchecked")
    public static <T extends Number> T add(T a, T b) throws GenericMathException {
        return switch (a) {
            case Integer c -> (T)IntMathHelper.HELPER.add((int)a, (int)b);
            case Float c -> (T)FloatMathHelper.HELPER.add((float)a, (float)b);
            case Double c -> (T)DoubleMathHelper.HELPER.add((double)a, (double)b);
            default -> throw new GenericMathException();
        };
    }

    @SuppressWarnings("unchecked")
    public static <T extends Number> T mult(T a, T b) throws GenericMathException {
        return switch (a) {
            case Integer c -> (T)IntMathHelper.HELPER.mult((int)a, (int)b);
            case Float c -> (T)FloatMathHelper.HELPER.mult((float)a, (float)b);
            case Double c -> (T)DoubleMathHelper.HELPER.mult((double)a, (double)b);
            default -> throw new GenericMathException();
        };
    }

}
