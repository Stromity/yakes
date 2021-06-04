package com.stromity.yakes;

import com.stromity.yakes.listener.KeyboardListener;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * A class that handles all key presses.
 * <p>
 * When firing events, the system depends on the listener map. This means
 * you are required to call {@link #registerListener(String, KeyboardListener)}
 * before being able to fire any events.
 */
public class KeyboardEventSystem {
    /**
     * A comparator used, whenever some keys is about to be fired. This is to help
     * multi key presses, and just allows for better user interaction.
     * <example>
     * The user may bind a key to = CTRL + A
     * Before sorting it would require the user to always first press CTRL, then A.
     * Using this comparator, the press order doesn't matter.
     *
     * TODO: Have this private, and create a separate copy in {@link com.stromity.yakes.listener.MultiKeyKeyboardListener}?
     */
    public final static Comparator<String> ALPHABETIC_SIZE_COMPARATOR = Comparator.<String, String>comparing(s -> s).thenComparing(String::length);

    /**
     * The registered listeners.
     */
    private final Map<String, KeyboardListener<? , ?>> keyboardListeners;

    public KeyboardEventSystem(Map<String, KeyboardListener<?, ?>> keyboardListeners) {
        this.keyboardListeners = keyboardListeners;
    }

    /**
     * Fires all corresponding events to the keys being pressed in {@code keys}.
     * <p>
     * It is allowed for multiple keys to be fired in the same loop, which essentially means
     * different events can be bound to the same key. In some cases, this can be problematic,
     * but in others completely intentional. Thus, it would not be appropriate to set a limitation
     * for that case.
     * <p>
     * If either {@code keys} or {@code type} is equal to null, a {@link NullPointerException}
     * will be thrown.
     *
     * @param keys the keys in action.
     * @param type the type of key press.
     */
    public void fireEvent(List<String> keys, PressType type) {
        if (keys == null || type == null) {
            throw new NullPointerException("Key press list or PressType cannot be null, when firing key events.");
        }

        // Sort the key presses.
        keys.sort(ALPHABETIC_SIZE_COMPARATOR);

        for (KeyboardListener<?, ?> listener : keyboardListeners.values()) {
            if (listener.shouldFire(keys, type)) {
                listener.fire(keys, type);
            }
        }
    }

    /**
     * Fires only the corresponding listener to {@code id}.
     * <p>
     * If either {@code keys} or {@code type} is equal to null, a {@link NullPointerException}
     * will be thrown.
     *
     * @param id the id of the listener to be fired.
     * @param keys the keys in action.
     * @param type the type of key press.
     */
    public void fireEvent(String id, List<String> keys, PressType type) {
        if (id == null) throw new NullPointerException("Id can't be null.");

        KeyboardListener<?, ?> listener = keyboardListeners.get(id);

        if (listener == null) throw new NullPointerException("Given id resulted in a null listener.");

        // Sort the key presses.
        keys.sort(ALPHABETIC_SIZE_COMPARATOR);

        if (listener.shouldFire(keys, type)) {
            listener.fire(keys, type);
        }
    }

    /**
     * Registers a listener and sets it id.
     * <p>
     * If either {@code id} or {@code listener} is null a {@link NullPointerException} is thrown.
     *
     * @param id the id of the listener.
     * @param listener the listener to register.
     */
    public void registerListener(String id, KeyboardListener<?, ?> listener) {
        if (id == null || listener == null) {
            throw new NullPointerException("Id or KeyboardListener was null on registration.");
        }

        keyboardListeners.put(id, listener);
        listener.setId(id);
    }

    /**
     * Removes the listener corresponding to {@code id}.
     *
     * @param id the id of the listener.
     */
    public void removeListener(String id) {
        keyboardListeners.remove(id);
    }

}
