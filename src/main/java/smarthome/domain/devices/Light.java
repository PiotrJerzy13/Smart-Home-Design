package smarthome.domain.devices;

import smarthome.domain.observer.Observable;

/**
 * Basic light actuator.
 * <p>
 * Emits observer messages on state changes:
 * <pre>
 * [Light] turn on
 * [Light] turn off
 * </pre>
 * Calls are idempotent to avoid duplicate notifications.
 */
public class Light extends Observable {
    private boolean isOn;

    /** Create a light initially turned off. */
    public Light() {
        this.isOn = false;
    }

    /**
     * Create a light with a custom initial state.
     * @param isOn initial on/off state
     */
    public Light(boolean isOn) {
        this.isOn = isOn;
    }

    /** Turn the light on; notifies observers if the state changed. */
    public void turnOn() {
        if (!isOn) {
            isOn = true;
            notifyObservers("[Light] turn on");
        }
    }

    /** Turn the light off; notifies observers if the state changed. */
    public void turnOff() {
        if (isOn) {
            isOn = false;
            notifyObservers("[Light] turn off");
        }
    }

    /** @return {@code true} if the light is on; otherwise {@code false}. */
    public boolean isOn() {
        return isOn;
    }
}
