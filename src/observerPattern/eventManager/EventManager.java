package observerPattern.eventManager;

import observerPattern.observer.Observer;
import observerPattern.observer.Subject;
import observerPattern.stockObservers.StockBroker;
import observerPattern.stockObservers.StockMonitor;
import stock.Stock;

import java.util.*;
import java.util.stream.Collectors;

public class EventManager extends Subject implements Observer {

    private static EventManager uniqueEventManager;

    private Map<String, List<Observer>> observerMap;

    protected EventManager() {
        super();
        this.observerMap = new HashMap<>();
    }

    public static EventManager getUniqueEventManager() {
        if (uniqueEventManager == null) {
            uniqueEventManager = new EventManager();
        }
        return uniqueEventManager;
    }

    @Override
    public void update(Stock stock, StockEvent event) {

        if (event.equals(StockEvent.Create_Stock)) {


        } else if (event.equals(StockEvent.Update_Stock)) {

        }

        this.notifyObservers(stock, event);
    }

    @Override
    public void registerObserver(Observer observer, StockEvent event) {
        this.observerMap.putIfAbsent(event.toString(), new ArrayList<>());
        this.observerMap.get(event.toString()).add(observer);
    }

    @Override
    public void notifyObservers(Stock stock, StockEvent event) {

        if (event.equals(StockEvent.Update_Stock)) {
            this.observerMap.get(event.toString()).forEach(observer -> observer.update(stock, event));
        } else {
            this.observerMap.values().stream().flatMap(Collection::stream)
                    .collect(Collectors.toList()).forEach(observer -> observer.update(stock, event));
        }
    }
}
