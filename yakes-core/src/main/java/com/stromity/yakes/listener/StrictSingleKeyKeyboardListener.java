package com.stromity.yakes.listener;

import com.stromity.yakes.PressType;
import com.stromity.yakes.listener.event.KeyboardPressCallback;
import com.stromity.yakes.listener.event.KeyboardPressCallbackProvider;

import java.util.List;
import java.util.Map;

/**
 * A listener that listens for single key presses. This listener respects,
 * if other key's is being pressed and will not fire any actions, unless only
 * the bound key is pressed.
 */
public class StrictSingleKeyKeyboardListener extends AbstractKeyboardListener<String, KeyboardPressCallback.Single> {
    public StrictSingleKeyKeyboardListener(
            Map<String, KeyboardPressCallbackProvider<String, KeyboardPressCallback.Single>> boundKeys,
            PressType acceptedPressType) {
        super(boundKeys, acceptedPressType);
    }

    /**
     * Fires this listener.
     *
     * @param keys the keys being pressed.
     * @param type the current press type.
     */
    @Override
    public void fire(List<String> keys, PressType type) {
        String key = keys.get(0); // only 1 key is being pressed.
        KeyboardPressCallback.Single callback = provideEvent(key);

        if (callback != null) {
            callback.fire(key);
        }
    }

    /**
     * A strict check that requires only 1 key being pressed at the time in order to pass.
     *
     * @param keysPressed the keys being pressed.
     * @return whether or not this listener should fire it's event.
     */
    @Override
    public boolean shouldFire(List<String> keysPressed, PressType type) {
        if (acceptedPressType != type) return false;
        if (keysPressed.size() > 1) return false;
        return boundKeys.containsKey(keysPressed.get(0));
    }
}
