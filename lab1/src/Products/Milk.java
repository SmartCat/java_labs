package Products;

/**
 * Created with IntelliJ IDEA.
 * User: SmartCat
 * Date: 09.09.12
 * Time: 16:52
 */
public class Milk extends Product {
    private float mFatness;

    public Milk() {
        this(0.58f, String.format("%s%d", Milk.class.getName(), Product.PRODUCT_COUNT), 3.2f);
    }

    public Milk(float price, String label, float fatness) {
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
