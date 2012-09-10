import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: SmartCat
 * Date: 09.09.12
 * Time: 15:55
 */
public class MainClass {
    public static void main (String[] args) {
	    Files files = new Files();
	    HashMap map = files.getFiles("./img");

	    for (Object obj : map.entrySet()) {
		    Map.Entry pair = (Map.Entry) obj;
		    System.out.println(pair.getKey() + " = " + pair.getValue());
	    }

	    Scanner scanner = new Scanner(System.in);
	    String filename = scanner.nextLine();
	    String description = (String) map.get(files.getStringKey(filename));
	    if (description == null) {
		    System.out.println("No file");
	    } else {
	        System.out.printf("Requested description: %s", description);
	    }
    }
}
