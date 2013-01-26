package ProductInfoInput;

import Main.NegativePriceException;
import Main.Tools;
import Products.Human;
import Products.Milk;

import java.io.BufferedReader;

/**
 * Created by IntelliJ IDEA.
 * User: dmitry.bushtets
 * Date: 9/12/12
 */
public class ProductKeyboardInput extends ProductInput {
	public ProductKeyboardInput(BufferedReader reader) {
		super(reader);
	}

	@Override
	public Milk inputMilk() {
		System.out.println("Input price:");
		float price = Tools.getFloatFromStream(mReader);
		System.out.println("Input label:");
		String label = Tools.getStringFromStream(mReader);
		System.out.println("Input fatness:");
		float fatness = Tools.getFloatFromStream(mReader);

		Milk milk = null;
		try {
			milk = new Milk(price, label, fatness);
		} catch (NegativePriceException e) {
			e.printStackTrace();
		}

		return milk;
	}

	@Override
	public Human inputHuman() {
		System.out.println("Input price:");
		float price = Tools.getFloatFromStream(mReader);
		System.out.println("Input label:");
		String label = Tools.getStringFromStream(mReader);
		System.out.println("Input sex (0 - female, 1(or any) - male):");
		Human.Sex sex = Tools.getIntFromStream(mReader) == 0 ? Human.Sex.FEMALE : Human.Sex.MALE;

		Human human = null;
		try {
			human = new Human(price, label, sex);
		} catch (NegativePriceException e) {
			e.printStackTrace();
		}

		return human;
	}
}
