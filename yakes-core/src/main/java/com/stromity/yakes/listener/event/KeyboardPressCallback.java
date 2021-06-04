package com.stromity.yakes.listener.event;

import java.util.List;

/**
 * The interface representing actions whenever a key has been pressed.
 * <p>
 * Exceptions will be handled by printing their stacktrace.
 *
 * TODO: Custom exception handling?
 */
public interface KeyboardPressCallback<T> {

    /**
     * Fires this event.
     */
    void fire(T t);

    /**
     * A callback representation, of whenever multiple keys is being pressed.
     */
    interface Multi extends KeyboardPressCallback<List<String>> {

        /**
         * Fires this event and passes all the keys being pressed as a {@link List}.
         *
         * @param keys the keys being pressed.
         */
        @Override
        void fire(List<String> keys);
    }

    /**
     * A callback representation, of whenever only a single key is being pressed.
     */
    interface Single extends KeyboardPressCallback<String> {

        /**
         * Fires this event and passes the key pressed as {@code key}.
         *
         * @param key the key being pressed.
         */
        @Override
        void fire(String key);
    }
}
