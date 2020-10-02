public enum Coin {
    UN_LEU        (1 , "C01"),
    CINCI_LEI     (5 , "C05"),
    ZECE_LEI      (10, "C10"),
    CINCI_ZECI_LEI(50, "C50");

    private int coinValue;
    private String coinCode;

    Coin(int coinValue, String coinCode) {
        this.coinValue = coinValue;
        this.coinCode = coinCode;
    }

    public static int getValuesByCode(String coinCode) {
        Coin[] coins = Coin.values();
        for(Coin element : coins) {
            if(coinCode.equalsIgnoreCase(element.getCoinCode())) {
                return element.getCoinValue();
            }
        }
        return 0;
    }

    public int getCoinValue() {
        return coinValue;
    }

    public String getCoinCode() {
        return coinCode;

    }

// to be continued
    public static int getValueByCode (String code) {
        for(Coin coin : Coin.values()) {
            if(code.equals(coin.getCoinCode())){
                return coin.getCoinValue();
            }
        }
        return 0;
    }
}
