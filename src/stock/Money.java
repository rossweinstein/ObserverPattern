package stock;

public class Money {

    private long dollars;
    private int cents;

    public Money(long dollars, int cents) {
        this.dollars = dollars;
        this.cents = cents;
    }

    public String currentBalance() {
        return this.dollars + "." + this.cents;
    }
}
