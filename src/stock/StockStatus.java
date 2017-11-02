package stock;

import java.time.LocalDateTime;

public class StockStatus {

    private Money price;
    private Stock stock;
    private LocalDateTime dateTime;

    public StockStatus(Stock stock, Money stockPrice) {
        this.price = stockPrice;
        this.stock = stock;
        this.dateTime = LocalDateTime.now();
    }

    public String toString() {
        return this.stock.getStockSymbol() + ": $" + this.price.currentBalance() + " [" + this.dateTime.toString() + "]";
    }

    public Money getPrice() {
        return this.price;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public Stock getStock() {
        return this.stock;
    }
}
