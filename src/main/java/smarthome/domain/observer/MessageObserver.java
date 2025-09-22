package smarthome.domain.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete {@link Observer} that accumulates all received messages.
 * <p>
 * Handy for tests: you can assert on {@link #getMessages()} to verify that
 * devices emitted the expected output (e.g., {@code "[Light] turn on"}).
 * </p>
 */
public class MessageObserver implements Observer {
    private final List<String> messages = new ArrayList<>();

    /**
     * Store the incoming message in an internal list.
     *
     * @param message a human-readable message; never {@code null}
     */
    @Override
    public void update(String message) {
        messages.add(message);
    }

    /**
     * Access all messages received so far.
     * <p>
     * Returns the live backing list to keep things simple for tests.
     * If you prefer stricter encapsulation, return an unmodifiable view instead.
     * </p>
     *
     * @return the (mutable) list of messages in arrival order
     */
    public List<String> getMessages() {
        return messages;
    }
}
