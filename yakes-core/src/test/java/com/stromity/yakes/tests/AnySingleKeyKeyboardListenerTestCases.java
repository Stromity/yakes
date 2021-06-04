package com.stromity.yakes.tests;

import com.stromity.yakes.KeyboardEventSystem;
import com.stromity.yakes.PressType;
import com.stromity.yakes.listener.AnySingleKeyKeyboardListener;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public class AnySingleKeyKeyboardListenerTestCases {

    @Test
    public void testAnySingleKeyKeyboardListener_HandlesKeyPressesCorrectly() {
        KeyboardEventSystem eventSystem = new KeyboardEventSystem(new HashMap<>());
        AnySingleKeyKeyboardListener keyKeyboardListener = new AnySingleKeyKeyboardListener(new HashMap<>(), PressType.ON_PRESS);

        AtomicReference<String> result = new AtomicReference<>();

        eventSystem.registerListener("ANY_TEST", keyKeyboardListener);

        // Faking key press.
        String pressedKey = "R";

        keyKeyboardListener.bind(pressedKey, () -> result::set);

        // Call the fake key press.
        eventSystem.fireEvent(Collections.singletonList(pressedKey), PressType.ON_PRESS);

        Assert.assertEquals(pressedKey, result.get());
    }
}