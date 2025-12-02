import java.util.HashMap;
import java.util.Map;

public class Portfolio {
    private Map<String, Integer> stocks = new HashMap<>(); // stock symbol -> quantity

    public void addStock(String symbol, int quantity) {
        stocks.put(symbol, stocks.getOrDefault(symbol, 0) + quantity);
    }

    public void removeStock(String symbol, int quantity) {
        int currentQuantity = stocks.getOrDefault(symbol, 0);
        if (currentQuantity >= quantity) {
            stocks.put(symbol, currentQuantity - quantity);
        }
    }

    public Map<String, Integer> getStocks() {
        return stocks;
    }
}