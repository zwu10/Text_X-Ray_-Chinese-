// This is the main method of this program.
// It will invoke the main GUI of this project show.
// As mentioned in PowerPoint, adjust the memory as necessary.
// -Xms512m
// -Xms1024m
// -Xms2048m
// -Xms3072m

package parser;

import java.awt.EventQueue;

public class Text_XRay_Chinese {

	public static void main(String[] args) {

		// invoke the main GUI
		EventQueue.invokeLater(gui.MainGui.mainRunner);
	}

}
