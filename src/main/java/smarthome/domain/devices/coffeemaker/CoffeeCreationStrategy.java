package smarthome.domain.devices.coffeemaker;

/**
 * Strategy for determining the caffeine content of the coffee to be brewed.
 * <p>
 * Implementations encapsulate a policy (e.g., "strong", "weak") so that the
 * {@link CoffeeMaker} can remain agnostic to how the amount is calculated.
 * </p>
 */
public interface CoffeeCreationStrategy {

    /**
     * @return caffeine amount in milligrams that should be used for the next coffee
     */
    int getCaffeineAmount();
}
