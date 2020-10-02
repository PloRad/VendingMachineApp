import products.Product;

import java.util.List;
import java.util.Map;

public class InputValidatorService {

    private IOService ioService;

    public boolean validate(String userInput, Map<String, List<Product>> productStock) {

        boolean productCodeExists = productStock.containsKey(userInput);
        if(!productCodeExists){
            ioService.codeNotValid(userInput);
            return false;
        }

        boolean productIsOnStock = productStock.get(userInput).size() > 0;
        if(!productIsOnStock){
            ioService.itemNotAvailable(userInput);
            return false;
        }
        return true;
    }
}
