package Products;

import Main.NegativePriceException;

/**
 * Created with IntelliJ IDEA.
 * User: SmartCat
 * Date: 09.09.12
 * Time: 16:01
 */
public abstract class Product implements IProduct {
    private float mPrice;
    private String mLabel;

    public Product() throws NegativePriceException {
        this(0.99f /*SALE! Only for 1 hour*/, Product.class.getName());
    }

    public Product(float price, String label) throws NegativePriceException {
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

        this.mPrice = price;
    }

    public String getLabel() {
        return mLabel;
    }

    public void setLabel(String label) {
        this.mLabel = label;
    }
}
