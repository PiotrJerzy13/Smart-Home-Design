package smarthome.domain.devices.coffeemaker;

/**
 * Strategy representing a "weak" coffee profile.
 */
public class WeakCoffeeCreationStrategy implements CoffeeCreationStrategy {

    /** {@inheritDoc} */
    @Override
    public int getCaffeineAmount() {
        return 20;
    }
}
