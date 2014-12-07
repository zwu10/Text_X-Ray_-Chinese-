package parser.method;

//import java.io.PrintStream;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.Sentence;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import edu.stanford.nlp.trees.GrammaticalStructure;
import edu.stanford.nlp.trees.GrammaticalStructureFactory;
import edu.stanford.nlp.trees.TreebankLanguagePack;
import edu.stanford.nlp.trees.TypedDependency;
import edu.stanford.nlp.trees.international.pennchinese.ChineseTreebankLanguagePack;

public class Processer {
	private static final String basedir = System.getProperty("SegDemo",
			global.Variable.SEGMENTER_LOCATION);

	public static String[] Segmenter(String input) {
		// System.setOut(new PrintStream(System.out, true, "utf-8"));
		Properties props = new Properties();
		props.setProperty("sighanCorporaDict", basedir);
		// props.setProperty("NormalizationTable", "data/norm.simp.utf8");
		// props.setProperty("normTableEncoding", "UTF-8");
		// below is needed because CTBSegDocumentIteratorFactory accesses it
		props.setProperty("serDictionary", basedir + "/dict-chris6.ser.gz");
		// if (args.length > 0) {
		// props.setProperty("testFile", args[0]);
		// }
		props.setProperty("inputEncoding", "UTF-8");
		props.setProperty("sighanPostProcessing", "true");

		CRFClassifier<CoreLabel> segmenter = new CRFClassifier<CoreLabel>(props);
		segmenter.loadClassifierNoExceptions(basedir + "/ctb.gz", props);
		// for (String filename : args) {
		// segmenter.classifyAndWriteAnswers(filename);
		// }
		String sample = input;
		List<String> segmented = segmenter.segmentString(sample);

		// for (int i = 0; i < segmented.size(); i++)
		// System.out.println(segmented.get(i));

		String[] segmentedText = segmented
				.toArray(new String[segmented.size()]);

		return segmentedText;
	}

	public static Hashtable<String, String> Tagger(String[] input) {
		MaxentTagger tagger = new MaxentTagger(global.Variable.TAGGER_LOCATION);
		Hashtable<String, String> taggedText = new Hashtable<String, String>();

		for (int i = 0; i < input.length; i++) {
			String taggedString = tagger.tagString(input[i]);
			taggedText.put(input[i], taggedString.substring(
					taggedString.indexOf("#"), taggedString.length()));
			// System.out.print(taggedText.get(input[i]));
		}
		return taggedText;
	}

	public static Object[] Parser(String[] input) {
		Object[] parsedResult = new Object[2];

		LexicalizedParser lp = LexicalizedParser
				.loadModel(global.Variable.PARSER_LOCATION);

		List<CoreLabel> rawWords = Sentence.toCoreLabelList(input);
		global.Variable.tree = lp.apply(rawWords);

		parsedResult[0] = global.Variable.tree.pennString();

		TreebankLanguagePack tlp = new ChineseTreebankLanguagePack();
		GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();
		GrammaticalStructure gs = gsf
				.newGrammaticalStructure(global.Variable.tree);
		List<TypedDependency> tdl = gs.typedDependenciesCCprocessed(true);

		parsedResult[1] = tdl;

		return parsedResult;
	}
}
