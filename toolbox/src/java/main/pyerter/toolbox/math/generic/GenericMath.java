package pyerter.toolbox.math.generic;

public class GenericMath {

    @SuppressWarnings("unchecked")
    public static <T extends Number> MathHelper<T> getHelper(Class<T> dtype) {
        String intClassName = Integer.class.getName();
        return switch (dtype.getName()) {
            case "java.lang.Integer" -> (MathHelper<T>) IntMathHelper.HELPER;
            default -> (MathHelper<T>) DoubleMathHelper.HELPER;
        };
    }

    @SuppressWarnings("unchecked")
    public static <T extends Number> T add(T a, T b) throws GenericMathException {
        return switch (a) {
            case Integer c -> (T)IntMathHelper.HELPER.add((int)a, (int)b);
            case Double c -> (T)DoubleMathHelper.HELPER.add((double)a, (double)b);
            default -> throw new GenericMathException("Invalid Number type??");
        };
    }

}
