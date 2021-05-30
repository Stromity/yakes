package com.stromity.yakes.listener;

import com.stromity.yakes.PressType;
import com.stromity.yakes.listener.event.KeyboardPressCallback;
import com.stromity.yakes.listener.event.KeyboardPressCallbackProvider;

import java.util.List;
import java.util.Map;

/**
 * An interface representing all keyboard listeners.
 * <p>
 * A set of keys can only contain one listener. This means, if you call
 * {@link #bind(String, KeyboardPressCallbackProvider)} it will override the current bound key.
 * <p>
 * A listener can only listen to on type of {@link PressType}. This means, if it's assigned
 * to only fire when a key is released, the key will only be fired whenever release calls is fired.
 *
 * @param <T> the key(s) object representation.
 * @see PressType
 */
public interface KeyboardListener<T, C extends KeyboardPressCallback<T>> {

    /**
     * @return the unique identifier for this listener.
     */
    String getId();

    /**
     * Sets this listener id to {@code id}.
     * <p>
     * This is mainly used to find bugs/errors in code for developers.
     * Should never be null.
     *
     * @param id the new id for the listener.
     */
    void setId(String id);

    /**
     * @return an unmodifiable map of the bound keys registry.
     */
    Map<String, KeyboardPressCallbackProvider<T, C>> getBoundKeys();

    /**
     * Fires this listener.
     *
     * @param keys the keys being pressed.
     * @param type the current press type.
     */
    void fire(List<String> keys, PressType type);

    /**
     * If this listener should fire. This is called, whenever a key is pressed.
     *
     * @param keysPressed the keys being pressed.
     * @return whether or not this listener should fire.
     */
    boolean shouldFire(List<String> keysPressed, PressType type);

    /**
     * Binds a key to an event.
     *
     * @param key the key to bind.
     * @param eventProvider the factory to be bound to.
     */
    void bind(String key, KeyboardPressCallbackProvider<T, C> eventProvider);

    /**
     * Removes the event bound to the given key.
     *
     * @param key the key to remove the binding from.
     */
    void remove(String key);

    /**
     * @return the accepted press type.
     */
    PressType getAcceptedPressType();

    /**
     * Provides an event instance from this listener, corresponding to the given key.
     *
     * @param key the key to provide an event for.
     *
     * @return the provided event.
     */
    C provideEvent(String key);
}
