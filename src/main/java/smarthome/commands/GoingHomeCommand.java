package smarthome.commands;

import smarthome.controller.HomeController;

/**
 * Command that ensures the heating system is turned on as the user is heading home.
 * <p>
 * Delegates to {@link HomeController#goingHome()} which performs an idempotent turn-on.
 * </p>
 */
public class GoingHomeCommand extends EventCommand {
    private final HomeController homeController;

    /**
     * @param homeController the receiver that performs the actual action
     */
    public GoingHomeCommand(HomeController homeController) {
        this.homeController = homeController;
    }

    /** Execute the "going home" use case. */
    @Override
    public void execute() {
        homeController.goingHome();
    }
}
