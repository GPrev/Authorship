package styleRepresentation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class DataRepresentation {
	
	
	
	public void parseAll(List<File> array) {
		for (File f : array){
			if (f.exists() && f.canRead()) {
				parse(f);
			}
			else {
				System.out.println("Error with file " + f.getName());
			}
		}
	}
	
	public void parse(File f) {
		BufferedReader br = null;
		 
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
		}
	}
	public void findPuntuation(String line) {
		
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