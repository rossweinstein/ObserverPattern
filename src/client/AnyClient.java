package client;

import stock.Money;
import stock.Stock;
import stock.StockStatus;

import java.util.ArrayList;
import java.util.List;

public class AnyClient {

    private List<Stock> clientStocks;

    public AnyClient() {
        this.clientStocks = new ArrayList<>();
    }

    public void addStock(String stockStymbol, Money startingValue) {
        Stock stock = new Stock(stockStymbol, startingValue);
        this.clientStocks.add(stock);
    }

    public void updateStockStatus(String stockSymbol, Money value) {
        this.clientStocks.stream().forEach(stock -> {
            if (stock.getStockSymbol().equals(stockSymbol)) {
                stock.addStatus(new StockStatus(stock, value));
            }
        });
    }
}
