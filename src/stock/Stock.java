package stock;

import observerPattern.eventManager.EventManager;
import observerPattern.eventManager.StockEvent;
import observerPattern.observer.Observer;
import observerPattern.observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class Stock extends Subject {

    private String stockSymbol;
    private List<StockStatus> stockStatus;

    public Stock(String stockSymbol, Money startingPrice) {
        super();
        this.stockSymbol = stockSymbol;
        this.stockStatus = new ArrayList<>();
        this.stockStatus.add(new StockStatus(this, startingPrice));
        this.registerObserver(EventManager.getUniqueEventManager(), StockEvent.Create_Stock);
    }

    public void addStatus(StockStatus stockStatus) {
        this.stockStatus.add(stockStatus);
        this.notifyObservers(this, StockEvent.Update_Stock);
    }

    public List<StockStatus> getStockStatus() {
        return this.stockStatus;
    }

    public StockStatus getCurrentStockStatus() {
        return stockStatus.get(this.stockStatus.size() - 1);
    }

    public String getStockSymbol() {
        return this.stockSymbol;
    }

    public String getPrice() {
        return "$" + this.getCurrentStockStatus().getPrice().currentBalance();
    }

    public String getDateTime() {
        return this.getCurrentStockStatus().getDateTime().toString();
    }

    @Override
    public void registerObserver(Observer observer, StockEvent event) {
        this.getObservers().add(observer);
        this.notifyObservers(this, StockEvent.Create_Stock);
    }
}
