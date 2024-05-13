package pyerter.toolbox.math.tensor;

import java.util.Arrays;

public class TensorKernel <T extends Number> extends Tensor<T> {

    protected Tensor<T> targetTensor;
    protected int kernelIndex;

    public TensorKernel(Class<T> dtype, Tensor<T> target, TensorShape kernelShape, int... initialIndexing) {
        super(dtype, kernelShape);
        if (!kernelShape.fitsIn(target.shape)) throw new IllegalTensorStateException("Tensor Kernel of " + kernelShape + " does not fit in " + target.toString());
        if (initialIndexing.length > target.shape.dimensions())
            throw new IllegalTensorStateException("Kernel indexing of " + Arrays.toString(initialIndexing) + " and " + kernelShape.toString() + " does not match to Tensor with " + target.shape.toString() + ". Expected total dimensions of indexing to be " + target.shape.dimensions() + " or less.");

        this.targetTensor = target;
        kernelIndex = target.shape.index(initialIndexing);
    }

    public void placeKernel(int ... indexing) throws IllegalTensorStateException, IndexOutOfBoundsException {
        if (indexing.length > targetTensor.shape.dimensions())
            throw new IllegalTensorStateException("Kernel indexing of " + Arrays.toString(indexing) + " and " + shape.toString() + " does not match to Tensor with " + targetTensor.shape.toString() + ". Expected indexing to have " + targetTensor.shape.dimensions() + " or less dimensions.");
        placeIndex(targetTensor.shape.index(indexing));
    }

    public void placeIndex(int index) throws IndexOutOfBoundsException {
        kernelIndex = index;
        if (shape.size() == 1) {
            data[0] = targetTensor.data[kernelIndex];
        }
        else if (shape.size() > 1) {
            // Update internal data
            // TODO: Offset starting currentIndex so that the center of the kernel is focused on
            // the original center point index
            int[] indexes = new int[shape.dimensions()];
            int i = 0;
            int currentIndex = kernelIndex;
            while (i < elements()) {
                data[i] = targetTensor.data[currentIndex];
                for (int j = indexes.length - 1; j >= 0; j--) {
                    indexes[j]++;
                    if (indexes[j] < shape.get(j)) break;
                    indexes[j] = 0;
                    currentIndex += shape.indexHopAtDimension(j);
                }
                i++;
            }
        } else {

        }
    }

    public T getSingle() throws IndexOutOfBoundsException {
        return data[0];
    }
}
