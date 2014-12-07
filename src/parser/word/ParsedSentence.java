package parser.word;

public class ParsedSentence {

	private String sentence = null;
	private int numOfWord = 0;

	public ParsedSentence(String sentence, int numOfWord) {
		this.sentence = sentence;
		this.numOfWord = numOfWord;
	}

	public String getSentence() {
		return sentence;
	}

	public int getNumOfWord() {
		return numOfWord;
	}

	public String toString() {
		return sentence + "---" + numOfWord;
	}

}
