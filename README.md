# Yakes - Yet Another Keyboard Event System

Yakes is an event system, that allows firing of different events, whenever a
key is pressed/released/being held. Yakes come with different type of default
implementations of the KeyboardListener class.

In the future, there's a few goals for Yakes:

#### LWJGL implementation - 0% done.
#### JavaFX implementation - 0% done.
#### Swing implementation - 0% done.

These will be published as separate gradle modules in this repository.

## Independent
Yakes is completely independent of any other libraries. There are both pros & cons to this.

#### Pros
Extremely lightweight
Doesn't require you to install 10 different libraries, when using Yakes
Yakes uses `String`s to represent the keys pressed. This allows for extremely easy implementation for other game/program frameworks.

#### Cons
Yakes is not designed for any specific library that allows key presses. This can lead to potential performance loss in some instances.
May have compatibility issues for certain libraries, that doesn't represent key presses as `String`s.

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
