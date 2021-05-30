package com.stromity.yakes.tests;

import com.stromity.yakes.KeyboardEventSystem;
import com.stromity.yakes.PressType;
import com.stromity.yakes.listener.StrictSingleKeyKeyboardListener;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public class StrictSingleKeyKeyboardListenerTestCases {

    @Test
    public void testStrictSingleKeyKeyboardListener_HandlesKeyPressesCorrectly() {
        KeyboardEventSystem eventSystem = new KeyboardEventSystem(new HashMap<>());
        StrictSingleKeyKeyboardListener keyKeyboardListener = new StrictSingleKeyKeyboardListener(new HashMap<>(), PressType.ON_PRESS);

        AtomicReference<String> result = new AtomicReference<>();

        // Faking a key press.
        String keyPressed = "R";

        eventSystem.registerListener("STRICT_TEST", keyKeyboardListener);

        keyKeyboardListener.bind(keyPressed, () -> result::set);
        eventSystem.fireEvent(Collections.singletonList(keyPressed), PressType.ON_PRESS);

        // Checks if the press went through.
        Assert.assertEquals(keyPressed, result.get());
    }
}
