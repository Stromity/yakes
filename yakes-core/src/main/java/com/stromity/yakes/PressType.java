package com.stromity.yakes;

/**
 * The different type of press types.
 */
public enum PressType {
    /**
     * Representing whenever the keys is being pressed.
     */
    ON_PRESS,

    /**
     * Representing whenever the keys is being held.
     */
    ON_HOLD,

    /**
     * Representing whenever the keys is being released.
     */
    ON_RELEASE,

    /**
     * Represents any type of key press (ON_PRESS, ON_HOLD & ON_RELEASE).
     */
    ANY
}
