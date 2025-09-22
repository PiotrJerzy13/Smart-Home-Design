package smarthome.commands;

/**
 * Enumeration of supported smart-home event commands.
 * Each constant maps to a concrete {@link EventCommand} in {@link EventCommandFactory}.
 */
public enum EventCommandType {
    /** User is on the way home; ensure heating is on. */
    GOING_HOME,

    /** User has arrived; disarm alarm, open door, brew coffee. */
    ARRIVES_HOME,

    /** Movement detected; alarm if armed, ensure lighting. */
    MOVEMENT,

    /** Switch to a holiday profile (e.g., weaker coffee). */
    CHANGE_TO_HOLIDAY,

    /** Switch to a working-day profile (e.g., stronger coffee). */
    CHANGE_TO_WORKING_DAY
}
