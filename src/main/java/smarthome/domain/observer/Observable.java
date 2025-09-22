package smarthome.domain.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Minimal subject in the Observer pattern.
 * <p>
 * Maintains a list of {@link Observer}s and synchronously delivers messages
 * via {@link #notifyObservers(String)}. This class is intentionally small and
 * <em>not</em> thread-safe; if you need concurrency, consider
 * {@code CopyOnWriteArrayList} or external synchronization.
 * </p>
 */
public abstract class Observable {
    private final List<Observer> observers = new ArrayList<>();

    /**
     * Register an observer to receive future notifications.
     *
     * @param observer the observer to add; must not be {@code null}
     */
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Notify all registered observers with the given message.
     * <p>
     * Delivery is synchronous and in registration order.
     * </p>
     *
     * @param message the message to deliver; must not be {@code null}
     */
    protected void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
