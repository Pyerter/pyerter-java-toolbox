package pyerter.toolbox.math.tensor;

/**
 * Used to store a reference to another tensor's data array. This allows the use of a proxy so that the data
 * does not get duplicated, saving space when creating Tensor "windows" over a larger tensor.
 */
public class TensorProxy <T extends Number> extends Tensor<T> {

    protected Tensor<T> target;
    protected int startCoverage;
    protected int endCoverage;

    public TensorProxy(Class<T> dtype, Tensor<T> target, int ... initialIndexing) {
        super(dtype, target.getShape().peel(initialIndexing.length), target.data);
        startCoverage = target.getShape().index(initialIndexing);
        endCoverage = startCoverage + shape.prod();
    }

    @Override
    public String toString() {
        return "TensorProxy for " + super.toString();
    }

    @Override
    public T get(int ... indexes) throws IllegalTensorStateException, IndexOutOfBoundsException {
        if (indexes.length != shape.dimensions()) {
            throw new IllegalTensorStateException(this, indexes, shape.dimensions());
        }
        return data[startCoverage + shape.index(indexes)];
    }

    @Override
    public T getRaw(int index) throws IndexOutOfBoundsException {
        return data[startCoverage + index];
    }

    @Override
    public int getRawDataFirstIndex() {
        return startCoverage;
    }

    @Override
    public int getRawDataLength() {
        return endCoverage - startCoverage;
    }

    @Override
    public void set(T value, int ... indexes) throws IllegalTensorStateException, IndexOutOfBoundsException {
        if (indexes.length != shape.dimensions()) {
            throw new IllegalTensorStateException(this, indexes, shape.dimensions());
        }
        data[startCoverage + shape.index(indexes)] = value;
    }

    @Override
    public void setRaw(T value, int index) throws IndexOutOfBoundsException {
        data[startCoverage + index] = value;
    }

    @Override
    public Tensor<T> getTensor(int ... indexes) throws IllegalTensorStateException, IndexOutOfBoundsException {
        if (indexes.length >= shape.dimensions()) {
            throw new IllegalTensorStateException(this, indexes, shape.dimensions());
        }

        int startIndex = shape.index(indexes) + startCoverage;
        TensorShape peeledTensorShape = shape.peel(indexes.length);
        Tensor<T> peeledTensor = new Tensor<>(dtype, peeledTensorShape);
        System.arraycopy(data, startIndex, peeledTensor.data, 0, peeledTensorShape.prod());
        return peeledTensor;
    }
}
