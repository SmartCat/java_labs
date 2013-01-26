package Main;

import Products.IProduct;
import Products.Stack;
import SortingMethods.SortingLabelAsc;
import SortingMethods.SortingLabelDsc;
import SortingMethods.SortingPriceAsc;
import SortingMethods.SortingPriceDsc;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: dmitry.bushtets
 * Date: 9/12/12
 */
public class Tools {
	public static int getIntFromStream(BufferedReader reader) {
		int num = 0;
		try {
			String answerStr = reader.readLine();
			num = Integer.parseInt(answerStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return num;
	}

	public static float getFloatFromStream(BufferedReader reader) {
		float num = 0;
		try {
			String answerStr = reader.readLine();
			num = Float.parseFloat(answerStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return num;
	}

	public static String getStringFromStream(BufferedReader reader) {
		String str = "";
		try {
			str = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}

	public static void sortContainer(Stack<IProduct> stack, BufferedReader reader) {
		System.out.println("Choose sorting method:");
		int answer;

		do {
			String[] tips = {"Price ascending", "Price descending", "Label ascending", "Label descending"};
			Tools.printCommandTips(tips);

			answer = Tools.getIntFromStream(reader);

			switch (answer) {
				case 1:
					stack.sortBy(new SortingPriceAsc());
					break;
				case 2:
					stack.sortBy(new SortingPriceDsc());
					break;
				case 3:
					stack.sortBy(new SortingLabelAsc());
					break;
				case 4:
					stack.sortBy(new SortingLabelDsc());
					break;
				default:
					System.out.println("Something wrong, try again");
					break;
			}
		} while (answer < 1 || answer > 4 );
	}

	public static void printCommandTips(String[] labels) {
		StringBuilder message = new StringBuilder("\n");
		for (int i = 0; i < labels.length; i++) {
			message.append(String.format("%d - %s\n", i+1, labels[i]));
		}
		System.out.print(message.toString());
	}
}
