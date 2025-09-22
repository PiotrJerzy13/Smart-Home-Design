package smarthome.domain.devices.heatingsystem;

import smarthome.domain.observer.Observable;

/**
 * Legacy heating device exposing a boolean-based control method.
 * <p>
 * Extends the project's {@code Observable} to emit human-readable messages when
 * state changes. Messages used by tests:
 * <pre>
 * [HeatingSystem] turn on
 * [HeatingSystem] turn off
 * </pre>
 * 
 */
public class LegacyHeatingSystem extends Observable {
    private boolean isTurnedOn;

    /** Create a device initially turned off. */
    public LegacyHeatingSystem() {
        this(false);
    }

    /**
     * @param isTurnedOn initial power state
     */
    public LegacyHeatingSystem(boolean isTurnedOn) {
        this.isTurnedOn = isTurnedOn;
    }

    /**
     * Set the device state.
     * <ul>
     *   <li>If the desired state differs from the current state, switch and notify observers.</li>
     *   <li>If the state is unchanged, do nothing (idempotent).</li>
     * </ul>
     *
     * @param turnOn desired state; {@code true} to turn on, {@code false} to turn off
     */
    public void operate(boolean turnOn) {
        if (this.isTurnedOn != turnOn) {
            this.isTurnedOn = turnOn;
            if (turnOn) {
                notifyObservers("[HeatingSystem] turn on");
            } else {
                notifyObservers("[HeatingSystem] turn off");
            }
        }
    }

    /**
     * @return {@code true} if the device is currently on; otherwise {@code false}.
     */
    public boolean isTurnedOn() {
        return isTurnedOn;
    }
}
