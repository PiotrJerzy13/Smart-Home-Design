package smarthome.controller;

import smarthome.domain.devices.AlarmSystem;
import smarthome.domain.devices.FrontDoor;
import smarthome.domain.devices.Light;
import smarthome.domain.observer.MessageObserver;
import smarthome.domain.devices.coffeemaker.CoffeeMaker;
import smarthome.domain.devices.heatingsystem.HeatingSystem;

/**
 * Central coordinator for the smart home.
 * <p>
 * This class expresses high-level use cases (events) as simple, readable methods
 * that orchestrate the underlying devices in the right order while avoiding
 * redundant actions (e.g., don't open a door that is already open).
 * </p>
 *
 * <h2>Design notes</h2>
 * <ul>
 *   <li><b>Facade / Application service:</b> keeps event logic in one place.</li>
 *   <li><b>Adapter (outside):</b> the heating system is provided through an adapter to a legacy API.</li>
 *   <li><b>Builder (outside):</b> construction, defaults, and observer wiring are handled by
 *       {@link com.epam.training.smarthome.controller.HomeControllerBuilder}.</li>
 *   <li><b>Observer (outside):</b> devices push human-readable messages to a {@link MessageObserver}.</li>
 * </ul>
 *
 * <p><b>Instantiation:</b> use {@link HomeController.HomeControllerBuilder} (shim) or the
 * top-level {@link com.epam.training.smarthome.controller.HomeControllerBuilder}.</p>
 */
public class HomeController {

    /** Intrusion / siren system. */
    AlarmSystem alarmSystem;

    /** Heating system exposed via {@link HeatingSystem} (possibly an adapter). */
    HeatingSystem heatingSystem;

    /** Entrance door actuator. */
    FrontDoor frontDoor;

    /** Basic lighting. */
    Light light;

    /** Coffee maker with a creation strategy managed elsewhere. */
    CoffeeMaker coffeeMaker;

    /** Target observer for device messages; injected by the builder. */
    MessageObserver messageObserver;

    /**
     * Package-private constructor. Instances should be created via a builder so that:
     * <ul>
     *   <li>defaults are applied,</li>
     *   <li>adapters are wrapped,</li>
     *   <li>observers are correctly registered on all devices.</li>
     * </ul>
     *
     * @param messageObserver sink for user-facing messages produced by devices
     */
    HomeController(MessageObserver messageObserver) {
        this.messageObserver = messageObserver;
    }

    /**
     * Intent: the user is heading home; ensure the heating is turned on.
     * Idempotent: if already on, emits a no-op log line rather than toggling.
     */
    public void goingHome() {
        if (!heatingSystem.isTurnedOn()) {
            heatingSystem.turnOn();
        } else {
            System.out.println("[HomeController] nothing to do (heating system is already turned on)");
        }
    }

    /**
     * Intent: the user arrives home.
     * <ol>
     *   <li>Turn off the alarm if it is currently armed.</li>
     *   <li>Open the front door if it is closed.</li>
     *   <li>Brew coffee (strategy determines the strength).</li>
     * </ol>
     * Each device is consulted for current state to avoid redundant actions.
     */
    public void arrivesHome() {
        if (alarmSystem.isOn()) {
            alarmSystem.turnOff();
        } else {
            System.out.println("[HomeController] nothing to do (alarm system is already turned off)");
        }

        if (!frontDoor.isOpen()) {
            frontDoor.open();
        } else {
            System.out.println("[HomeController] nothing to do (front door is already opened)");
        }

        coffeeMaker.createCoffee();
    }

    /**
     * Intent: motion detected while at home or away.
     * <ol>
     *   <li>If the alarm is armed, raise an alarm.</li>
     *   <li>Ensure the light is on for safety/visibility.</li>
     * </ol>
     */
    public void movement() {
        if (alarmSystem.isOn()) {
            alarmSystem.alarm();
        }

        if (!light.isOn()) {
            light.turnOn();
        } else {
            System.out.println("[HomeController] nothing to do (light is already turned on)");
        }
    }

    /**
     * Switch to holiday profile for coffee (e.g., weaker coffee).
     * The actual strategy switch and message emission is handled by the coffee maker.
     */
    public void changeToHoliday() {
        coffeeMaker.changeCoffeeType();
    }

    /**
     * Switch to working-day profile for coffee (e.g., stronger coffee).
     * The actual strategy switch and message emission is handled by the coffee maker.
     */
    public void changeToWorkingDay() {
        coffeeMaker.changeCoffeeType();
    }

    /**
     * <b>Compatibility shim.</b> Nested builder preserved so existing code/tests using
     * {@code new HomeController.HomeControllerBuilder(...)} keep working.
     * Internally delegates to the new top-level
     * {@link com.epam.training.smarthome.controller.HomeControllerBuilder}.
     */
    public static class HomeControllerBuilder {
        private final smarthome.controller.HomeControllerBuilder delegate;

        /**
         * Create a delegating builder.
         * @param messageObserver observer to collect device messages
         */
        public HomeControllerBuilder(MessageObserver messageObserver) {
            this.delegate = new smarthome.controller.HomeControllerBuilder(messageObserver);
        }

        /** Set a custom alarm system. */
        public HomeControllerBuilder alarmSystem(AlarmSystem a) {
            delegate.alarmSystem(a);
            return this;
        }

        /** Provide a heating system (interface or adapter). */
        public HomeControllerBuilder heatingSystem(HeatingSystem h) {
            delegate.heatingSystem(h);
            return this;
        }

        /**
         * Convenience for tests: accept a concrete {@link
         * com.epam.training.smarthome.domain.devices.heatingsystem.HeatingSystemAdapter}.
         */
        public HomeControllerBuilder heatingSystemAdapter(
                smarthome.domain.devices.heatingsystem.HeatingSystemAdapter a) {
            delegate.heatingSystemAdapter(a);
            return this;
        }

        /** Set the front door device. */
        public HomeControllerBuilder frontDoor(FrontDoor f) {
            delegate.frontDoor(f);
            return this;
        }

        /** Set the light device. */
        public HomeControllerBuilder light(Light l) {
            delegate.light(l);
            return this;
        }

        /** Set the coffee maker device. */
        public HomeControllerBuilder coffeeMaker(CoffeeMaker c) {
            delegate.coffeeMaker(c);
            return this;
        }

        /**
         * Build a fully wired {@link HomeController}:
         * defaults applied, adapters created if needed, and the {@link MessageObserver}
         * subscribed to each device.
         */
        public HomeController build() {
            return delegate.build();
        }
    }
}
