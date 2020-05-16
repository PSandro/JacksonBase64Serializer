package wrapper;

public class Base64Wrapper<T> {

    private final T wrapped;

    private Base64Wrapper(T wrapped) {
        this.wrapped = wrapped;
    }

    public T getWrapped() {
        return this.wrapped;
    }

    public static <T> Base64Wrapper<T> of(T wrapped) {
        return new Base64Wrapper<>(wrapped);
    }
}