package styleRepresentation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import Stats.MulticlassStat;
import styleClassifier.KNearestNeighborsClassifier;
import fileListBuilding.*;

public class DataRepresentation {
	public static List<List<Integer>> confusionM = null;
	
	public static List<List<Integer>> getMatrix(){
		if (confusionM != null)
			return confusionM;
		else
			throw new NullPointerException("Matrice de confusion non initialisée (vous dever parser d'abord)");
	}
	
	
	public static List<TextData> parseAll(List<Article> artArray) {
		initMatrix();
		ArrayList<TextData> textsData = new ArrayList<TextData>();
		HashMap<String, AuthorData> authData = new HashMap<String, AuthorData>();
		for (Article article : artArray){
			/*if (!authData.containsKey(article.getAuthor())) {
				authData.put(article.getAuthor(), new AuthorData());
			}*/
				//authData.get(article.getAuthor()).textsData.add(parse(article.getCurrent()));
			textsData.add(parse(article));
		}
		return textsData;
	}
	
	public static TextData parse(Article article) {
		if (!article.getCurrent().exists() || !article.getCurrent().canRead()) {
			System.out.println("Error with file " + article.getCurrent().getName());
			return null;
		}
		
		
		byte[] bytes = null;
		int paragraphs = 1;
		HashMap<Character, Integer> punctuation = new HashMap<Character, Integer>();
		punctuation.put(';', 0);
		punctuation.put('!', 0);
		punctuation.put('?', 0);
		punctuation.put(':', 0);
		punctuation.put(',', 0);
		punctuation.put('.', 0);
		punctuation.put('-', 0);
		punctuation.put('(', 0);
		punctuation.put('"', 0);
		punctuation.put('\'', 0);
		int abrev = 0;
		int mName = 0;
		//punctuation.put(')', 0);
		try {
			bytes = Files.readAllBytes(Paths.get(article.getCurrent().getPath()));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String text = new String(bytes, StandardCharsets.UTF_8);
		for(int i = 0; i < text.length(); i++) {
			Character c = text.charAt(i);
		    if(punctuation.containsKey(c)) { 
		    	punctuation.put(text.charAt(i), punctuation.get(c) + 1);
		    }
		}
		//"(\\.|;)"
		String[] lines = text.split("\n");
		ArrayList<Integer> numberOfWords = new ArrayList<Integer>();
		for (String line : lines) {
			if (isTitle(line)) {
				paragraphs++;
			}
			else {
				for (String word : line.split(" ")) {
					if (word.equals(word.toUpperCase())) {
						abrev++;
					}
					else if (word.charAt(0) == word.toUpperCase().charAt(0)){
						mName++;
					}
				}	
			}
			//System.out.println(line);
			numberOfWords.add(line.split(" ").length);
			
		}
		return new TextData(article.getAuthor(), punctuation, numberOfWords, paragraphs,  text.length(), lines.length, mName, abrev);

	}
	
	private static void initMatrix() {
		System.out.println("Initialisation de la matrice de confusion");
		//if (confusionM == null){
			int sizeee = ListBuilder.authorList().size();
			confusionM = new ArrayList<List<Integer>>(sizeee);
			for(int i=0; i<sizeee; ++i){
				ArrayList<Integer> tmpl = new ArrayList<Integer>();
				for (int j = 0; j < sizeee; ++j){
					tmpl.add(j, 0);
				}
				confusionM.add(i, tmpl);
			}
		//}
	}


	public static boolean isTitle(String line) {
		return line.equals(line.toUpperCase());
	}
	
	public static void testAll(List<TextData> trainingSet, List<Article> testSet) {
		
		resetMatrix();
		int k =  10;
		int goodAnswers = 0;
		int yolorandom = 0;
		for (Article art : testSet) {
			Entry<String, Integer> res = KNearestNeighborsClassifier.getResponse(KNearestNeighborsClassifier.getNeighbors(trainingSet, parse(art), k));
			
			String realAuthor = art.getAuthor(); 
			Integer idRealAuthor = ListBuilder.authorList().get(realAuthor);
			
			String computedAuthor = res.getKey(); 
			Integer idComputedAuthor = ListBuilder.authorList().get(computedAuthor);
			
			confusionM.get(idRealAuthor).set(idComputedAuthor, confusionM.get(idRealAuthor).get(idComputedAuthor)+1);
			
			if (realAuthor.equals(computedAuthor)) {
				goodAnswers++;
				if (res.getValue() == 1){
					yolorandom++;
				}
			}
					
			//System.out.println("file " + art.getCurrent().getName()+ " " + art.getAuthor() + " "+ res.getKey() + " : " + res.getValue());
		}
		System.out.println(""+ goodAnswers + "/2500 good answers, " + (((double)goodAnswers)/2500*100) + "%, " + yolorandom + " yolorandom");
	}
	
	private static void resetMatrix() {
		System.out.println("Reset Matrice");
		for(int i=0; i<confusionM.size(); ++i){
			for (int j = 0; j < confusionM.size(); ++j){
			confusionM.get(i).set(j, 0);
			}
		}
	}

	public static String printMatrix(){
		String s = "";
		for(int i=0; i<confusionM.size(); ++i){
			for (int j = 0; j < confusionM.size(); ++j){
				s+=confusionM.get(i).get(j);
			}
			s+="\n";
		}
		return s;
	}

	public static void main(String [] args) {
		//record : k=10, 664 on train, 0 en test
		//1164/2500 good answers, 46.56%, 602 yolorandom train
		List<Article> trainSet= ListBuilder.buildList("../../Data/Reuters50_50/C50train/");
		List<TextData> trainingSet = parseAll(trainSet);
		TextData.setMinMaxFeatures(trainingSet);
		
		
		//List<Article> listfiles = ListBuilder.buildList("C:/Users/Miura Hareaki/Desktop/Travail/authorship_attribution/Authorship/Data/Reuters50_50/C50test/AaronPressman/");
		List<Article> testSet = ListBuilder.buildList("../../Data/Reuters50_50/C50test/");
		
		//d.parseAll(listfiles);
		testAll(trainingSet, trainSet);
		MulticlassStat m = MulticlassStat.makeMulticlassStat(getMatrix());
		
		System.out.println("Précision : " + m.accuracy());
		System.out.println("Recall : " + m.recall());
		System.out.println("fMesure : " + m.fMeasure());
		
		System.out.println(printMatrix());
		
		System.out.println("Validation test");
		
		testAll(trainingSet, testSet);
		m = MulticlassStat.makeMulticlassStat(getMatrix());
		
		System.out.println("Précision : " + m.accuracy());
		System.out.println("Recall : " + m.recall());
		System.out.println("fMesure : " + m.fMeasure());
		
		System.out.println(printMatrix());
		
		
		
		//call classifier
		//dance macarena
		//bring beer and stuffs
		//hookers
		//profit
	}
}