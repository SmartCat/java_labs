package ProductInfoInput;

import Main.Tools;
import Products.Human;
import Products.Milk;
import Products.Product;

import java.io.BufferedReader;

/**
 * Created by IntelliJ IDEA.
 * User: dmitry.bushtets
 * Date: 9/12/12
 */
abstract public class ProductInput {
	protected BufferedReader mReader;

	ProductInput(BufferedReader reader) {
		mReader = reader;
	}

	public Product inputProduct() {
		Product product = null;

		int answer;

		do {
			String[] tips = {"Add Milk", "Add Human"};
			Tools.printCommandTips(tips);
			answer = Tools.getIntFromStream(mReader);

			switch (answer) {
				case 1:
					product = inputMilk();
					break;
				case 2:
					product = inputHuman();
					break;
				default:
					System.out.println("Something wrong, try again");
					break;
			}
		} while (answer != 1 && answer != 2 || product == null);

		return product;
	}

	abstract Milk inputMilk();
	abstract Human inputHuman();
}
