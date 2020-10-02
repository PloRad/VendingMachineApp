public class Indentation {
    public String indentation(int productNameLength) {

        String tab = "\t";
        if(productNameLength <= 3){
            tab = "\t\t\t\t";
        } else if (productNameLength <= 7) {
            tab = "\t\t\t";
        } else if (productNameLength > 7 && productNameLength <= 10) {
            tab = "\t\t";
        }
        return tab;
    }
}