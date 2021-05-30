# Yakes - Yet Another Keyboard Event System

Yakes is an event system, that allows firing of different events, whenever a
key is pressed/released/being held. Yakes come with different type of default
implementations of the KeyboardListener class.

## Code example

```´java
// The main system instance.
KeyboardEventSystem eventSystem = new KeyboardEventSystem(new HashMap<>());

// The listener with it's own filters and rules.
AnySingleKeyKeyboardListener keyKeyboardListener = new AnySingleKeyKeyboardListener(new HashMap<>(), PressType.ON_PRESS);

// Register the listener to the system.
eventSystem.registerListener("Any_Single", keyKeyboardListener);

// Prints the key being pressed.
keyKeyboardListener.bind("R", () -> System.out::println);

// Hook the event system.
eventSystem.fireEvent(..., PressType.ON_PRESS);
```

## Rules
#### A listener can only listen 1 type of `PressType` at once. 
#### Each listener has it's own rules, of whenever to fire it's `KeyboardPressCallback`'s.
#### All events is called as `KeyboardPressCallBack` and depends on a `KeyboardPressCallbackProvider` to be created.
#### The system currently requires key presses to be passed as `List`'s in `String`'s. This may change in the future
#### All listeners require an id that is set during `KeyboardEventSystem.registerListener`.
#### Each time an event is fired `KeyboardPressCallbackProvider.create` is called, to provide the `KeyboardPressCallback` instance.
