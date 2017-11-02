package observerPattern.eventListener;

import observerPattern.observer.Observer;
import observerPattern.observer.Subject;
import observerPattern.stockObservers.StockBroker;
import observerPattern.stockObservers.StockMonitor;
import stock.Stock;

import java.util.*;
import java.util.stream.Collectors;

public class EventListener extends Subject implements Observer {

    private static EventListener uniqueEventListener;

    private Map<String, List<Observer>> observerMap;

    protected EventListener() {
        super();
        this.observerMap = new HashMap<>();
        this.registerStockBrokerAndStockMonitor();
    }

    public static EventListener getUniqueEventListener() {
        if (uniqueEventListener == null) {
            uniqueEventListener = new EventListener();
        }
        return uniqueEventListener;
    }

    @Override
    public void update(Subject subject, Object hint) {
       this.notifyObservers(subject, hint);
    }

    @Override
    public void registerObserver(Observer observer, Object hint) {
        this.observerMap.putIfAbsent(hint.toString(), new ArrayList<>());
        this.observerMap.get(hint.toString()).add(observer);
    }

    @Override
    public void notifyObservers(Subject subject, Object hint) {

        if (hint.equals(StockEvents.Update_Stock)) {
            this.observerMap.get(hint.toString()).forEach(observer -> observer.update(subject, hint));
        } else {
            this.observerMap.values().stream().flatMap(Collection::stream)
                    .collect(Collectors.toList()).forEach(observer -> observer.update(subject, hint));
        }
    }

    private void registerStockBrokerAndStockMonitor() {
        this.registerObserver(StockBroker.getUniqueInstance(), StockEvents.Add_Stock);
        this.registerObserver(StockMonitor.getUniqueStockMonitor(), StockEvents.Add_Stock);
        this.registerObserver(StockMonitor.getUniqueStockMonitor(), StockEvents.Update_Stock);
    }
}
