package Products;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: SmartCat
 * Date: 09.09.12
 * Time: 17:08
 */
public class ProductContainer {
    private static final int PACK_SIZE = 10;
    private int nCount = 0;
    private IProduct mContainer[] = new IProduct[PACK_SIZE];

    public ProductContainer() {
    }

    public void add(Product product) {
        if (nCount == mContainer.length) { //extend array
            mContainer = Arrays.copyOf(mContainer, mContainer.length + PACK_SIZE);
        }

        mContainer[nCount] = product;
        nCount++;
    }

    public int count() {
        return nCount;
    }

    public Product getProduct(int index) throws IndexOutOfBoundsException {
        //if (index < 0 || index >= nCount) throw ne
        return (Product) mContainer[index];
    }

    public void sortByPrice(final boolean ascending) {
        Comparator<Object> comparator = new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 == null || o2 == null) return 0;

                Product p1 = (Product) o1;
                Product p2 = (Product) o2;

                int result = (int) Math.signum(p1.getPrice() - p2.getPrice());

                if (!ascending) result = -result;

                return result;
            }
        };

        Arrays.sort(mContainer, comparator);
    }

    public float getAveragePrice() {
        if (count() == 0) return 0;
        float sum = 0;
        for (int i = 0; i < nCount; i++) {
            sum += mContainer[i].getPrice();
        }

        return sum / count();
    }

    public void printAll() {
        for (int i = 0; i < nCount; i++) {
            System.out.printf("%d: %s\n", i, mContainer[i].toString());
        }
    }
}
