package observerPattern.observer;

import observerPattern.eventManager.StockEvent;
import stock.Stock;

public interface Observer {
    void update(Stock stock, StockEvent event);
}