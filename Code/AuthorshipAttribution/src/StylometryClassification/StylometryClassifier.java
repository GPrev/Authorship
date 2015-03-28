package StylometryClassification;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import FileListBuilding.Article;

import com.jgaap.backend.API;
import com.jgaap.generics.AnalysisDriver;
import com.jgaap.generics.Document;
import com.jgaap.generics.EventCuller;
import com.jgaap.generics.EventDriver;
import com.jgaap.languages.English;

import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

public class StylometryClassifier {
	private API JGAAP_API ;
	

	public StylometryClassifier(){
		if(API.getPrivateInstance() == null){
			System.out.println("Null API");
		}else{
			JGAAP_API = API.getPrivateInstance();
		}
		
	}

	public API getJGAAP_API() {
		return JGAAP_API;
	}


	public void addDocToClassifier(String path, String auteur) throws Exception{
		getJGAAP_API().addDocument(new Document(path, auteur, ""));
	}
	
	/**
	 * After adding documents, need to load them
	 */
	public void loadDocumentsAdded() throws Exception{
		getJGAAP_API().loadDocuments();
	}
	
	/**
	 * Canonicalization : standardization or normalization
	 */
	public void choiceOfCanonicizer(String actionOfCanonicizer) throws Exception{
		getJGAAP_API().addCanonicizer(actionOfCanonicizer);
	}
	
	public void choiceOfEventCuller(String action) throws Exception{
		getJGAAP_API().addEventCuller(action);
	}
	
	public void choiceOfEventDriver(String action) throws Exception{
		getJGAAP_API().addEventDriver(action);
	}
	
	public void choiceOfAnalysisMethod(String action) throws Exception{
		getJGAAP_API().addAnalysisDriver(action);
	}
	
	public void process_Classifier() throws Exception{
		getJGAAP_API().execute();
	}
	
	
	public void getPrintResult(String doc_result) throws IOException{
		OutputStream outputstream = new FileOutputStream(doc_result);
		Writer output = new OutputStreamWriter(outputstream);
		output = new BufferedWriter(output);
		
		List<Document> documents = JGAAP_API.getDocuments();
		StringBuffer buffer = new StringBuffer();
		
		for (Document document : documents) {
			String result = document.getResult();
			buffer.append(result);
		}
		
		output.write(buffer.toString());
		output.flush();
	}
	
	
}
