package observerPattern.eventManager;

import observerPattern.observer.Observer;
import observerPattern.observer.Subject;
import observerPattern.stockObservers.StockBroker;
import observerPattern.stockObservers.StockMonitor;
import observerPattern.stockObservers.StockObserver;
import stock.Stock;

import java.util.*;
import java.util.stream.Collectors;

public class EventManager implements Subject, Observer {

    private static EventManager uniqueInstance;
    private Map<StockEvent, List<Observer>> observerMap;

    protected EventManager() {
        this.observerMap = new HashMap<>();
        this.observerMap.put(StockEvent.Create_Stock, Arrays.asList(StockBroker.getUniqueInstance(),
                StockMonitor.getUniqueInstance()));
        this.observerMap.put(StockEvent.Update_Stock, Arrays.asList(StockMonitor.getUniqueInstance()));
    }

    public static EventManager getUniqueInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new EventManager();
        }
        return uniqueInstance;
    }

    @Override
    public void update(Stock stock, StockEvent event) {

        if (event.equals(StockEvent.Create_Stock)) {
           this.printStockCreationBanner(stock);
        } else if (event.equals(StockEvent.Update_Stock)) {
            this.printStockUpdateBanner(stock);
        }

        this.notifyObservers(stock, event);
        this.displayEvent(event);
    }

    @Override
    public Set<Observer> getObservers() {
        return this.observerMap.values().stream().flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    @Override
    public void registerObserver(Observer observer, StockEvent event) {
        this.observerMap.putIfAbsent(event, new ArrayList<>());
        this.observerMap.get(event.toString()).add(observer);
    }

    @Override
    public void notifyObservers(Stock stock, StockEvent event) {
        this.observerMap.get(event).forEach(observer -> observer.update(stock, event));
    }

    private void displayEvent(StockEvent event) {
        System.out.println("\n----> DISPLAY STOCK CHANGES <----\n");
        this.observerMap.get(event).forEach(observer -> {

            if (observer instanceof StockObserver) {
                StockObserver stockObserver = (StockObserver) observer;
                stockObserver.displayStocks();
            }
        });

    }

    private void printStockCreationBanner(Stock stock) {
        System.out.println("----> ADDING STOCK " + stock.getStockSymbol() + " <----\n");
    }

    private void printStockUpdateBanner(Stock stock) {
        System.out.println("----> UPDATING STOCK " + stock.getStockSymbol() + " <----");
    }
}
