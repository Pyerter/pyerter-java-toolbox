package pyerter.toolbox.math.tensor;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class TensorShape implements Iterable<Integer> {

    protected int[] shape;
    protected int baseProd;

    public TensorShape(int ... dimensions) {
        this.shape = dimensions.clone();
        baseProd = prod();
    }

    public int get(int index) throws ArrayIndexOutOfBoundsException {
        return index >= 0 ? shape[index] : shape[shape.length - index];
    }

    public int dimensions() {
        return shape.length;
    }

    public int size() {
        return baseProd;
    }

    public int prod() {
        int total = 1;
        for (int i : shape) {
            total *= i;
        }
        return total;
    }

    public int prod(int start) {
        return prod(start, shape.length);
    }

    public int prod(int start, int end) {
        if (start < 0) start = this.shape.length + start;
        if (start < 0) throw new IllegalArgumentException("start must be bounded between in [-shape.length, shape.length - 1]");
        int total = 1;
        for (int i = start; i < end; i++) {
            total *= shape[i];
        }
        return total;
    }

    public int index(int ... indexes) {
        if (indexes.length > shape.length) throw new IllegalTensorStateException();

        int target = 0;
        int jump = 1;
        for (int i = shape.length - 1; i >= 0; i--) {
            if (i < indexes.length) {
                int currentIndexing = indexes[i];
                if (currentIndexing < 0) currentIndexing = shape[i] + currentIndexing;
                if (currentIndexing < 0 || currentIndexing >= shape[i])
                    throw new IndexOutOfBoundsException("Index " + indexes[i] + " out of bounds for range [0, " + shape[i] + "]");
                target += jump * currentIndexing;
            }
            jump *= shape[i];
        }
        return target;
    }

    public int[] toArray() {
        return shape.clone();
    }

    public TensorShape peel() {
        return peel(1);
    }

    public TensorShape peel(int layers) {
        if (layers < 0) throw new IllegalArgumentException("Cannot peel negative layers from " + toString());

        if (shape.length <= layers) return new TensorShape();
        int[] peeledShape = new int[shape.length - layers];
        System.arraycopy(shape, layers, peeledShape, 0, shape.length - layers);
        return new TensorShape(peeledShape);
    }

    public static int[] peel(int[] shape) {
        if (shape.length <= 1) return new int[0];
        int[] peeledShape = new int[shape.length - 1];
        System.arraycopy(shape, 1, peeledShape, 0, shape.length - 1);
        return peeledShape;
    }

    public boolean fitsIn(TensorShape target) {
        if (target.dimensions() < dimensions()) return false;
        for (int i = 0; i < shape.length; i++) {
            if (target.get(-i) < get(-i)) return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Shape " + Arrays.toString(shape);
    }

    @Override
    public Iterator<Integer> iterator() {
        return new TensorShapeIterator(this.shape);
    }

    @Override
    public void forEach(Consumer<? super Integer> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<Integer> spliterator() {
        return Iterable.super.spliterator();
    }

    protected static class TensorShapeIterator implements Iterator<Integer> {
        protected int index = 0;
        protected int[] vals;

        public TensorShapeIterator(int[] vals) {
            this.vals = vals.clone();
        }

        @Override
        public boolean hasNext() {
            return index < vals.length;
        }

        public Integer next() {
            return vals[index++];
        }
    }
}
