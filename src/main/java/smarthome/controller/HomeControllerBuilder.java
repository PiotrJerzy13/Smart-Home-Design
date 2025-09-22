package smarthome.controller;

import smarthome.domain.devices.AlarmSystem;
import smarthome.domain.devices.FrontDoor;
import smarthome.domain.devices.Light;
import smarthome.domain.observer.MessageObserver;
import smarthome.domain.devices.coffeemaker.CoffeeMaker;
import smarthome.domain.devices.coffeemaker.StrongCoffeeCreationStrategy;
import smarthome.domain.devices.heatingsystem.HeatingSystem;
import smarthome.domain.devices.heatingsystem.HeatingSystemAdapter;
import smarthome.domain.devices.heatingsystem.LegacyHeatingSystem;

/**
 * Top-level builder for {@link HomeController}.
 * <p>
 * Applies sensible defaults, wraps a legacy heating system with an adapter when no
 * explicit heating system is provided, and registers a {@link MessageObserver}
 * on all devices so tests can assert emitted messages.
 * </p>
 */
public class HomeControllerBuilder {
    private final MessageObserver messageObserver;
    private AlarmSystem alarmSystem;
    private HeatingSystem heatingSystem;
    private FrontDoor frontDoor;
    private Light light;
    private CoffeeMaker coffeeMaker;

    /**
     * @param messageObserver sink for device messages (required)
     */
    public HomeControllerBuilder(MessageObserver messageObserver) {
        this.messageObserver = messageObserver;
    }

    /** Provide a custom alarm system implementation. */
    public HomeControllerBuilder alarmSystem(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
        return this;
    }

    /** Provide a custom heating system (adapter or implementation). */
    public HomeControllerBuilder heatingSystem(HeatingSystem heatingSystem) {
        this.heatingSystem = heatingSystem;
        return this;
    }

    /**
     * Convenience overload used by tests to pass a concrete adapter instance.
     * Delegates to {@link #heatingSystem(HeatingSystem)}.
     */
    public HomeControllerBuilder heatingSystemAdapter(HeatingSystemAdapter adapter) {
        return heatingSystem(adapter);
    }

    /** Provide a custom front door implementation. */
    public HomeControllerBuilder frontDoor(FrontDoor frontDoor) {
        this.frontDoor = frontDoor;
        return this;
    }

    /** Provide a custom light implementation. */
    public HomeControllerBuilder light(Light light) {
        this.light = light;
        return this;
    }

    /** Provide a custom coffee maker (with strategy already selected). */
    public HomeControllerBuilder coffeeMaker(CoffeeMaker coffeeMaker) {
        this.coffeeMaker = coffeeMaker;
        return this;
    }

    /**
     * Build a fully wired {@link HomeController}.
     * <ul>
     *   <li>If not provided, creates default devices.</li>
     *   <li>If heating is not provided, uses {@code new HeatingSystemAdapter(new LegacyHeatingSystem())}.</li>
     *   <li>If coffee maker is not provided, uses strong coffee by default.</li>
     *   <li>Subscribes the {@link MessageObserver} to all devices.</li>
     * </ul>
     */
    public HomeController build() {
        HomeController controller = new HomeController(messageObserver);

        // Defaults
        controller.alarmSystem = (this.alarmSystem != null) ? this.alarmSystem : new AlarmSystem();
        controller.heatingSystem = (this.heatingSystem != null) ? this.heatingSystem
                : new HeatingSystemAdapter(new LegacyHeatingSystem());
        controller.frontDoor = (this.frontDoor != null) ? this.frontDoor : new FrontDoor();
        controller.light = (this.light != null) ? this.light : new Light();
        controller.coffeeMaker = (this.coffeeMaker != null) ? this.coffeeMaker
                : new CoffeeMaker(new StrongCoffeeCreationStrategy());

        // Subscribe observer so tests can assert messages
        controller.alarmSystem.addObserver(messageObserver);
        if (controller.heatingSystem instanceof HeatingSystemAdapter) {
            ((HeatingSystemAdapter) controller.heatingSystem).addObserver(messageObserver);
        }
        controller.frontDoor.addObserver(messageObserver);
        controller.light.addObserver(messageObserver);
        controller.coffeeMaker.addObserver(messageObserver);

        return controller;
    }
}
