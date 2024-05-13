package pyerter.toolbox.math.tensor;

import java.util.Iterator;

public class TensorIterator <T extends Number> implements Iterator<T> {

    protected T[] data;
    protected int start;
    protected int end;
    protected int current;

    public TensorIterator(Tensor<T> target) {
        data = target.getRawData();
        start = target.getRawDataFirstIndex();
        end = start + target.getRawDataLength();
        current = start;
    }

    @Override
    public boolean hasNext() {
        return current < end;
    }

    @Override
    public T next() {
        return data[current++];
    }
}
