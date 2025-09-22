package smarthome.domain.devices.heatingsystem;

/**
 * Abstraction for a heating system used by the application layer.
 * <p>
 * Keeps the rest of the codebase decoupled from any legacy or vendor-specific
 * APIs. Implementations should be <em>idempotent</em> where reasonable
 * (e.g., calling {@link #turnOn()} twice should not toggle the state).
 * </p>
 */
public interface HeatingSystem {

    /**
     * Ensure the heating system is turned on.
     * Implementations should avoid redundant work if already on.
     */
    void turnOn();

    /**
     * Ensure the heating system is turned off.
     * Implementations should avoid redundant work if already off.
     */
    void turnOff();

    /**
     * @return {@code true} if the system is currently on; otherwise {@code false}.
     */
    boolean isTurnedOn();
}
