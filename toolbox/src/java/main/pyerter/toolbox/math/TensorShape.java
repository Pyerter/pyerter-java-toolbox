package pyerter.toolbox.math;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class TensorShape implements Iterable<Integer> {

    protected int[] shape;

    public TensorShape(int ... dimensions) {
        this.shape = dimensions.clone();
    }

    public int get(int index) throws ArrayIndexOutOfBoundsException {
        return index >= 0 ? shape[index] : shape[shape.length - index];
    }

    public int dimensions() {
        return shape.length;
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
        int total = 1;
        for (int i = start; i < end; i++) {
            total *= i;
        }
        return total;
    }

    public int index(int ... indexes) {
        if (indexes.length != shape.length) throw new IllegalTensorStateException();
        int target = 0;
        int jump = 1;
        for (int i = 0; i < indexes.length; i++) {
            jump *= shape[shape.length - (i + 1)];
            target += jump * indexes[i];
        }
        return target;
    }

    public int[] toArray() {
        return shape.clone();
    }

    public TensorShape peel() {
        if (shape.length <= 1) return new TensorShape();
        int[] peeledShape = new int[shape.length - 1];
        System.arraycopy(shape, 1, peeledShape, 0, shape.length - 1);
        return new TensorShape(peeledShape);
    }

    public static int[] peel(int[] shape) {
        if (shape.length <= 1) return new int[0];
        int[] peeledShape = new int[shape.length - 1];
        System.arraycopy(shape, 1, peeledShape, 0, shape.length - 1);
        return peeledShape;
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