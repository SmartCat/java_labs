package Main;

/**
 * Created with IntelliJ IDEA.
 * User: SmartCat
 * Date: 09.09.12
 * Time: 20:01
 */
public class NegativePriceException extends Exception {
    private float price;

    public NegativePriceException(float price) {
        this.price = price;
    }

    public String toString() {
        return String.format("%s %f", NegativePriceException.class.getName(), price);
    }
}
