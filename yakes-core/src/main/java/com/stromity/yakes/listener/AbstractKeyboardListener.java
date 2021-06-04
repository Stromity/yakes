package com.stromity.yakes.listener;

import com.stromity.yakes.PressType;
import com.stromity.yakes.listener.event.KeyboardPressCallback;
import com.stromity.yakes.listener.event.KeyboardPressCallbackProvider;

import java.util.Collections;
import java.util.Map;

/**
 * The abstract implementation of {@link KeyboardListener}. This class
 * is mainly used for maintainability, and to prevent boilerplate code
 * for actual implementations.
 * <p>
 * Each listener is assigned with their own unique id stored as a {@link String}.
 * This is to help the developer with possible stack traces, and how to identify
 * a listener from one another. It can always be re-assigned, but in that case,
 * you should remember to update it in your {@link com.stromity.yakes.KeyboardEventSystem}
 * as that could result in code running incorrectly.
 */
public abstract class AbstractKeyboardListener<T, C extends KeyboardPressCallback<T>> implements KeyboardListener<T, C> {

    /**
     * The bound key registry. Stores all {@link KeyboardPressCallbackProvider}'s
     * with correspondence of the key press it was registered to.
     */
    protected final Map<String, KeyboardPressCallbackProvider<T, C>> boundKeys;

    /**
     * The accepted type of this listener.
     */
    protected final PressType acceptedPressType;

    /**
     * The unique id of this listener.
     */
    protected String id;

    public AbstractKeyboardListener(
            Map<String, KeyboardPressCallbackProvider<T, C>> boundKeys,
            PressType acceptedPressType) {
        this.boundKeys = boundKeys;
        this.acceptedPressType = acceptedPressType;
    }

    /**
     * @return the unique identifier for this listener.
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * Sets this listener id to {@code id}.
     * <p>
     * This is mainly used to find bugs/errors in code for developers.
     * Should never be null.
     *
     * @param id the new id for the listener.
     */
    @Override
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return an unmodifiable version of {@code boundKeys} using {@link Collections#unmodifiableMap(Map)}.
     */
    @Override
    public Map<String, KeyboardPressCallbackProvider<T, C>> getBoundKeys() {
        return Collections.unmodifiableMap(boundKeys);
    }

    /**
     * @return the accepted press type.
     */
    @Override
    public PressType getAcceptedPressType() {
        return acceptedPressType;
    }

    @Override
    public void bind(String key, KeyboardPressCallbackProvider<T, C> eventProvider) {
        boundKeys.put(key, eventProvider);
    }

    @Override
    public void remove(String key) {
        boundKeys.remove(key);
    }

    /**
     * Performs a lookup in the registry, and finds the corresponding
     * {@link KeyboardPressCallbackProvider} to {@code key}. If the provider
     * exists {@link KeyboardPressCallbackProvider#create()} is then returned.
     * <p>
     * If no {@link KeyboardPressCallbackProvider} is associated with the given key,
     * the {@code null} is returned.
     *
     * @param key the key to provide an event for.
     *
     * @return the event corresponding to {@code key}.
     */
    @Override
    public C provideEvent(String key) {
        KeyboardPressCallbackProvider<T, C> provider = boundKeys.get(key);
        if (provider == null) return null;
        return provider.create();
    }
}
