package com.stromity.yakes.listener.event;

/**
 * An interface to help creating new events, when it's needed.
 */
public interface KeyboardPressCallbackProvider<T, C extends KeyboardPressCallback<T>> {

    /**
     * Creates a new {@link KeyboardPressCallback}.
     * <p>
     * This must never return {@code null}.
     *
     * @return the new event.
     */
    C create();
}
