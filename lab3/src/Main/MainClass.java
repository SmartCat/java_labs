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

        System.out.print("\n***Reverse Iterator...***\n");
        Iterator iter = productContainer.objectsReverse();
        while (iter.hasNext()) {
            System.out.printf("%s\n", iter.next().toString());
        }

        System.out.print("\n***Price matching iterator...***\n");
        Iterator iter2 = productContainer.objectsMatchingPriceDiapason(1.f, 200000.f);
        while (iter2.hasNext()) {
            System.out.printf("%s\n", iter2.next().toString());
        }
    }
}
