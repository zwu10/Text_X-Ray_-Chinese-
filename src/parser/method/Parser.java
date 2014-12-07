package parser.method;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import parser.word.ParsedSentence;
import parser.word.ParsedWord;

public class Parser {
	public static void StartParse(String input) {
		// Record parsing start time
		long startTime = System.nanoTime();

		// Read the sample text
		String sampleText = input;

		// Segment the sample text
		String[] segmentedText = parser.method.Processer.Segmenter(sampleText);

		// Tag the sample text
		Hashtable<String, String> taggedText = parser.method.Processer
				.Tagger(segmentedText);

		// Parse the sample text
		Object[] parsedText = parser.method.Processer.Parser(segmentedText);
		String parsedTree = (String) parsedText[0];
		global.Variable.parsedTree = parsedTree + "\n";
		List typedDependency = (List) parsedText[1];
		for (int i = 0; i < typedDependency.size(); i++) {
			global.Variable.parsedTree += typedDependency.get(i) + "\n";
		}

		// Clear and Reassign ParsedWord and ParseSentence LinkedList
		global.Variable.ParsedWordList.clear();
		global.Variable.ParsedSentenceList.clear();
		String currentSentence = "";
		int currentSentenceNumOfWord = 0;
		for (int i = 0; i < segmentedText.length; i++) {
			String word = segmentedText[i];
			String tag = taggedText.get(segmentedText[i]).replaceAll("#", "")
					.replaceAll(" ", "");
			String property = parser.method.Classifier.PropertyClassifier(tag);
			Color color = parser.method.Classifier.ColorClassifier(property);

			if (!word.equals("。")) {
				if (!property.equals("punc.")) {
					currentSentence = currentSentence + word + "  ";
					currentSentenceNumOfWord++;
				} else {
					currentSentence = currentSentence + word + "  ";
				}
			}
			if (word.equals("。")) {
				currentSentence = currentSentence + word + "  ("
						+ currentSentenceNumOfWord + "个词)\n\n";
				global.Variable.ParsedSentenceList.add(new ParsedSentence(
						currentSentence, currentSentenceNumOfWord));
				currentSentence = "";
				currentSentenceNumOfWord = 0;
			}

			global.Variable.ParsedWordList.add(new ParsedWord(word, tag,
					property, color, false));

		}

		// Print the sample text

		global.Variable.parsedResult += "The sample text:\n";

		global.Variable.parsedResult += sampleText + "\n";

		global.Variable.parsedResult += "\n";

		// Print the segmented text

		global.Variable.parsedResult += "The segmented text:\n";
		for (int i = 0; i < segmentedText.length; i++) {

			global.Variable.parsedResult += segmentedText[i] + "    ";
		}

		global.Variable.parsedResult += "\n";

		global.Variable.parsedResult += "\n";

		// Print the tagged text

		global.Variable.parsedResult += "The tagged text:\n";
		for (int i = 0; i < segmentedText.length; i++) {

			global.Variable.parsedResult += segmentedText[i] + "---"
					+ taggedText.get(segmentedText[i]) + "\n";
		}

		global.Variable.parsedResult += "\n";

		// Print the parsed text

		global.Variable.parsedResult += "The parsed text:\n";

		global.Variable.parsedResult += parsedTree + "\n";
		for (int i = 0; i < typedDependency.size(); i++) {

			global.Variable.parsedResult += typedDependency.get(i) + "\n";
		}

		global.Variable.parsedResult += "\n";

		// Record parsing end time
		long endTime = System.nanoTime();
		global.Variable.parseTime = TimeUnit.SECONDS.convert(
				(endTime - startTime), TimeUnit.NANOSECONDS);

		// Print time

		global.Variable.parsedResult += "The total time is:\n";

		global.Variable.parsedResult += global.Variable.parseTime + "";

		global.Variable.parsedResult += "s\n";

		global.Variable.parsedResult += "-----------------------------------------\n\n";

		parser.method.FileWrite.writer(global.Variable.parsedResult,
				global.Variable.RESULT_LOCATION);
	}
}
