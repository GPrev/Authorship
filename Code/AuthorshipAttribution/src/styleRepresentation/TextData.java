package styleRepresentation;

import java.util.HashMap;
import java.util.List;

public class TextData {
	public HashMap<Character, Integer> punctuation;
	public double lineLength;
	public TextData(HashMap<Character, Integer> punct, List<Integer> linesLengths) {
		punctuation = new HashMap<Character, Integer>(punct);
		lineLength = ListCalc.median(linesLengths);
	}
}
