package styleClassifier;

import java.io.File;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import styleRepresentation.AuthorData;
import styleRepresentation.TextData;

public class KNearestNeighborsClassifier {
	public static double euclidianDist(TextData textd, TextData authd) {
		double distance = 0;
		/*ArrayList<Double> td = textd.getVals(), ad = authd.getVals();
		for (int i = 0; i <  td.size() ; i++) {
			distance += Math.pow(td.get(i) - ad.get(i), 2);
		}*/
		for (String feature : textd.features.keySet()) {
			distance += Math.pow(textd.getNormalized(feature) - authd.getNormalized(feature), 2);
		}
		return Math.sqrt(distance);
	}
	public static List<Entry<String, Double>> getNeighbors(List<TextData> trainingSet, TextData td, int k) {
		ArrayList<Map.Entry<String,Double>>  distances = new ArrayList<Map.Entry<String,Double>>();

		for (TextData authd : trainingSet){
			distances.add(new AbstractMap.SimpleEntry<String, Double>(authd.author ,euclidianDist(td, authd)));
		}
		Collections.sort(distances, new Comparator<Map.Entry<String, Double>>() {
			@Override
			public int compare(Entry<String, Double> e1, Entry<String, Double> e2) {
			    if (e1.getValue() > e2.getValue())
			    	return 1;
		    	else if (e1.getValue() < e2.getValue())
			    	return -1;
			    else
			    	return 0;
			}	
		});
		/*for (int i = 0; i < k ; i++) {
			distances
		}*/
		return distances.subList(0, k-1);
	}
	
	public static Map.Entry<String, Integer> getResponse(List<Entry<String, Double>> neighbors) {
		Map<String, Integer> wordCount = new HashMap<String, Integer>();

		for(Entry<String, Double> word: neighbors) {
			Integer count = wordCount.get(word.getKey());          
			wordCount.put(word.getKey(), (count==null) ? 1 : count+1);
		}
		
		Map.Entry<String, Integer> maxEntry = null;

		for (Map.Entry<String, Integer> entry : wordCount.entrySet())
		{
		    if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
		    {
		        maxEntry = entry;
		    }
		}
		if (maxEntry.getValue() == 1){
			maxEntry = new AbstractMap.SimpleEntry<String, Integer>(neighbors.get(0).getKey(), 1);
		}
		return maxEntry;
	}
	
}
