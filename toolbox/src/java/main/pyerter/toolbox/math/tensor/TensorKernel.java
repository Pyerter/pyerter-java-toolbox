package pyerter.toolbox.math.tensor;

import java.util.Arrays;

public class TensorKernel <T extends Number> extends Tensor<T> {

    protected Tensor<T> targetTensor;
    protected int kernelIndex;

    public TensorKernel(Class<T> dtype, Tensor<T> target, TensorShape kernelShape, int... initialIndexing) {
        super(dtype, kernelShape, target.data);
        if (!kernelShape.fitsIn(target.shape)) throw new IllegalTensorStateException("Tensor Kernel of " + kernelShape + " does not fit in " + target.toString());
        if (initialIndexing.length > target.shape.dimensions())
            throw new IllegalTensorStateException("Kernel indexing of " + Arrays.toString(initialIndexing) + " and " + kernelShape.toString() + " does not match to Tensor with " + target.shape.toString() + ". Expected total dimensions of indexing to be " + target.shape.dimensions() + " or less.");

        this.targetTensor = target;
        kernelIndex = target.shape.index(initialIndexing);
    }

    public void placeKernel(int ... indexing) {
        if (indexing.length > targetTensor.shape.dimensions())
            throw new IllegalTensorStateException("Kernel indexing of " + Arrays.toString(indexing) + " and " + shape.toString() + " does not match to Tensor with " + targetTensor.shape.toString() + ". Expected indexing to have " + targetTensor.shape.dimensions() + " or less dimensions.");
        kernelIndex = targetTensor.shape.index(indexing);
    }
}
