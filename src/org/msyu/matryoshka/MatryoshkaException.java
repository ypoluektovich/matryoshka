package org.msyu.matryoshka;

public abstract class MatryoshkaException extends Exception {
    MatryoshkaException(Throwable cause) {
        super(cause);
    }
}
