package Products;

import Main.NegativePriceException;

/**
 * Created with IntelliJ IDEA.
 * User: SmartCat
 * Date: 09.09.12
 * Time: 19:41
 */
public interface IProduct {
    public float getPrice();
    public void setPrice(float price) throws NegativePriceException;
    public String getLabel();
    public void setLabel(String label);
}
