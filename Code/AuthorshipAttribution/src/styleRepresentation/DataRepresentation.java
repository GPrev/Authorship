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
import java.util.List;

import fileListBuilding.*;

public class DataRepresentation {
	
	
	
	public void parseAll(List<File> array) {
		 ArrayList<Article> artArray = ListBuilder.buildList("");
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
		try {
			bytes = Files.readAllBytes(Paths.get(f.getPath()));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String text = new String(bytes, StandardCharsets.UTF_8);
		
		String[] lines = text.split("(\\.|;)");
		ArrayList<Integer> numberOfWords = new ArrayList<Integer>();
		for (String line : lines) {
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
		DataRepresentation d = new DataRepresentation();
		//d.parse(array);
		//call classifier
		//dance macarena
		//bring beer and stuffs
		//hookers
		//profit
	}
}