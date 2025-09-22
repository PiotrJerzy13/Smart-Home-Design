package smarthome.commands;

import smarthome.controller.HomeController;

/**
 * Factory for creating {@link EventCommand} instances based on an {@link EventCommandType}.
 * <p>
 * This keeps object creation in one place and prevents callers from depending on
 * concrete command classes. It pairs nicely with schedulers, queues, or
 * controller code that only knows the enum.
 * </p>
 */
public class EventCommandFactory {
    private final HomeController homeController;

    /**
     * @param homeController the receiver that commands will operate on (must not be {@code null})
     */
    public EventCommandFactory(HomeController homeController) {
        this.homeController = homeController;
    }

    /**
     * Create a concrete command for the given {@code type}.
     *
     * @param type the type of event to execute
     * @return a concrete {@link EventCommand} bound to the configured {@link HomeController}
     * @throws IllegalArgumentException if the type is not recognized
     */
    public EventCommand createEventCommand(EventCommandType type) {
        switch (type) {
            case GOING_HOME:
                return new GoingHomeCommand(homeController);
            case ARRIVES_HOME:
                return new ArrivesHomeCommand(homeController);
            case MOVEMENT:
                return new MovementCommand(homeController);
            case CHANGE_TO_HOLIDAY:
                return new ChangeToHolidayCommand(homeController);
            case CHANGE_TO_WORKING_DAY:
                return new ChangeToWorkingDayCommand(homeController);
            default:
                throw new IllegalArgumentException("Unknown event type: " + type);
        }
    }
}
