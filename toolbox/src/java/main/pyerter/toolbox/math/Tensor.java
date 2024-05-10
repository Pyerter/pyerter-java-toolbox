package pyerter.toolbox.math;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;

public class Tensor <T extends Number> {

    protected TensorShape shape;
    protected T[] data;
    protected Class<T> dtype;
    protected boolean valuesLocked;

    protected Tensor() {

    }

    public Tensor(Class<T> dtype, int ... shape) {
        this(dtype, new TensorShape(shape));
    }

    @SuppressWarnings("unchecked")
    public Tensor(Class<T> dtype, TensorShape shape) {
        this.shape = shape;
        data = (T[]) Array.newInstance(dtype, shape.prod());
        this.dtype = dtype;
        this.valuesLocked = false;
    }

    public static Tensor<Integer> getIntTensor(int ... shape) {
        return new Tensor<>(Integer.class, shape);
    }

    public TensorShape getShape() {
        return shape;
    }

    public boolean valuesAreLocked() {
        return valuesLocked;
    }

    public void setValuesAreLocked(boolean valuesLocked) {
        this.valuesLocked = valuesLocked;
    }

    public int dimensions() {
        return shape.dimensions();
    }

    public int elements() {
        return shape.size();
    }

    @Override
    public String toString() {
        return "Tensor<" + dtype.getSimpleName() + "> of " + shape.toString();
    }

    public T get(int ... indexes) throws IllegalTensorStateException, IndexOutOfBoundsException {
        if (indexes.length != shape.dimensions()) {
            throw new IllegalTensorStateException(this, indexes, shape.dimensions());
        }
        return data[shape.index(indexes)];
    }

    public T getRaw(int index) throws IndexOutOfBoundsException {
        return data[index];
    }

    /**
     * Only use this method if you fully understand how the data in the {@code Tensor} is stored. This method exists
     * solely for the purpose of allowing manual operating over an array so that time is not wasted by calling
     * intermediate methods such as {@code get} or {@code getRaw}.
     * @return a reference to the internal array of data
     */
    public T[] getRawData() {
        return data;
    }

    public void set(T value, int ... indexes) throws IllegalTensorStateException, IndexOutOfBoundsException {
        if (indexes.length != shape.dimensions()) {
            throw new IllegalTensorStateException(this, indexes, shape.dimensions());
        }
        data[shape.index(indexes)] = value;
    }

    public void setRaw(T value, int index) throws IndexOutOfBoundsException {
        data[index] = value;
    }

    public Tensor<T> getTensor(int ... indexes) throws IllegalTensorStateException, IndexOutOfBoundsException {
        if (indexes.length >= shape.dimensions()) {
            throw new IllegalTensorStateException(this, indexes, shape.dimensions());
        }

        int startIndex = shape.index(indexes);
        TensorShape peeledTensorShape = shape.peel(indexes.length);
        Tensor<T> peeledTensor = new Tensor<>(dtype, peeledTensorShape);
        System.arraycopy(data, startIndex, peeledTensor.data, 0, peeledTensorShape.prod());
        return peeledTensor;
    }

    protected class LazyOperatedTensor <R extends Number> extends Tensor<R> {

        protected boolean initialized = false;

        public LazyOperatedTensor() {
            super();
        }

    }

}
