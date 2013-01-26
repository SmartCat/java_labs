package ProductInfoInput;

import Main.NegativePriceException;
import Products.Human;
import Products.Milk;

import java.io.BufferedReader;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: dmitry.bushtets
 * Date: 9/12/12
 */
public class ProductRandomInput extends ProductInput {
	private Random rnd = new Random();
	private static final String LABEL_FORMAT = "Random%s-%d";

	public ProductRandomInput(BufferedReader reader) {
		super(reader);
	}

	@Override
	public Milk inputMilk() {
		float price = rnd.nextFloat() * 10;
		String label = String.format(LABEL_FORMAT, "Milk", rnd.nextInt());
		float fatness = rnd.nextFloat() * 100;
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
		float price = rnd.nextFloat() * 10000;
		String label = String.format(LABEL_FORMAT, "Human", rnd.nextInt());
		Human.Sex sex = (rnd.nextBoolean()) ? Human.Sex.FEMALE : Human.Sex.MALE;
		Human human = null;
		try {
			human = new Human(price, label, sex);
		} catch (NegativePriceException e) {
			e.printStackTrace();
		}
		return human;
	}
}
