package observerPattern.observer;

import observerPattern.eventManager.StockEvent;
import stock.Stock;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {

    private List<Observer> observers;

    public Subject() {
        this.observers = new ArrayList<>();
    }

    public List<Observer> getObservers() {
        return this.observers;
    }

    public abstract void registerObserver(Observer observer, StockEvent event);

    public void notifyObservers(Stock stock, StockEvent event) {
        this.observers.forEach(observer -> observer.update(stock, event));
    }
}
