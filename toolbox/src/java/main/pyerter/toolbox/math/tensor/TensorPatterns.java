package pyerter.toolbox.math.tensor;

public class TensorPatterns {

    /**
     * {@code start} defaults to 0.
     *
     * @see #arange(Class, Number, Number)
     */
    public static <T extends Number> Tensor<T> arange(Class<T> dtype, T end) {
        return arange(dtype, dtype.cast(0), end);
    }

    /**
     * {@code step} defaults to 1.
     *
     * @see #arange(Class, Number, Number, Number)
     */
    public static <T extends Number> Tensor<T> arange(Class<T> dtype, T start, T end) {
        return arange(dtype, start, end, dtype.cast(1));
    }

    /**
     *
     * @param dtype
     * @param start
     * @param end
     * @param step
     * @return
     * @param <T>
     */
    public static <T extends Number> Tensor<T> arange(Class<T> dtype, T start, T end, T step) {
        // T val = start + end;
        // int length = (int)Math.ceil((end.doubleValue() - start.doubleValue()) / step.doubleValue());
        TensorShape shape = new TensorShape();
        Tensor<T> tensor = new Tensor<T>();
        return tensor;
    }



}
