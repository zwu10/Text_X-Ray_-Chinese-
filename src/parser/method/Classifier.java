package parser.method;

import java.awt.Color;

public class Classifier {
	public static String PropertyClassifier(String tag) {
		String Property = null;

		// noun
		if (tag.equals("NR") || tag.equals("NT") || tag.equals("NN"))
			Property = "noun";

		// verb
		else if (tag.equals("VC") || tag.equals("VE") || tag.equals("VV"))
			Property = "verb";

		// adj.
		else if (tag.equals("VA"))
			Property = "adj.";

		// adv.
		else if (tag.equals("AD"))
			Property = "adv.";

		// pron.
		else if (tag.equals("PN"))
			Property = "pron.";

		// num.
		else if (tag.equals("M"))
			Property = "num.";

		// prep.
		else if (tag.equals("P"))
			Property = "prep.";

		// conj.
		else if (tag.equals("CC") || tag.equals("CS"))
			Property = "conj.";

		// punc.
		else if (tag.equals("PU"))
			Property = "punc.";

		// others
		else
			Property = "other";

		return Property;
	}

	public static Color ColorClassifier(String property) {
		Color color = null;

		// noun, green
		if (property.equals("noun"))
			color = Color.green;

		// verb, pink
		else if (property.equals("verb"))
			color = Color.pink;

		// adj., red
		else if (property.equals("adj."))
			color = Color.red;

		// adv., blue
		else if (property.equals("adv."))
			color = Color.blue;

		// pron., black
		else if (property.equals("pron."))
			color = new Color(255, 0, 127);

		// num., MAGENTA
		else if (property.equals("num."))
			color = Color.MAGENTA;

		// prep., GRAY
		else if (property.equals("prep."))
			color = Color.GRAY;

		// conj., ORANGE
		else if (property.equals("conj."))
			color = Color.ORANGE;

		// punc., CYAN
		else if (property.equals("punc."))
			color = Color.CYAN;

		// others
		else
			color = Color.black;

		return color;
	}

}
