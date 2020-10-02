import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PaymentService {

    private IOService ioService;

    public PaymentService(IOService ioService) {
        this.ioService = ioService;
    }

    public boolean processPayment(Map<String, List<Coin>> coinStock, int price) {
        Map<Integer, List<Coin>> treeMap = new TreeMap<>(Collections.reverseOrder());   //ordonam treeMap DESCRESCATOR
        ioService.displayPaymentMessage(price);
        ioService.displayCoinMenu();
        int totalIntrodus = 0;

        while (totalIntrodus < price) {
            String coinCode = ioService.getUserInput();
            int valoareaMonedeiIntroduse = Coin.getValuesByCode(coinCode);
            for (Coin coin : Coin.values()) {
                if (coin.getCoinValue() == valoareaMonedeiIntroduse) {
                    coinStock.get(coinCode).add(coin);
                }
            }
            totalIntrodus += valoareaMonedeiIntroduse;
            int diferenta = price - totalIntrodus;
            if (diferenta > 0) {
                ioService.amountLeftToPay(diferenta);
            }
        }
        int rest = totalIntrodus - price;

        if (rest > 0) {
            giveChange(coinStock, treeMap, rest);
        }
        ioService.paymentSuccessful();
        return true;
    }

   private boolean hasChange(Map<Integer, List<Coin>> treeMap, int rest) {
       int rest_acumulat_din_stockCoin = 0;

       for (Map.Entry<Integer, List<Coin>> entry : treeMap.entrySet()) {
           if (entry.getKey() > rest) {
               continue;
           }
           List<Coin> lista_Coins = entry.getValue();
           int numarCoins = lista_Coins.size();

           while (numarCoins > 0 && entry.getKey() <= rest) {
               int returnCoinValue = lista_Coins.get(0).getCoinValue();
               rest_acumulat_din_stockCoin += returnCoinValue;
               numarCoins --;
               rest -= returnCoinValue;
           }
       }
       if(rest_acumulat_din_stockCoin < rest) {
           return false;
       }
       return true;
   }

   private void changeListLarge_To_Small(Map<String, List<Coin>> coinStock, Map<Integer, List<Coin>> treeMap) {
       for (Map.Entry<String, List<Coin>> entry : coinStock.entrySet()) {
           List<Coin> listaDeCoins = entry.getValue();
           if (listaDeCoins.size() == 0) {
               continue;
           }
           Coin coin = listaDeCoins.get(0);
           treeMap.put(coin.getCoinValue(), listaDeCoins);
       }
   }

    private void giveChange(Map<String, List<Coin>> coinStock, Map<Integer, List<Coin>> treeMap, int rest) {
        ioService.returnChange(rest);



//        changeListLarge_To_Small(coinStock, treeMap);
        hasChange(treeMap, rest);

        for (Map.Entry<String, List<Coin>> entry : coinStock.entrySet()) {
            List<Coin> listaDeCoins = entry.getValue();
            if (listaDeCoins.size() == 0) {
                continue;
            }
            Coin coin = listaDeCoins.get(0);
            treeMap.put(coin.getCoinValue(), listaDeCoins);
        }

        for (Map.Entry<Integer, List<Coin>> entry : treeMap.entrySet()) {
            List<Coin> lista_Coins = entry.getValue();
            if (entry.getKey() > rest) {
                continue;
            }
            while (lista_Coins.size() > 0 && entry.getKey() <= rest) {
                Coin coinRemoved = lista_Coins.remove(0);
                int removedCoinValue = coinRemoved.getCoinValue();
                rest -= coinRemoved.getCoinValue();
                ioService.amountReturned(removedCoinValue);
            }
        }


//        To be continued -> not working as intended
           if(hasChange(treeMap, rest)){
            for (Map.Entry<Integer, List<Coin>> entry : treeMap.entrySet()) {
                if (entry.getKey() > rest) {
                    continue;
                }
                List<Coin> lista_Coins = entry.getValue();
                while (lista_Coins.size() > 0 && entry.getKey() <= rest) {
                    Coin coinRemoved = lista_Coins.remove(0);
                    int removedCoinValue = coinRemoved.getCoinValue();
                    rest -= coinRemoved.getCoinValue();
                    ioService.amountReturned(removedCoinValue);
                }
            }
        } else if (!hasChange(treeMap, rest)){
            System.out.println("----------- You get the money Back. WE DON'T HAVE CHANGE FOR THAT BILL! ---------------");
        }


    }
}
