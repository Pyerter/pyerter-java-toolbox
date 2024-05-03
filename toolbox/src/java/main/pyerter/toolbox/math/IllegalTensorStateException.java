package pyerter.toolbox.math;

import java.util.Arrays;

public class IllegalTensorStateException extends IllegalStateException {

    public enum TensorStateFlag {
        MULTI_DIMENSIONAL(1),
        FLAT(2);

        protected final int value;

        TensorStateFlag(int value) {
            this.value = value;
        }

        TensorStateFlag(TensorStateFlag ... flags) {
            this.value = combine(flags);
        }

        public int getValue() {
            return value;
        }

        public boolean hasFlag(TensorStateFlag flag) {
            return bitmaskHasFlag(value, flag);
        }

        public boolean hasFlag(int flag) {
            return (value & flag) != 0;
        }

        public static int combine(TensorStateFlag ... flags) {
            int result = 0;
            for (TensorStateFlag f : flags) {
                result |= f.getValue();
            }
            return result;
        }

        public static boolean bitmaskHasFlag(int bitmask, TensorStateFlag flag) {
            return (bitmask & flag.getValue()) != 0;
        }
    }

    protected int stateFlags;

    public IllegalTensorStateException(TensorStateFlag ... flags) {
        super();
        stateFlags = TensorStateFlag.combine(flags);
    }

    public IllegalTensorStateException(String message, TensorStateFlag ... flags) {
        super(message);
        stateFlags = TensorStateFlag.combine(flags);
    }

    public IllegalTensorStateException(Tensor<?> tensor, int[] indexes, int neededIndexes) {
        super("Shape mismatch: given " + indexes.length + "(" + Arrays.toString(indexes) + ") indexes when " + neededIndexes + " were needed for " + tensor.toString());
    }

    public boolean multiDimensional() {
        return TensorStateFlag.bitmaskHasFlag(stateFlags, TensorStateFlag.MULTI_DIMENSIONAL);
    }

    public boolean flat() {
        return TensorStateFlag.bitmaskHasFlag(stateFlags, TensorStateFlag.FLAT);
    }

}
