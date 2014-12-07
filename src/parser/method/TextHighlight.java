package parser.method;

import java.awt.Color;

import javax.swing.JToggleButton;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;

import parser.word.ParsedWord;

public class TextHighlight {

	public static void noHighlightText(String text) {
		global.Component.textPane.setText("");
		StyleConstants.setForeground(global.Component.style, Color.black);
		try {
			global.Component.doc.insertString(global.Component.doc.getLength(),
					text, global.Component.style);
		} catch (BadLocationException e) {
		}
	}

	public static void wordHighlight(String currentProperty,
			JToggleButton currentButton) {

		global.Component.textPane.setText("");

		for (int i = 0; i < global.Variable.ParsedWordList.size(); i++) {
			ParsedWord currentProcessingWord = global.Variable.ParsedWordList
					.get(i);
			if (currentButton.isSelected()) {
				if (currentProcessingWord.getProperty().equals(currentProperty)) {
					StyleConstants.setForeground(global.Component.style,
							currentProcessingWord.getColor());
					try {
						global.Component.doc.insertString(
								global.Component.doc.getLength(),
								currentProcessingWord.getWord(),
								global.Component.style);
					} catch (BadLocationException e) {
					}

					global.Variable.ParsedWordList.get(i).setHighlighting(true);
				} else {

					if (currentProcessingWord.getHighlighting()) {
						StyleConstants.setForeground(global.Component.style,
								currentProcessingWord.getColor());
						try {
							global.Component.doc.insertString(
									global.Component.doc.getLength(),
									currentProcessingWord.getWord(),
									global.Component.style);
						} catch (BadLocationException e) {
						}
					} else {
						StyleConstants.setForeground(global.Component.style,
								Color.black);
						try {
							global.Component.doc.insertString(
									global.Component.doc.getLength(),
									currentProcessingWord.getWord(),
									global.Component.style);
						} catch (BadLocationException e) {
						}
					}
				}
			} // end button is selected
			else {
				if (currentProcessingWord.getProperty().equals(currentProperty)) {
					StyleConstants.setForeground(global.Component.style,
							Color.black);
					try {
						global.Component.doc.insertString(
								global.Component.doc.getLength(),
								currentProcessingWord.getWord(),
								global.Component.style);
					} catch (BadLocationException e) {
					}

					global.Variable.ParsedWordList.get(i)
							.setHighlighting(false);
				} else {
					if (currentProcessingWord.getHighlighting()) {
						StyleConstants.setForeground(global.Component.style,
								currentProcessingWord.getColor());
						try {
							global.Component.doc.insertString(
									global.Component.doc.getLength(),
									currentProcessingWord.getWord(),
									global.Component.style);
						} catch (BadLocationException e) {
						}
					} else {
						StyleConstants.setForeground(global.Component.style,
								Color.black);
						try {
							global.Component.doc.insertString(
									global.Component.doc.getLength(),
									currentProcessingWord.getWord(),
									global.Component.style);
						} catch (BadLocationException e) {
						}
					}
				}
			} // end button is selected else
		} // end for loop
	}

	public static void sentenceHighlight(int numOfWord,
			JToggleButton sentenceButton) {
		global.Component.textPane.setText("");
		if (sentenceButton.isSelected()) {
			for (int i = 0; i < global.Variable.ParsedSentenceList.size(); i++) {
				String currentSentence = global.Variable.ParsedSentenceList
						.get(i).getSentence();
				int currentSentenceNumofWord = global.Variable.ParsedSentenceList
						.get(i).getNumOfWord();

				if (currentSentenceNumofWord >= numOfWord) {
					StyleConstants.setForeground(global.Component.style,
							Color.red);
					try {
						global.Component.doc.insertString(
								global.Component.doc.getLength(),
								currentSentence, global.Component.style);
					} catch (BadLocationException e) {
					}
				} else {
					StyleConstants.setForeground(global.Component.style,
							Color.black);
					try {
						global.Component.doc.insertString(
								global.Component.doc.getLength(),
								currentSentence, global.Component.style);
					} catch (BadLocationException e) {
					}

				}
			}
		} else {

			for (int j = 0; j < global.Variable.ParsedWordList.size(); j++) {
				ParsedWord currentProcessingWord = global.Variable.ParsedWordList
						.get(j);

				if (currentProcessingWord.getHighlighting()) {
					StyleConstants.setForeground(global.Component.style,
							currentProcessingWord.getColor());
					try {
						global.Component.doc.insertString(
								global.Component.doc.getLength(),
								currentProcessingWord.getWord(),
								global.Component.style);
					} catch (BadLocationException e) {
					}
				} else {
					StyleConstants.setForeground(global.Component.style,
							Color.black);
					try {
						global.Component.doc.insertString(
								global.Component.doc.getLength(),
								currentProcessingWord.getWord(),
								global.Component.style);
					} catch (BadLocationException e) {
					}
				}

			}

		}

	}

}
