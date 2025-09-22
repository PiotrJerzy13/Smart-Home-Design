package smarthome.commands;

import smarthome.controller.HomeController;

/**
 * Command that signals the user has arrived home.
 * <p>
 * Delegates to {@link HomeController#arrivesHome()} which typically:
 * turns off the alarm if armed, opens the front door if closed,
 * and brews coffee using the configured strategy.
 * </p>
 */
public class ArrivesHomeCommand extends EventCommand {
    private final HomeController homeController;

    /**
     * @param homeController the receiver that performs the actual work
     */
    public ArrivesHomeCommand(HomeController homeController) {
        this.homeController = homeController;
    }

    /**
     * Execute the "arrives home" use case on the {@link HomeController}.
     */
    @Override
    public void execute() {
        homeController.arrivesHome();
    }
}
