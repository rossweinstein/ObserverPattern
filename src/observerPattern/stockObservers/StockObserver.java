package observerPattern.stockObservers;

import observerPattern.observer.Observer;
import stock.Stock;
import stock.StockStatus;

import java.util.HashMap;
import java.util.Map;

public abstract class StockObserver implements Observer {

    private Map<String, Stock> stockMap;

    public StockObserver() {
        this.stockMap = new HashMap<>();
    }

    public Map<String, Stock> getStockList() {
        return this.stockMap;
    }

    public StockStatus getCurrentStockStatus(String stockSymbol) {
        return this.stockMap.get(stockSymbol).getCurrentStockStatus();
    }

    public abstract void displayStocks();

    protected boolean addNewStock(Stock stock) {
        return this.stockMap.putIfAbsent(stock.getStockSymbol(), stock) == null;
    }

    protected boolean removeStock(Stock stock) {
        return this.stockMap.remove(stock.getStockSymbol()) != null;
    }
}
