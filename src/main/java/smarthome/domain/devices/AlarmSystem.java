package smarthome.domain.devices;

import smarthome.domain.observer.Observable;

/**
 * Intrusion alarm system.
 * <p>
 * Emits observer messages on state changes:
 * <pre>
 * [AlarmSystem] turn on
 * [AlarmSystem] turn off
 * [AlarmSystem] alarm
 * </pre>
 * Calls are idempotent: re-arming or re-disarming does nothing.
 */
public class AlarmSystem extends Observable {
    private boolean isOn;

    /** Create an alarm initially disarmed. */
    public AlarmSystem() {
        this.isOn = false;
    }

    /**
     * Create an alarm with a custom initial state.
     * @param isOn initial armed state
     */
    public AlarmSystem(boolean isOn) {
        this.isOn = isOn;
    }

    /** Arm the alarm; notifies observers if the state changed. */
    public void turnOn() {
        if (!isOn) {
            isOn = true;
            notifyObservers("[AlarmSystem] turn on");
        }
    }

    /** Disarm the alarm; notifies observers if the state changed. */
    public void turnOff() {
        if (isOn) {
            isOn = false;
            notifyObservers("[AlarmSystem] turn off");
        }
    }

    /**
     * Trigger the alarm only if armed; emits a message but doesn’t change the armed state.
     */
    public void alarm() {
        if (isOn) {
            notifyObservers("[AlarmSystem] alarm");
        }
    }

    /** @return {@code true} if armed; otherwise {@code false}. */
    public boolean isOn() {
        return isOn;
    }
}
