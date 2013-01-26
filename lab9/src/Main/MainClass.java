package Main;

import Network.TCPServer;
import ProductInfoInput.ProductInput;
import ProductInfoInput.ProductKeyboardInput;
import ProductInfoInput.ProductRandomInput;
import Products.*;

import java.io.*;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: SmartCat
 * Date: 09.09.12
 * Time: 15:55                                        1
 */
public class MainClass {
    private static final String STACK_SERIALIZE_FILE_NAME = "./serialized/stack-product.serial";
	private static ProductInput productInput;

    public static void main (String[] args) {
        Stack<IProduct> productContainer = initContainer();

        BufferedReader bufIn = new BufferedReader(new InputStreamReader(System.in));
	    setProductInput(bufIn);

        new TCPServer(productContainer);

        int answer;

        do {
	        String[] tips = {"Add product", "List products", "Sort products", "Exit"};
            Tools.printCommandTips(tips);
            answer = Tools.getIntFromStream(bufIn);

            switch (answer) {
                case 1:
	                productContainer.add(productInput.inputProduct());
                    break;
                case 2:
                    System.out.println("Container:");
                    productContainer.printAll();
                    break;
                case 3:
	                Tools.sortContainer(productContainer, bufIn);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Something wrong, try again");
                    break;
            }

        } while (answer != 4);


        try {
            saveContainerToFile(productContainer, STACK_SERIALIZE_FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Stack<IProduct> initContainer() {
        Stack<IProduct> productStack;
        try {
            productStack = initContainerFromFile(STACK_SERIALIZE_FILE_NAME);
        } catch (IOException e) {
            //e.printStackTrace();
            productStack = initContainerByDefault();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            productStack = initContainerByDefault();
        }

        return productStack;
    }

    private static Stack<IProduct> initContainerByDefault() {
        Stack<IProduct> productStack = new Stack<IProduct>();

        try {
            Product product1 = new Human(500000.f, "Droid", Human.Sex.MALE);
            productStack.add(product1);
        } catch (NegativePriceException e) {
            e.printStackTrace();
        }

        return productStack;
    }

    private static Stack<IProduct> initContainerFromFile(String filename) throws IOException, ClassNotFoundException {
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(filename));

        Object obj = is.readObject();

        Stack<IProduct> stack;
        if (obj instanceof Stack) {
            //TODO: unsafe operation
            stack = (Stack<IProduct>) obj;
        } else {
            throw new ClassNotFoundException();
        }

        return stack;
    }

    private static void saveContainerToFile(Stack<IProduct> productStack, String filename) throws IOException {
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filename));
        os.writeObject(productStack);
        os.close();
    }

	private static void setProductInput(BufferedReader reader) {
		int answer;

		System.out.println("Choose product info source:");
		do {
			String[] tips = {"Random", "Keyboard"};
			Tools.printCommandTips(tips);
			answer = Tools.getIntFromStream(reader);

			switch (answer) {
				case 1:
					productInput = new ProductRandomInput(reader);
					break;
				case 2:
					productInput = new ProductKeyboardInput(reader);
					break;
				default:
					System.out.println("Something wrong, try again");
					break;
			}

		} while (answer != 1 && answer != 2);
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
