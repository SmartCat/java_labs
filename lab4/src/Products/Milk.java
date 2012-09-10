package Products;

import Main.NegativePriceException;

/**
 * Created with IntelliJ IDEA.
 * User: SmartCat
 * Date: 09.09.12
 * Time: 16:52
 */
public class Milk extends Product implements IMilk {
    private float mFatness;

    public Milk() throws NegativePriceException {
        this(0.58f, Milk.class.getName(), 3.2f);
    }

    public Milk(float price, String label, float fatness) throws NegativePriceException {
        super(price, label);
        setFatness(fatness);
    }
    public String toString() {
        return String.format("Milk %s (Fatness: %f) costs %f", getLabel(), mFatness, getPrice());
    }

    public float getFatness() {
        return mFatness;
    }

    public void setFatness(float fatness) {
        if (fatness < 0) fatness = 0;
        if (fatness > 100) fatness = 100;
        this.mFatness = fatness;
    }
}
