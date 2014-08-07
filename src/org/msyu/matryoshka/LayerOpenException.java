package org.msyu.matryoshka;

public final class LayerOpenException extends MatryoshkaException {

    private final int openedStackSize;

    LayerOpenException(Exception cause, int openedStackSize) {
        super(cause);
        this.openedStackSize = openedStackSize;
    }

    @Override
    public final String getMessage() {
        return "after successfully opening " + openedStackSize + " layers";
    }

}
