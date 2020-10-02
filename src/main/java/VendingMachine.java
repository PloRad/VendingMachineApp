import products.Product;

import java.util.*;


public class VendingMachine {

    private IOService ioService                         = new IOService();
    private PaymentService paymentService               = new PaymentService(ioService);
    private InputValidatorService inputValidatorService = new InputValidatorService();

    private Map<String, List<Product>> productStock     = new TreeMap<>();
    private Map<String, List<Coin>> coinStock = new HashMap<>();


    public void load() {

        loadCoins();
        loadProducts();
    }

    private void loadProducts() {

        for (Product unProdus : Product.values()) {
            List<Product> stockProdus = new ArrayList<>();
            for (int index = 0; index < 1; index++) {
                stockProdus.add(unProdus);
            }
            productStock.put(unProdus.getCode(), stockProdus);
        }
    }

    private void loadCoins() {

        for (Coin unCoin : Coin.values()) {
            List<Coin> stockCoins = new ArrayList<>();
            for (int index = 0; index < 2; index++) {
                stockCoins.add(unCoin);
            }
            coinStock.put(unCoin.getCoinCode(), stockCoins);
        }
    }

    public void start() {

        while (true) {
            ioService.displayStockAsMenu(productStock);
            String userInput = ioService.getUserInput();
            if (isStopCondition(userInput)) {
                return;
            }
            processOrder(userInput);
        }
    }

    private boolean isStopCondition(String userInput) {

        return userInput.equalsIgnoreCase("exit");
    }

    private void processOrder(String userInput) {

        ioService.displayProcessOrder(userInput);
        boolean validationSuccessful = inputValidatorService.validate(userInput, productStock);

        if (!validationSuccessful) {
            return;
        }

        boolean paymentComplete = paymentService
                .processPayment(coinStock, productStock.get(userInput).get(0).getPrice());

        if(!paymentComplete) {
            return;
        }

        releaseProduct(userInput);
    }

    private void releaseProduct(String userInput) {

         ioService.displayProductRelease();
        List<Product> productToDeliver = productStock.get(userInput);
        productToDeliver.remove(0);
        ioService.haveAniceDay();
    }
}
