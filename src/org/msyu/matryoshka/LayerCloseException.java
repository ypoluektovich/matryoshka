package org.msyu.matryoshka;

public final class LayerCloseException extends MatryoshkaException {

    private final int unclosedStackSize;

    LayerCloseException(Throwable cause, int unclosedStackSize) {
        super(cause);
        this.unclosedStackSize = unclosedStackSize;
    }

    @Override
    public final String getMessage() {
        return "and there are " + unclosedStackSize + " more layers to close";
    }

}
