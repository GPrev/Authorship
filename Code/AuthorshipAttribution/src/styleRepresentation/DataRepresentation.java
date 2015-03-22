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

import fileListBuilding.*;

public class DataRepresentation {
	
	
	
	public void parseAll(List<Article> artArray) {
		for (Article article : artArray){
			if (article.getCurrent().exists() && article.getCurrent().canRead()) {
				parse(article.getCurrent());
			}
			else {
				System.out.println("Error with file " + article.getCurrent().getName());
			}
		}
	}
	
	public void parse(File f) {
		byte[] bytes = null;
		HashMap<Character, Integer> punctuation = new HashMap<Character, Integer>();
		punctuation.put(';', 0);
		punctuation.put('!', 0);
		punctuation.put('?', 0);
		punctuation.put(':', 0);
		punctuation.put(',', 0);
		try {
			bytes = Files.readAllBytes(Paths.get(f.getPath()));
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
			System.out.println(line);
			numberOfWords.add(line.split(" ").length);
		}
		
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
	
	public void findPuntuation(String line) {
		while (true) {
			
		}
		
	}
	
	public static void main(String [] args) {
		//call julien work
		//List<Article> listfiles = ListBuilder.buildList("../Data/Reuters50_50/C50test/AaronPressman");
		//List<Article> listfiles = ListBuilder.buildList("C:/Users/Miura Hareaki/Desktop/Travail/authorship_attribution/Authorship/Data/Reuters50_50/C50test/AaronPressman/");
		DataRepresentation d = new DataRepresentation();
		//d.parseAll(listfiles);
		File f = new File("C:/Users/Miura Hareaki/Desktop/Travail/authorship_attribution/Authorship/Data/Reuters50_50/C50test/AaronPressman/42764newsML.txt");
		d.parse(f);
		//call classifier
		//dance macarena
		//bring beer and stuffs
		//hookers
		//profit
	}
}