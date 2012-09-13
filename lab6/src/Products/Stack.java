package Products;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: SmartCat
 * Date: 09.09.12
 * Time: 17:08
 */
public class Stack<E extends IProduct> implements Cloneable, Serializable {
    private static final int PACK_SIZE = 10;
    private int containerCount = 0;
    private IProduct mContainer[];

    public Stack() {
        mContainer = new IProduct[PACK_SIZE];
    }

	public Stack clone() throws CloneNotSupportedException {
		Stack tempStack = (Stack) super.clone();

		// TODO: is this correct?
		tempStack.mContainer = new IProduct[mContainer.length];
		for (int i = 0; i < containerCount; i++) {
			tempStack.mContainer[i] = ((Product) mContainer[i]).clone();
		}

		return tempStack;
	}

    public void add(E product) {
        if (containerCount == mContainer.length) { //extend array
            mContainer = Arrays.copyOf(mContainer, mContainer.length + PACK_SIZE);
        }

        mContainer[containerCount] = product;
        containerCount++;
    }

    public int count() {
        return containerCount;
    }

    public IProduct getProduct(int index) throws IndexOutOfBoundsException {
        //if (index < 0 || index >= containerCount) throw ne
        return mContainer[index];
    }

    public Iterator<IProduct> objectsReverse() {
        class ReverseIterator implements Iterator<IProduct> {
            private int idx = containerCount - 1;
            @Override
            public boolean hasNext() {
                return (idx >= 0);
            }

            @Override
            public IProduct next() {
                IProduct result = getProduct(idx);
                idx--;
                return result;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        }
        return new ReverseIterator();
    }

    public Iterator objectsMatchingPriceDiapason(final float minPrice, final float maxPrice) {
        return new Iterator() {
            int idx = 0;
            @Override
            public boolean hasNext() {
                if (containerCount > 0 && idx < containerCount) {
                    if (getMatchedObject(idx) != -1) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public Object next() {
                int pos = getMatchedObject(idx);
                idx = pos + 1;
                return mContainer[pos];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            private int getMatchedObject(int idxStart) {
                for (int i = idxStart; i < containerCount; i++) {
                    if (mContainer[i].getPrice() >= minPrice && mContainer[i].getPrice() <= maxPrice) {
                        return i;
                    }
                }
                return -1;
            }
        };
    }

	public void sortBy(Comparator<IProduct> comparator) {
		Arrays.sort(mContainer, comparator);
	}

    public void printAll() {
        for (int i = 0; i < containerCount; i++) {
            System.out.printf("%d: %s\n", i, mContainer[i].toString());
        }
    }
}
