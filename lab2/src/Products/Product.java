package Products;

import Main.NegativePriceException;

/**
 * Created with IntelliJ IDEA.
 * User: SmartCat
 * Date: 09.09.12
 * Time: 16:01
 */
public abstract class Product implements IProduct {
    //TODO: even don't ask me why
    static int PRODUCT_COUNT = 0;
    static float PRODUCT_TOTAL_PRICE = 0;

    public static float GetAveragePrice() {
        if (PRODUCT_COUNT == 0) return 0;

        return PRODUCT_TOTAL_PRICE / PRODUCT_COUNT;
    }

    private float mPrice;
    private String mLabel;

    public Product() throws NegativePriceException {
        this(0.99f /*SALE! Only for 1 hour*/, String.format("%s%d", Product.class.getName(), PRODUCT_COUNT));
    }

    public Product(float price, String label) throws NegativePriceException {
        PRODUCT_COUNT++;
        setPrice(price);
        setLabel(label);
    }

    public String toString() {
        return String.format("Product %s costs %f", mLabel, mPrice);
    }

    public float getPrice() {
        return mPrice;
    }

    public void setPrice(float price) throws NegativePriceException {
        //****************
        //just for fun
        if (price < 0) throw new NegativePriceException(price);
        //****************

        //TODO: even don't ask me why
        PRODUCT_TOTAL_PRICE -= this.mPrice;
        PRODUCT_TOTAL_PRICE += price;

        this.mPrice = price;
    }

    public String getLabel() {
        return mLabel;
    }

    public void setLabel(String label) {
        this.mLabel = label;
    }
}