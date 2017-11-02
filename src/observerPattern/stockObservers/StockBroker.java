package observerPattern.stockObservers;

import observerPattern.eventManager.EventManager;
import observerPattern.eventManager.StockEvent;
import stock.Stock;

public class StockBroker extends StockObserver {

    private static StockBroker uniqueInstance;

    protected StockBroker() {
        super();
    }

    public static StockBroker getUniqueInstance() {

        if (uniqueInstance == null) {
            uniqueInstance = new StockBroker();
        }
        return uniqueInstance;
    }

    public void displayStocks() {
        System.out.println("--STOCK BROKER--");
        System.out.print("STOCKS: ");

        this.getStockList().values().forEach(stock -> {
            System.out.print(stock.getStockSymbol() + " ");
        });
        System.out.println("\n");
    }

    @Override
    public void update(Stock stock, StockEvent event) {

        if (event.equals(StockEvent.Create_Stock)) {

            stock.registerObserver(EventManager.getUniqueEventManager(), event);

            if (super.addNewStock(stock)) {
                System.out.println("STOCK BROKER NOTIFIED: ADDING STOCK LISTENER FOR " + stock.getStockSymbol());
            }
        }
    }
}
