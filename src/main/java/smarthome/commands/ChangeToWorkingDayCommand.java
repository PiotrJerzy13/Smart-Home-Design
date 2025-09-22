package smarthome.commands;

import smarthome.controller.HomeController;

/**
 * Command that switches the home into the "working day" profile.
 * <p>
 * Delegates to {@link HomeController#changeToWorkingDay()}, which usually
 * instructs the coffee maker to switch to a stronger coffee strategy
 * and may be extended to adjust other subsystems.
 * </p>
 */
public class ChangeToWorkingDayCommand extends EventCommand {
    private final HomeController homeController;

    /**
     * @param homeController the receiver that performs the actual work
     */
    public ChangeToWorkingDayCommand(HomeController homeController) {
        this.homeController = homeController;
    }

    /** Execute the working-day profile change on the controller. */
    @Override
    public void execute() {
        homeController.changeToWorkingDay();
    }
}
