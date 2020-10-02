package products;

public enum  Product {
    COLA     ("Cola"         , "A001", 3),
    DORNA    ("Dorna"        , "A002", 2),
    SNICKERS ("Snickers"     , "A003", 5),
    SEVENDAYS("7 Days  "     , "A004", 8),
    FANTA    ("Fanta Madness", "A005", 3);

    private String name;
    private String code;
    private int    price;

    Product(String name, String code, int price) {
        this.name  = name;
        this.code  = code;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public int getPrice() {
        return price;
    }


}
