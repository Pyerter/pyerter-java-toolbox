package pyerter.toolbox.math.tensor;

import java.util.Iterator;

/**
 * A class that allows convolution over a tensor. This allows either iterating directly element-wise over the Tensor
 * or convolving a window over the Tensor. Additionally, the convolver can operate over the Tensor, returning a
 * new Tensor according to the given operation.
 */
public class TensorConvolverIterator<T extends Number> implements Iterator<TensorKernel<T>> {

    protected Tensor<T> target;
    protected T[] data;
    protected int start;
    protected int end;
    protected int current;

    public TensorConvolverIterator(Tensor<T> target) {
        this.target = target;
        this.data = target.getRawData();
        this.start = target.getRawDataFirstIndex();
        this.end = start + target.getRawDataLength();
        this.current = start;
    }

    @Override
    public boolean hasNext() {
        return current < end;
    }

    @Override
    public TensorKernel<T> next() {
        return null;
    }
}
