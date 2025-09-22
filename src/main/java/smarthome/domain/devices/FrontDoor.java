package smarthome.domain.devices;

import smarthome.domain.observer.Observable;

/**
 * Front door actuator.
 * <p>
 * Emits observer messages on state changes:
 * <pre>
 * [FrontDoor] open
 * [FrontDoor] close
 * </pre>
 * Calls are idempotent: re-opening an open door or re-closing a closed door does nothing.
 */
public class FrontDoor extends Observable {
    private boolean isOpen;

    /** Create a door initially closed. */
    public FrontDoor() {
        this.isOpen = false;
    }

    /**
     * Create a door with a custom initial state.
     * @param isOpen initial open state
     */
    public FrontDoor(boolean isOpen) {
        this.isOpen = isOpen;
    }

    /** Open the door; notifies observers if the state changed. */
    public void open() {
        if (!isOpen) {
            isOpen = true;
            notifyObservers("[FrontDoor] open");
        }
    }

    /** Close the door; notifies observers if the state changed. */
    public void close() {
        if (isOpen) {
            isOpen = false;
            notifyObservers("[FrontDoor] close");
        }
    }

    /** @return {@code true} if the door is open; otherwise {@code false}. */
    public boolean isOpen() {
        return isOpen;
    }
}
