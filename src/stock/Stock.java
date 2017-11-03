package stock;

import observerPattern.eventManager.EventManager;
import observerPattern.eventManager.StockEvent;
import observerPattern.observer.Observer;
import observerPattern.observer.Subject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Stock implements Subject {

    private String stockSymbol;
    private List<StockStatus> stockStatus;
    private Set<Observer> observers;

    public Stock(String stockSymbol, Money startingPrice) {
        this.stockSymbol = stockSymbol;
        this.stockStatus = new ArrayList<>();
        this.stockStatus.add(new StockStatus(this, startingPrice));
        this.observers = new HashSet<>();
        this.registerObserver(EventManager.getUniqueInstance(), StockEvent.Create_Stock);
    }

    @Override
    public Set<Observer> getObservers() {
        return this.observers;
    }

    public void addStatus(StockStatus stockStatus) {
        this.stockStatus.add(stockStatus);
        this.notifyObservers(this, StockEvent.Update_Stock);
    }

    public StockStatus getCurrentStockStatus() {
        return stockStatus.get(this.stockStatus.size() - 1);
    }

    public String getStockSymbol() {
        return this.stockSymbol;
    }

    public String getCurrentPrice() {
        return "$" + this.getCurrentStockStatus().getPrice().currentBalance();
    }

    public String getLastDateTime() {
        return this.getCurrentStockStatus().getDateTime().toString();
    }

    @Override
    public void registerObserver(Observer observer, StockEvent event) {
        this.getObservers().add(observer);
        this.notifyObservers(this, StockEvent.Create_Stock);
    }

    @Override
    public void notifyObservers(Stock stock, StockEvent event) {
        this.observers.forEach(observer -> observer.update(stock, event));
    }
}
