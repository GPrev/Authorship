package styleRepresentation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class TextData {
	public static TextData max;
	public static TextData min;
	
	public String author;
	public HashMap<Character, Integer> punctuation;
	public HashMap<String, Double> features;
	public double lineLength;
	public int textSize;
	public int nbParagraphs;
	public int nbLines;
	public double nbWords;
	
	public TextData(String author) {
		this.author = author;
		features = new HashMap<String, Double>();
	}
	
	public TextData(String author, HashMap<Character, Integer> punct, List<Integer> linesLengths, int paras, int size, int nbLines, int mName, int abrev) {
		this.author = author;
		features = new HashMap<String, Double>();
		features.put("lineLengths", ListCalc.median(linesLengths));
		features.put("nbParagraphs", (double) paras);
		features.put("textSize", (double) size);
		features.put("nbWords", (double) linesLengths.stream().mapToInt(Integer::intValue).sum() / linesLengths.size());
		features.put("nbLines", (double) nbLines);
		features.put("mName", (double) mName);
		features.put("abrev", (double) abrev);
		//punctuation = new HashMap<Character, Integer>(punct);
		for (Character s : punct.keySet()) {
			features.put("punctuation"+ s, (double) punct.get(s));
		}
		//lineLength = ListCalc.median(linesLengths);
		//nbParagraphs = paras;
		//textSize = size;
		//nbWords = linesLengths.stream().mapToInt(Integer::intValue).sum() / linesLengths.size();
		//this.nbLines = nbLines;
	}
	
	public ArrayList<Double> getVals(){
		return new ArrayList<Double>() {{
			//add((double) textSize);
			add(lineLength); add((double) nbParagraphs); add((double) nbLines); 
			add((double) punctuation.get(';')); add((double) punctuation.get(',')); add((double) punctuation.get('?'));
			add((double) punctuation.get('!')); add((double) punctuation.get(':')); add(nbWords);
		}};
	}
	public static void setMinMaxFeatures(List<TextData> ld) {
		if (ld.size() == 0) {
			return;
		}
		else if (ld.size() == 1) {
			max = ld.get(0);
			min = ld.get(0);
			return;
		}
		max = new TextData("max");
		min = new TextData("min");
		Set<String> keys = ld.get(0).features.keySet();
		for (TextData d : ld) {
			for (String feature : keys) {
				double curVal = d.features.get(feature);
				if (!max.features.containsKey(feature) || max.features.get(feature) < curVal) {
					max.features.put(feature, curVal);
				}
				if (!min.features.containsKey(feature) || min.features.get(feature) > curVal) {
					min.features.put(feature, curVal);
				}
			}
		}
	}
	public double getNormalized(String feature){
		return (this.features.get(feature) - min.features.get(feature)) / (max.features.get(feature) - min.features.get(feature));
	}
}
