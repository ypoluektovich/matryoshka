package org.msyu.matryoshka;

public interface Layer<I, O> {
    O setUp(I in) throws Exception;
    Layer<? super O, ?> nextLayer();
    void tearDown(O out) throws Exception;
}
