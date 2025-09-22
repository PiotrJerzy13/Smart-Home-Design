package smarthome.domain.devices.coffeemaker;

import smarthome.domain.observer.Observable;

/**
 * Coffee maker device that delegates caffeine strength to a {@link CoffeeCreationStrategy}
 * and emits user-facing messages via the Observer pattern.
 * <p>
 * Responsibilities:
 * <ul>
 *   <li>Use the current strategy to brew coffee.</li>
 *   <li>Allow switching between strong and weak strategies.</li>
 *   <li>Notify observers about actions performed.</li>
 * </ul>
 * <p>
 * Observers receive messages like:<br>
 * <code>[CoffeeMaker] create coffee with 40mg caffeine</code><br>
 * <code>[CoffeeMaker] change the type of coffee</code>
 * </p>
 */
public class CoffeeMaker extends Observable {
    private CoffeeCreationStrategy strategy;

    /**
     * Create a coffee maker with the given caffeine strategy.
     *
     * @param strategy initial strategy to determine caffeine amount (must not be {@code null})
     */
    public CoffeeMaker(CoffeeCreationStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Brew a coffee using the current {@link CoffeeCreationStrategy}, and notify observers
     * with a human-readable message containing the caffeine amount.
     */
    public void createCoffee() {
        int caffeine = strategy.getCaffeineAmount();
        notifyObservers("[CoffeeMaker] create coffee with " + caffeine + "mg caffeine");
    }

    /**
     * Toggle the coffee strength:
     * <ul>
     *   <li>If currently strong, switch to weak;</li>
     *   <li>otherwise, switch to strong.</li>
     * </ul>
     * After switching, notify observers that the type changed.
     * <p><b>Note:</b> This method hard-codes the two concrete strategies.
     * If you need more variants later, consider injecting a strategy provider/factory.</p>
     */
    public void changeCoffeeType() {
        if (strategy instanceof StrongCoffeeCreationStrategy) {
            strategy = new WeakCoffeeCreationStrategy();
        } else {
            strategy = new StrongCoffeeCreationStrategy();
        }
        notifyObservers("[CoffeeMaker] change the type of coffee");
    }
}
