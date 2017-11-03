package observerPattern.observer;

import observerPattern.eventManager.StockEvent;
import stock.Stock;
import java.util.Set;

public interface Subject {
    Set<Observer> getObservers();
    void registerObserver(Observer observer, StockEvent event);
    void notifyObservers(Stock stock, StockEvent event);
}
