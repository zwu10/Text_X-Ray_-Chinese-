package parser.word;

import java.awt.Color;

public class ParsedWord {
	private String word = null;
	private String tag = null;
	private String property = null;
	private Color highlightColor = null;
	private boolean highlighting = false;

	public ParsedWord(String word, String tag, String property,
			Color highlightColor, boolean highlighting) {
		this.word = word;
		this.tag = tag;
		this.property = property;
		this.highlightColor = highlightColor;
		this.highlighting = highlighting;
	}

	public String getWord() {
		return word;
	}

	public String getTag() {
		return tag;
	}

	public String getProperty() {
		return property;
	}

	public Color getColor() {
		return highlightColor;
	}

	public void setHighlighting(boolean highlighting) {
		this.highlighting = highlighting;
	}

	public boolean getHighlighting() {
		return highlighting;
	}

	public String toString() {
		return word + " " + tag + " " + property + " " + highlightColor + " "
				+ highlighting;
	}

}
