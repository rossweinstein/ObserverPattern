package observerPattern.stockObservers;

import observerPattern.eventManager.StockEvent;
import stock.Stock;

public class StockMonitor extends StockObserver {

    private static StockMonitor uniqueInstance;

    protected StockMonitor() {
        super();
    }

    public static StockMonitor getUniqueInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new StockMonitor();
        }
        return uniqueInstance;
    }

    public void displayStocks() {

        System.out.println("--STOCK MONITOR--");
        System.out.println("\tSTOCK\t|\tPRICE\t|\tLast Update"
                +"\n----------------------------------------------------");

        this.getStockList().values().forEach(stock -> {
            System.out.println("\t" + stock.getStockSymbol() +
                    "\t\t|\t" + stock.getCurrentPrice() + "\t|\t" + stock.getLastDateTime());

        });
        System.out.println();
    }

    @Override
    public void update(Stock stock, StockEvent event) {

        if (event.equals(StockEvent.Create_Stock)) {

            if(super.addNewStock(stock)) {
                System.out.println("STOCK MONITOR NOTIFIED: ADDING STOCK LISTENER FOR " + stock.getStockSymbol());
            }
        } else if (event.equals(StockEvent.Update_Stock)) {

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
