package styleRepresentation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TextData {
	public String author;
	public HashMap<Character, Integer> punctuation;
	public double lineLength;
	public int textSize;
	public int nbParagraphs;
	public int nbLines;
	public double nbWords;
	
	public TextData(String author, HashMap<Character, Integer> punct, List<Integer> linesLengths, int paras, int size, int nbLines) {
		this.author = author;
		punctuation = new HashMap<Character, Integer>(punct);
		lineLength = ListCalc.median(linesLengths);
		nbParagraphs = paras;
		textSize = size;
		nbWords = linesLengths.stream().mapToInt(Integer::intValue).sum() / linesLengths.size();
		this.nbLines = nbLines;
	}
	public ArrayList<Double> getVals(){
		return new ArrayList<Double>() {{
			//add((double) textSize);
			add(lineLength); add((double) nbParagraphs); add((double) nbLines); 
			add((double)punctuation.get(';')); add((double) punctuation.get(',')); add((double) punctuation.get('?'));
			add((double) punctuation.get('!')); add((double) punctuation.get(':')); add(nbWords);
		}};
	}
	/*public int medianWords() {
		return (textSize - nbLines - nbLines * lineLength)  / 
	}*/
}
