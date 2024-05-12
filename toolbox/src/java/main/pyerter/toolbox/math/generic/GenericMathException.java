package pyerter.toolbox.math.generic;

public class GenericMathException extends RuntimeException {

    public GenericMathException() {
        super("Invalid Number type! Accepts exactly one of: Byte (1), Short (2), Integer (4), Long (8), Float (4), Double (8).");
    }

    public GenericMathException(String message) {
        super(message);
    }
}
