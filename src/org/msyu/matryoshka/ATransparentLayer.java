package org.msyu.matryoshka;

public abstract class ATransparentLayer<T> implements Layer<T, T> {

    @Override
    public final T setUp(T ctx) {
        setUp0(ctx);
        return ctx;
    }

    protected abstract void setUp0(T ctx);

}
