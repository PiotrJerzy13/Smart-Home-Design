package smarthome.commands;

/**
 * Base type for all smart-home event commands.
 * <p>
 * Implements the Command pattern: each concrete command encapsulates a
 * single action that can be executed later, queued, logged, or retried.
 * </p>
 */
public abstract class EventCommand {

    /**
     * Execute the encapsulated action.
     * Implementations should be side-effecting and typically delegate
     * to a {@code HomeController}.
     */
    public abstract void execute();
}
