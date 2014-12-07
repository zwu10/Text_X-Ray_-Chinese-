package global;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;

import javax.swing.text.Style;
import javax.swing.text.StyledDocument;

public class Component {
	public static JTextPane textPane = new JTextPane();
	public static final StyledDocument doc = textPane.getStyledDocument();
	public static final Style style = textPane.addStyle(null, null);
	// public static JTextArea textArea = new JTextArea(
	// parser.text.SampleText.sampleText);

	public static final JToggleButton NounButton = new JToggleButton(
			"名词 (noun)");
	public static final JToggleButton VerbButton = new JToggleButton(
			"动词 (verb)");
	public static final JToggleButton AdjectiveButton = new JToggleButton(
			"形容词 (adj.)");
	public static final JToggleButton AdverbButton = new JToggleButton(
			"副词 (adv.)");
	public static final JToggleButton PronounButton = new JToggleButton(
			"代词 (pron.)");
	public static final JToggleButton NumeralButton = new JToggleButton(
			"量词 (num.)");
	public static final JToggleButton PrepositionButton = new JToggleButton(
			"介词 (prep.)");
	public static final JToggleButton ConjunctionButton = new JToggleButton(
			"连词 (conj.)");
	public static final JToggleButton PunctuationButton = new JToggleButton(
			"标点 (punc.)");
	public static final JToggleButton LongSentenceButton = new JToggleButton(
			"长度超过x个词的句子 (sentences with x or more words)");

	public static final JFrame waitingFrame = new JFrame("正在剖析 (parsing)");

	public static JLabel textLabel = new JLabel("正在剖析，请稍等。。。。。。" + " "
			+ global.Variable.timerTime + "秒");
	public static JLabel textLabel2 = new JLabel("parsing, please wait..."
			+ " " + global.Variable.timerTime + "s");

}
