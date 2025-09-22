package smarthome.domain.observer;

/**
 * Observer in a simple string-based Observer pattern.
 * <p>
 * Implementations receive user-facing messages from {@link Observable}s.
 * </p>
 */
public interface Observer {

    /**
     * Receive a notification from a subject.
     *
     * @param message a human-readable message; never {@code null}
     */
    void update(String message);
}
