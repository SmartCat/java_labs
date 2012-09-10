import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: dmitry.bushtets
 * Date: 9/10/12
 */
public class Files {
	class StringKey {
		private String mString;

		public StringKey(String string) {
			mString = string;
		}

		public String toString() {
			return mString;
		}

		public int hashCode() {
			final int LENGTH = 4;
			int mul = 1;
			for (int i = 0; i < LENGTH; i++) {
				mul *= (int) mString.charAt(i);
			}

			return mul;
		}

		public boolean equals(Object strKey) {
			return (strKey instanceof StringKey) && mString.equals(((StringKey) strKey).mString);
		}
	}

	public Files() {
	}

	public StringKey getStringKey(String file) {
		return new StringKey(file);
	}

	public HashMap getFiles(String dirName) {
		File dir = new File(dirName);
		String[] fileNameList = dir.list(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				//boolean result = false;
				return name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".gif");
			}
		});

		HashMap<StringKey, String> resultMap = new HashMap<StringKey, String>();
		for (String fileName : fileNameList) {
			resultMap.put(new StringKey(fileName), fileName.concat(" description"));
		}

		return resultMap;
	}
}
