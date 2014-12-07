package global;

import java.util.LinkedList;

import edu.stanford.nlp.trees.Tree;
import parser.word.ParsedSentence;
import parser.word.ParsedWord;

public class Variable {

	public final static String SEGMENTER_LOCATION = "lib\\local\\LocalSegmenter";
	public final static String TAGGER_LOCATION = "lib\\local\\LocalTagger\\"
			+ "chinese-distsim" + ".tagger";
	public final static String PARSER_LOCATION = "lib\\local\\LocalLexparser\\"
			+ "chineseFactored.ser" + ".gz";

	public final static String RESULT_LOCATION = "result\\"
			+ "Chinese_Parser_Result" + ".txt";

	public final static String LOG_LOCATION = "log\\" + "Chinese_Parser_Log"
			+ ".txt";

	public static int EditorFontSize = 16;

	public static LinkedList<ParsedWord> ParsedWordList = new LinkedList<ParsedWord>();
	public static LinkedList<ParsedSentence> ParsedSentenceList = new LinkedList<ParsedSentence>();

	public static String parsedTree = "";

	public static long parseTime = 0;

	public static int timerTime = 0;

	public static Tree tree = null;

	public static boolean treeGraphIsDisplaying = false;

	public static javax.swing.Timer parseTimer = null;

	public static String parsedResult = "";

	public static String systemLog = "";

}
