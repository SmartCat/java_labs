package Main;

import Products.*;

import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: SmartCat
 * Date: 09.09.12
 * Time: 15:55
 */
public class MainClass {
    public static void main (String[] args) {
	    Stack<IProduct> productContainer = new Stack<IProduct>();

	    //TODO: array with loop ?
	    Product product1 = null; //default price 0.58
	    Product product2 = null;
	    Product product3 = null;
	    Product product4 = null; // default price 10000
	    try {
		    product1 = new Milk();
		    product2 = new Human(500000.f, "Droid", Human.Sex.MALE);
		    product3 = new Milk(1.02f, "Almost Water", 0.001f);
		    product4 = new Human();
	    } catch (NegativePriceException e) {
		    e.printStackTrace();
	    }

	    productContainer.add(product1);
	    productContainer.add(product2);
	    productContainer.add(product3);
	    productContainer.add(product4);

	    //main
	    System.out.print("\n***Main Routine***\n");
	    productContainer.printAll();

	    System.out.print("\n***Average Price***\n");
	    System.out.printf("Average price: %f\n", avgPrice(productContainer));

	    Stack newStack = null;
	    try {
		    newStack = productContainer.clone();
		    // checking clone()
		    ((Human) newStack.getProduct(1)).setSex(Human.Sex.FEMALE); // checking clone()
		    ((Human) newStack.getProduct(3)).setSex(Human.Sex.MALE); // checking clone()
		    ((Milk) newStack.getProduct(0)).setFatness(1); // checking clone()
		    ((Milk) newStack.getProduct(2)).setFatness(1); // checking clone()
	    } catch (CloneNotSupportedException e) {
		    e.printStackTrace();
	    }

	    productContainer.printAll();
	    if (newStack != null) {
		    newStack.printAll();
	    }

    }

	public static float avgPrice(Stack<IProduct> container) {
		if (container.count() == 0) return 0;
		float sum = 0;

		Iterator<IProduct> iter = container.objectsReverse();
		while (iter.hasNext()) {
			sum += iter.next().getPrice();
		}
		return sum / container.count();
	}
}
