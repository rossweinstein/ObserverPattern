package observerPattern.stockObservers;

import observerPattern.eventListener.StockEvents;
import observerPattern.observer.Subject;
import stock.Stock;

public class StockMonitor extends StockObserver {

    private static StockMonitor stockMonitor;

    protected StockMonitor() {
        super();
    }

    public static StockMonitor getUniqueStockMonitor() {
        if (stockMonitor == null) {
            stockMonitor = new StockMonitor();
        }
        return stockMonitor;
    }

    public void displayStocks() {

        System.out.println("--STOCK MONITOR--");
        System.out.println("\tSTOCK\t|\tPRICE\t|\tLast Update"
                +"\n----------------------------------------------------");

        this.getStockList().values().forEach(stock -> {
            System.out.println("\t" + stock.getStockSymbol() +
                    "\t\t|\t" + stock.getPrice() + "\t|\t" + stock.getDateTime());

        });
        System.out.println();
    }

    @Override
    public void update(Subject subject, Object hint) {

        if (!(subject instanceof Stock)) return;
        Stock stock = (Stock) subject;

        if (hint.equals(StockEvents.Add_Stock)) {
            if(super.addNewStock(stock)) {
                System.out.println("STOCK MONITOR NOTIFIED: ADDING STOCK LISTENER FOR " + stock.getStockSymbol());
            }
        } else if (hint.equals(StockEvents.Update_Stock)) {
            this.updateStockStatus(stock);
            System.out.println("\nSTOCK MONITOR NOTIFIED: UPDATING STOCK STATUS FOR " + stock.getStockSymbol());
        }
    }

    private void updateStockStatus(Stock stock) {
        if (this.getStockList().containsKey(stock.getStockSymbol())) {
            this.getStockList().replace(stock.getStockSymbol(), stock);
        }
    }
}
