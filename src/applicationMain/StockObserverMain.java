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

        anyClient.addStock("ABC", new Money(3, 34));
        anyClient.addStock("BND", new Money(3, 56));
        anyClient.updateStockStatus("ABC", new Money(5, 99));
    }
}
