import Products.Human;
import Products.Milk;
import Products.Product;
import Products.ProductContainer;

/**
 * Created with IntelliJ IDEA.
 * User: SmartCat
 * Date: 09.09.12
 * Time: 15:55
 */
public class MainClass {
    public static void main (String[] args) {
        ProductContainer productContainer = new ProductContainer();

        //TODO: array with loop ?
        Product product1 = new Milk(); //default price 0.58
        Product product2 = new Human(500000.f, "Droid", Human.Sex.MALE);
        Product product3 = new Milk(1.02f, "Almost Water", 0.001f);
        Product product4 = new Human(); // default price 10000

        productContainer.add(product1);
        productContainer.add(product2);
        productContainer.add(product3);
        productContainer.add(product4);

        //Testing average price
        System.out.printf("Price average (static method): %f\n", Product.GetAveragePrice());
        System.out.printf("Price average (normal method): %f\n", productContainer.getAveragePrice());

        //main
        System.out.print("\n***Main Routine***\n");
        productContainer.printAll();
        System.out.print("\n***Sorting ascending...***\n");
        productContainer.sortByPrice(true);
        productContainer.printAll();
        System.out.print("\n***Sorting descending...***\n");
        productContainer.sortByPrice(false);
        productContainer.printAll();
    }
}
