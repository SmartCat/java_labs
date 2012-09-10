package Main;

import Products.*;

import java.io.*;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: SmartCat
 * Date: 09.09.12
 * Time: 15:55
 */
public class MainClass {
    private static final String STACK_SERIALIZE_FILE_NAME = "./serialized/stack-product.serial";

    public static void main (String[] args) {
        Stack<IProduct> productContainer = initContainer();

        BufferedReader bufIn = new BufferedReader(new InputStreamReader(System.in));
        int answer;

        do {
            printCommandTips();
            answer = getIntFromStream(bufIn);

            switch (answer) {
                case 1:
                    break;
                case 2:
                    System.out.println("Container:");
                    productContainer.printAll();
                    break;
                case 3:
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

    private static int getIntFromStream(BufferedReader reader) {
        int num = 0;
        try {
            String answerStr = reader.readLine();
            num = Integer.parseInt(answerStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return num;
    }

    private static void printCommandTips() {
        StringBuilder message = new StringBuilder("\n");
        message.append("1 - Add product").append("\n");
        message.append("2 - List products").append("\n");
        message.append("3 - Sort products").append("\n");
        message.append("4 - Exit").append("\n");
        System.out.print(message.toString());
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
