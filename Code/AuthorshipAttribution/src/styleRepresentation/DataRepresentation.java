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

import styleClassifier.KNearestNeighborsClassifier;
import fileListBuilding.*;

public class DataRepresentation {
	
	
	
	public static List<TextData> parseAll(List<Article> artArray) {
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
		//return lines.length;
		/*BufferedReader br = null;
		 
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader(f));
			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
				
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}*/
	}
	
	public static boolean isTitle(String line) {
		return line.equals(line.toUpperCase());
	}
	
	public static void testAll(List<TextData> trainingSet, List<Article> testSet) {
		int k =  10;
		int goodAnswers = 0;
		int yolorandom = 0;
		for (Article art : testSet) {
			Entry<String, Integer> res = KNearestNeighborsClassifier.getResponse(KNearestNeighborsClassifier.getNeighbors(trainingSet, parse(art), k));
			if (art.getAuthor().equals(res.getKey())) {
				goodAnswers++;
				if (res.getValue() == 1){
					yolorandom++;
				}
			}
			System.out.println("file " + art.getCurrent().getName()+ " " + art.getAuthor() + " "+ res.getKey() + " : " + res.getValue());
		}
		System.out.println(""+ goodAnswers + "/2500 good answers, " + (((double)goodAnswers)/2500*100) + "%, " + yolorandom + " yolorandom");
	}
	
	public static void main(String [] args) {
		//record : k=10, 664 on train, 0 en test
		//1164/2500 good answers, 46.56%, 602 yolorandom train
		List<Article> listfiles = ListBuilder.buildList("../../Data/Reuters50_50/C50train");
		//List<Article> listfiles = ListBuilder.buildList("C:/Users/Miura Hareaki/Desktop/Travail/authorship_attribution/Authorship/Data/Reuters50_50/C50test/AaronPressman/");
		List<TextData> trainingSet = parseAll(listfiles);
		TextData.setMinMaxFeatures(trainingSet);
		listfiles = ListBuilder.buildList("../../Data/Reuters50_50/C50test");
		//d.parseAll(listfiles);
		testAll(trainingSet, listfiles);
		//call classifier
		//dance macarena
		//bring beer and stuffs
		//hookers
		//profit
	}
}