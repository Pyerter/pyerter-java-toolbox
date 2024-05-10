package pyerter.toolbox.math.generic;

public abstract class MathHelper <T extends Number> {

    protected Class<T> dtype;

    public MathHelper(Class<T> dtype) {
        this.dtype = dtype;
    }

    public Class<T> getDtype() {
        return dtype;
    }

    public abstract T add(T a, T b);
    public abstract T subtract(T a, T b);
    public abstract T mult(T a, T b);
    public abstract T divide(T a, T b);
    public abstract T mod(T a, T b);
    public abstract T inc(T a);
    public abstract T dec(T a);
    public abstract T pow(T a, T power);

}