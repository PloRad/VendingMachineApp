import products.Product;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class IOService {

    private Indentation space = new Indentation();

    public void displayStockAsMenu (Map < String, List < Product >> productStock){
        System.out.println("________________________________________________\nMenu:");
        System.out.println("Code\tProduct\t\t\tPrice\n_____________________________");

        for (Map.Entry<String, List<Product>> entry : productStock.entrySet()) {    // sintaxa pentru a itera intr-un MAP (enhanced for)

            List<Product> randulCuAceleasiProduse = entry.getValue();              //iau valoarea
            if (randulCuAceleasiProduse.size() > 0) {
                Product product = randulCuAceleasiProduse.get(0);

                // indentation
                int productNameLength = product.getName().length();
                String tab = space.indentation(productNameLength);

                System.out.println(product.getCode() + "\t" + product.getName() + tab + product.getPrice() + " RON");
            }
        }
        System.out.println("-----------------------------");
    }

    public void displayCoinMenu() {
        System.out.println("Please insert the code corresponding to the coin you want to insert");
        Coin[] values = Coin.values();
        for (Coin coin : values) {
            System.out.println(coin.getCoinCode() + " for " + coin.getCoinValue());
        }

    }

    public void displayPaymentMessage(int price) {

        System.out.println("You have to make a payment of " + price + " to get the selected product");
    }

    public String getUserInput() {

        System.out.print("Please insert your option: ");
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    public void displayProcessOrder(String userInput) {

        System.out.println("Processing order " + userInput);
    }

    public void displayProductRelease() {
        System.out.println("Releasing product...");
    }

    public void haveAniceDay() {
        System.out.println("Have a nice day!");
    }

    public void amountLeftToPay(int diferenta) {
        System.out.println("Please introduce the amount: " + diferenta);
    }

    public void paymentSuccessful() {
        System.out.println("Payment Successful!");
    }

    public void returnChange(int rest) {
        System.out.println("Returning " + rest + " change...");
    }

    public void amountReturned(int removedCoinValue) {
        System.out.println("Returning: " + removedCoinValue + " RON");
    }

    public void codeNotValid(String userInput) {
        System.out.println("Codul " + userInput + " nu este valid.");
    }

    public void itemNotAvailable(String userInput) {
        System.out.println("Unfortunately, item " + userInput + " is no longer available.");
    }
}
