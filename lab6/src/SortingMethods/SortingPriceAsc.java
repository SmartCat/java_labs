package SortingMethods;

import Products.IProduct;

import java.util.Comparator;

/**
 * Created by IntelliJ IDEA.
 * User: dmitry.bushtets
 * Date: 9/12/12
 */
public class SortingPriceAsc implements Comparator<IProduct> {
	@Override
	public int compare(IProduct o1, IProduct o2) {
		if (o1 == null || o2 == null) return 0;

		return (int) Math.signum(o1.getPrice() - o2.getPrice());
	}
}
