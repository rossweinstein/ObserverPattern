package observerPattern.observer;

import observerPattern.eventListener.StockEvents;

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

    public void registerObserver(Observer observer, Object hint) {
        this.getObservers().add(observer);
        this.notifyObservers(this, StockEvents.Add_Stock);
    }


    public void removeObserver(Observer observer, Object hint) {
        // apart of observer pattern, but unneeded for assignment
    }

    public void notifyObservers(Subject subject, Object hint) {
        this.observers.forEach(observer -> observer.update(subject, hint));
    }
}
