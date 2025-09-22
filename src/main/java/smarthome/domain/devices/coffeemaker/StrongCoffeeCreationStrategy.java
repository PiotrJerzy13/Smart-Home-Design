package smarthome.domain.devices.coffeemaker;

/**
 * Strategy representing a "strong" coffee profile.
 */
public class StrongCoffeeCreationStrategy implements CoffeeCreationStrategy {

    /** {@inheritDoc} */
    @Override
    public int getCaffeineAmount() {
        return 40;
    }
}
