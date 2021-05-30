package com.stromity.yakes.tests;

import com.stromity.yakes.KeyboardEventSystem;
import com.stromity.yakes.PressType;
import com.stromity.yakes.listener.MultiKeyKeyboardListener;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class MultiKeyKeyboardListenerTestCases {

    @Test
    public void testMultiKeyKeyboardListener_HandleKeyPressesCorrectly() {
        KeyboardEventSystem eventSystem = new KeyboardEventSystem(new HashMap<>());
        MultiKeyKeyboardListener keyKeyboardListener = new MultiKeyKeyboardListener(new HashMap<>(), PressType.ON_PRESS);

        // Register the test listener.
        eventSystem.registerListener("MULTI_TEST", keyKeyboardListener);

        AtomicReference<List<String>> result = new AtomicReference<>();

        List<String> keysToListenFor = new ArrayList<>();

        // Will be stored in the order:
        // ALT, CTRL, DLT.
        keysToListenFor.add("CTRL");
        keysToListenFor.add("ALT");
        keysToListenFor.add("DLT");

        keyKeyboardListener.bind(keysToListenFor, () -> result::set);

        // Fake key press setup.
        List<String> pressedKeys = new ArrayList<>();
        pressedKeys.add("DLT");
        pressedKeys.add("CTRL");
        pressedKeys.add("ALT");

        eventSystem.fireEvent(pressedKeys, PressType.ON_PRESS);

        // Checks if the press went through.
        Assert.assertEquals(pressedKeys, result.get());
    }
}
