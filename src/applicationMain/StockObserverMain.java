package applicationMain;

import client.AnyClient;
import observerPattern.stockObservers.StockBroker;
import observerPattern.stockObservers.StockMonitor;
import observerPattern.stockObservers.StockObserver;
import stock.Money;

import java.util.Arrays;
import java.util.List;

public class StockObserverMain {

    public static void main (String[] args) {

        AnyClient anyClient = new AnyClient();
        AnyClient otherClient = new AnyClient();

        System.out.println("\n\n----> ADDING STOCK ABC <----\n");

        System.out.println("-OBSERVATIONS-");
        anyClient.addStock("ABC", new Money(3, 34));
        showStocks();

        System.out.println("----> ADDING STOCK BND <----\n");

        System.out.println("-OBSERVATIONS-");
        anyClient.addStock("BND", new Money(3, 56));
        showStocks();

        System.out.println("----> UPDATING STOCK ABC <----\n");

        System.out.println("-OBSERVATIONS-");
        anyClient.updateStockStatus("ABC", new Money(5, 99));
        showStocks();
    }

    private static void showStocks() {
        System.out.println();
        List<StockObserver> stockObservers = Arrays.asList(
                StockBroker.getUniqueInstance(), StockMonitor.getUniqueStockMonitor()
        );

        stockObservers.forEach(stockObserver -> stockObserver.displayStocks());
        System.out.println();
    }
}
