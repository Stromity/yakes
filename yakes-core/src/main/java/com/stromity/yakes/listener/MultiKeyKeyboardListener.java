package com.stromity.yakes.listener;

import com.stromity.yakes.KeyboardEventSystem;
import com.stromity.yakes.PressType;
import com.stromity.yakes.listener.event.KeyboardPressCallback;
import com.stromity.yakes.listener.event.KeyboardPressCallbackProvider;

import java.util.List;
import java.util.Map;

/**
 * A listener that listens for multi key presses. This listener will only fire
 * it's event's, if the combination is the only one being pressed.
 * <p>
 * When multiple key's is being registered, they will be sorted alphabetically &
 * based on it's size.
 * <example>
 * List of keys: CTRL, SHIFT, A
 * Sorted to: A, CTRL, SHIFT
 * Stored as: A_CTRL_SHIFT
 * <p>
 * This is done, so the user doesn't have to press the keys in exact way it was registered,
 * but can simply just press the combination of keys, and the event will be fired.
 */
public class MultiKeyKeyboardListener extends AbstractKeyboardListener<List<String>, KeyboardPressCallback.Multi> {
    /**
     * The separator used in between strings in lists being converted.
     */
    private static final String KEY_SEPARATOR = "_";

    public MultiKeyKeyboardListener(
            Map<String, KeyboardPressCallbackProvider<List<String>, KeyboardPressCallback.Multi>> boundKeys,
            PressType acceptedPressType) {
        super(boundKeys, acceptedPressType);
    }

    /**
     * Converts a type to a string, so we can store it in {@link boundKeys}.
     *
     * @param list the key type to be converted to a string.
     * @return list converted.
     */
    protected String convertToString(List<String> list) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < list.size(); ++i) {
            sb.append(list.get(i));

            // Append the an underscore, if a key with an index
            // higher than the current exists.
            if (i + 1 < list.size()) {
                sb.append(KEY_SEPARATOR);
            }
        }

        return sb.toString();
    }

    /**
     * Fires this listener.
     *
     * @param keys the keys being pressed.
     * @param type the type of press.
     */
    @Override
    public void fire(List<String> keys, PressType type) {
        KeyboardPressCallback.Multi callback = provideEventCallback(convertToString(keys));

        if (callback != null) {
            callback.fire(keys);
        }
    }

    /**
     * Binds a list of keys to this listener.
     * <p>
     * Before binding, the list is sorted using {@link KeyboardEventSystem#ALPHABETIC_SIZE_COMPARATOR}.
     *
     * @param keys the keys to bind.
     * @param eventProvider the event factory to be bounded to.
     */
    public void bind(List<String> keys, KeyboardPressCallbackProvider<List<String>, KeyboardPressCallback.Multi> eventProvider) {
        keys.sort(KeyboardEventSystem.ALPHABETIC_SIZE_COMPARATOR);
        bind(convertToString(keys), eventProvider);
    }

    /**
     * Removes the corresponding {@link KeyboardPressCallbackProvider}.
     * <p>
     * When removed, it is assumed the list is already sorted the way
     * it is contained. This means, you have to provide the exact order,
     * when removing multiple keys.
     *
     * @param keys the keys to remove the event from.
     */
    public void remove(List<String> keys) {
        remove(convertToString(keys));
    }

    /**
     * Checks whether or not the list {@code keysPressed}
     * corresponds to any registered event.
     *
     * @param keysPressed the keys being pressed.
     * @return whether or an event should be fired.
     */
    @Override
    public boolean shouldFire(List<String> keysPressed, PressType type) {
        return isPressTypeAccepted(type) && boundKeys.containsKey(convertToString(keysPressed));
    }
}
