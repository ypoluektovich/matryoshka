package org.msyu.matryoshka;

import java.util.ArrayDeque;

public class Matryoshka {

    public static void play(Layer<Void, ?> layer) throws MatryoshkaException {
        play(layer, null);
    }

    public static <T> void play(Layer<T, ?> layer, T input) throws MatryoshkaException {
        ArrayDeque<LayerFrame<?>> stack = new ArrayDeque<>();
        MatryoshkaException exception = null;
        Object currentInput = input;
        Layer<?, ?> currentLayer = layer;
        while (currentLayer != null) {
            LayerFrame<?> frame;
            try {
                frame = open(currentLayer, currentInput);
            } catch (Exception e) {
                exception = new LayerOpenException(e, stack.size());
                break;
            }
            stack.push(frame);
            currentInput = frame.layerOutput;
            currentLayer = frame.layer.nextLayer();
        }
        while (!stack.isEmpty()) {
            try {
                close(stack.pop());
            } catch (Exception e) {
                if (exception == null) {
                    exception = new LayerCloseException(e, stack.size());
                } else {
                    exception.addSuppressed(e);
                }
            }
        }
        if (exception != null) {
            throw exception;
        }
    }

    private static class LayerFrame<O> {
        final Layer<?, O> layer;
        final O layerOutput;

        LayerFrame(Layer<?, O> layer, O layerOutput) {
            this.layer = layer;
            this.layerOutput = layerOutput;
        }
    }

    @SuppressWarnings("unchecked")
    private static <I, O> LayerFrame<O> open(Layer<I, O> layer, Object input) throws Exception {
        O output = layer.setUp((I) input);
        return new LayerFrame<>(layer, output);
    }

    private static <O> void close(LayerFrame<O> frame) throws Exception {
        frame.layer.tearDown(frame.layerOutput);
    }


}
