package Products;

import Main.NegativePriceException;

/**
 * Created with IntelliJ IDEA.
 * User: SmartCat
 * Date: 09.09.12
 * Time: 16:54
 */
public class Human extends Product implements IHuman {
    public enum Sex {
        MALE,
        FEMALE,
    }

    private Sex mSex;

    public Human() throws NegativePriceException {
        this(10000.f, String.format("%s%d", Human.class.getName(), Product.PRODUCT_COUNT), Sex.FEMALE);
    }

    public Human(float price, String name, Sex sex) throws NegativePriceException {
        super(price, name);
        setSex(sex);
    }

    public String toString() {
        return String.format("Human %s (%s) costs %f", getLabel(), mSex.name(), getPrice());
    }

    public Sex getSex() {
        return mSex;
    }

    public void setSex(Sex sex) {
        this.mSex = sex;
    }
}
