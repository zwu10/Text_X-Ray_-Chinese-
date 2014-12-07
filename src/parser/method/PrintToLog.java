package parser.method;

import java.sql.Timestamp;

public class PrintToLog {

	public static void catchCurrentButton(String inputButton, String location) {
		java.util.Date date = new java.util.Date();
		global.Variable.systemLog += new Timestamp(date.getTime())
				+ "  Button [" + inputButton + "] preesed.\n";
		parser.method.FileWrite.writer(global.Variable.systemLog, location);
	}
}
