public class TradingService {
    private UserService userService;
    private PortfolioService portfolioService;
    private MarketService marketService;

    public TradingService(UserService userService, PortfolioService portfolioService, MarketService marketService) {
        this.userService = userService;
        this.portfolioService = portfolioService;
        this.marketService = marketService;
    }

    public boolean buyStock(String userId, String symbol, int quantity) {
        User user = userService.getUser(userId);
        if (user == null) return false;

        double price = marketService.getRealTimePrice(symbol) * quantity;
        if (user.getBalance() >= price) {
            user.setBalance(user.getBalance() - price);
            Portfolio portfolio = portfolioService.getPortfolio(userId);
            portfolio.addStock(symbol, quantity);
            return true;
        }
        return false;
    }

    public boolean sellStock(String userId, String symbol, int quantity) {
        Portfolio portfolio = portfolioService.getPortfolio(userId);
        if (portfolio.getStocks().getOrDefault(symbol, 0) >= quantity) {
            double price = marketService.getRealTimePrice(symbol) * quantity;
            User user = userService.getUser(userId);
            user.setBalance(user.getBalance() + price);
            portfolio.removeStock(symbol, quantity);
            return true;
        }
        return false;
    }
}