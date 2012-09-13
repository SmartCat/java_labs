package SortingMethods;

import Products.IProduct;

import java.util.Comparator;

/**
 * Created by IntelliJ IDEA.
 * User: dmitry.bushtets
 * Date: 9/12/12
 */
public class SortingPriceDsc implements Comparator<IProduct> {
	@Override
	public int compare(IProduct o1, IProduct o2) {
		if (o1 == null || o2 == null) return 0;

		return (int) Math.signum(o2.getPrice() - o1.getPrice());
	}
}
