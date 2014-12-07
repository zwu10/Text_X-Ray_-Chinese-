package parser.method;

public class Reset {
	public static void buttonReset() {
		global.Component.NounButton.setSelected(false);
		global.Component.VerbButton.setSelected(false);
		global.Component.AdjectiveButton.setSelected(false);
		global.Component.AdverbButton.setSelected(false);
		global.Component.PronounButton.setSelected(false);
		global.Component.NumeralButton.setSelected(false);
		global.Component.PrepositionButton.setSelected(false);
		global.Component.ConjunctionButton.setSelected(false);
		global.Component.PunctuationButton.setSelected(false);
		global.Component.LongSentenceButton.setSelected(false);
	}

	public static void wordHighlightingReset() {
		for (int i = 0; i < global.Variable.ParsedWordList.size(); i++) {
			global.Variable.ParsedWordList.get(i).setHighlighting(false);
		}
	}
}
