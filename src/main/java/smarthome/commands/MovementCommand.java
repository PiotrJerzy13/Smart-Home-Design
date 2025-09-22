package smarthome.commands;

import smarthome.controller.HomeController;

/**
 * Command triggered on movement detection.
 * <p>
 * Delegates to {@link HomeController#movement()} which raises an alarm if armed
 * and ensures lighting is on.
 * </p>
 */
public class MovementCommand extends EventCommand {
    private final HomeController homeController;

    /**
     * @param homeController the receiver that performs the actual action
     */
    public MovementCommand(HomeController homeController) {
        this.homeController = homeController;
    }

    /** Execute the movement handling use case. */
    @Override
    public void execute() {
        homeController.movement();
    }
}
