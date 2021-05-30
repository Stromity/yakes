package com.stromity.yakes.tests;

import com.stromity.yakes.KeyboardEventSystem;
import com.stromity.yakes.PressType;
import com.stromity.yakes.listener.AnySingleKeyKeyboardListener;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;

public class AnySingleKeyKeyboardListenerTestCases {

    @Test
    public void testAnySingleKeyKeyboardListener_HandlesKeyPressesCorrectly() {
        KeyboardEventSystem eventSystem = new KeyboardEventSystem(new HashMap<>());
        AnySingleKeyKeyboardListener keyKeyboardListener = new AnySingleKeyKeyboardListener(new HashMap<>(), PressType.ON_PRESS);

        eventSystem.registerListener("ANY_TEST", keyKeyboardListener);

        keyKeyboardListener.bind("R", () -> System.out::println);
        eventSystem.fireEvent(Collections.singletonList("R"), PressType.ON_PRESS);
    }
}