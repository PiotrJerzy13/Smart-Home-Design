package smarthome.domain.devices.heatingsystem;

import smarthome.domain.observer.MessageObserver;

/**
 * Adapter that exposes a {@link HeatingSystem} interface over a {@link LegacyHeatingSystem}.
 * <p>
 * This lets the application use a simple, modern API while delegating the actual work
 * to the legacy device. Observer registration is delegated to the wrapped legacy system
 * so tests and UIs can receive messages like:
 * <pre>
 * [HeatingSystem] turn on
 * [HeatingSystem] turn off
 * </pre>
 * 
 */
public class HeatingSystemAdapter implements HeatingSystem {
    private final LegacyHeatingSystem legacyHeatingSystem;

    /**
     * @param legacyHeatingSystem the wrapped legacy device (must not be {@code null})
     */
    public HeatingSystemAdapter(LegacyHeatingSystem legacyHeatingSystem) {
        this.legacyHeatingSystem = legacyHeatingSystem;
    }

    /**
     * Access to the wrapped device.
     * <p><b>Note:</b> Prefer using {@link #addObserver(MessageObserver)} instead of
     * reaching through the adapter, to preserve encapsulation.
     */
    public LegacyHeatingSystem getLegacySystem() {
        return legacyHeatingSystem;
    }

    /**
     * Register an observer for messages emitted by the legacy device.
     */
    public void addObserver(MessageObserver o) {
        legacyHeatingSystem.addObserver(o);
    }

    @Override
    public void turnOn() {
        legacyHeatingSystem.operate(true);
    }

    @Override
    public void turnOff() {
        legacyHeatingSystem.operate(false);
    }

    @Override
    public boolean isTurnedOn() {
        return legacyHeatingSystem.isTurnedOn();
    }
}
