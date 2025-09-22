package smarthome.commands;

import smarthome.controller.HomeController;

/**
 * Command that switches the home into the "holiday" profile.
 * <p>
 * Delegates to {@link HomeController#changeToHoliday()}, which usually
 * instructs the coffee maker to switch to a weaker coffee strategy
 * and may be extended to adjust other subsystems.
 * </p>
 */
public class ChangeToHolidayCommand extends EventCommand {
    private final HomeController homeController;

    /**
     * @param homeController the receiver that performs the actual work
     */
    public ChangeToHolidayCommand(HomeController homeController) {
        this.homeController = homeController;
    }

    /** Execute the holiday profile change on the controller. */
    @Override
    public void execute() {
        homeController.changeToHoliday();
    }
}
