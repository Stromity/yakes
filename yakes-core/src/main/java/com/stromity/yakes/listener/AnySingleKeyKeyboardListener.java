package com.stromity.yakes.listener;

import com.stromity.yakes.PressType;
import com.stromity.yakes.listener.event.KeyboardPressCallback;
import com.stromity.yakes.listener.event.KeyboardPressCallbackProvider;

import java.util.List;
import java.util.Map;

/**
 * A listener that listens for single key presses. This listener
 * only listens for, whenever a key has been pressed, and does not respect,
 * if other key's is being pressed at the same time.
 * <p>
 * A listener like this, would be useful for games. For instance;
 * Let's say a player is walking their character, but needs to switch
 * weapon, while walking.
 */
public class AnySingleKeyKeyboardListener extends AbstractKeyboardListener<String, KeyboardPressCallback.Single> {
    public AnySingleKeyKeyboardListener(
            Map<String, KeyboardPressCallbackProvider<String, KeyboardPressCallback.Single>> boundKeys,
            PressType acceptedPressType) {
        super(boundKeys, acceptedPressType);
    }

    @Override
    public void fire(List<String> keys, PressType type) {
        for (String key : keys) {
            KeyboardPressCallback.Single callback = provideEvent(key);

            if (callback != null) {
                callback.fire(key);
            }
        }
    }

    @Override
    public boolean shouldFire(List<String> keysPressed, PressType type) {
        if (!isPressTypeAccepted(type)) return false;

        for (String key : keysPressed) {
            return boundKeys.containsKey(key);
        }
        return false;
    }
}
