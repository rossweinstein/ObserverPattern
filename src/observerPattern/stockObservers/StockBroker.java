package observerPattern.stockObservers;

import observerPattern.eventListener.StockEvents;
import stock.Stock;
import observerPattern.observer.Subject;

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
    public void update(Subject subject, Object hint) {

        if (!(subject instanceof Stock)) return;
        Stock stock = (Stock) subject;

        if (hint.equals(StockEvents.Add_Stock)) {
            if (super.addNewStock(stock)) {
                System.out.println("STOCK BROKER NOTIFIED: ADDING STOCK LISTENER FOR " + stock.getStockSymbol());
            }
        }
    }
}
