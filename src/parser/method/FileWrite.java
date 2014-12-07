package parser.method;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class FileWrite {
	public static void writer(String input, String location) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(location, "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		writer.append(input);
		writer.close();
	}

}
