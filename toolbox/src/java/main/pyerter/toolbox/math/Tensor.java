package pyerter.toolbox.math;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;

public class Tensor <T extends Number> {

    protected TensorShape shape;
    protected T[] data;
    protected Class<T> dtype;

    public Tensor(Class<T> dtype, int ... shape) {
        this(dtype, new TensorShape(shape));
    }

    @SuppressWarnings("unchecked")
    public Tensor(Class<T> dtype, TensorShape shape) {
        this.shape = shape;
        data = (T[]) Array.newInstance(dtype, shape.prod());
        this.dtype = dtype;
    }

    public static Tensor<Integer> getIntTensor(int ... shape) {
        return new Tensor<>(Integer.class, shape);
    }

    public TensorShape getShape() {
        return shape;
    }

    public int dimensions() {
        return shape.dimensions();
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

}
