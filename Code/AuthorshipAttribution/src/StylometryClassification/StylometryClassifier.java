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
	
	/** 
	 * Initialize the JGAAP API
	 */
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

	/** 
	 * Add documents to analyze : 
	 * If the document belongs to the training set --> declare the author
	 * Else if it is a part of the test set --> the author should be NULL
	 */
	public void addDocToClassifier(String path, String auteur) throws Exception{
		getJGAAP_API().addDocument(new Document(path, auteur, ""));
	}
	
	/**
	 * Process analysis on the documents added 
	 */
	public void loadDocumentsAdded() throws Exception{
		getJGAAP_API().loadDocuments();
	}
	
	/**
	 * Canonicalization : standardization or normalization. 
	 * Refer to JGAAP documentation to know all the canonicizers available.
	 * @param	action : the name of the canonicalization
	 */
	public void choiceOfCanonicizer(String action) throws Exception{
		getJGAAP_API().addCanonicizer(action);
	}
	
	/**
	 * Event culler : operates on the resulting events from processed documents .
	 * Refer to JGAAP documentation to know all the canonicizers available.
	 * @param	action : the name of the event culler
	 */
	public void choiceOfEventCuller(String action) throws Exception{
		getJGAAP_API().addEventCuller(action);
	}
	
	/**
	 * Event driver : like the lexical frequencies, tagging, punctuation ...
	 * Refer to JGAAP documentation to know all the event drivers available.
	 * @param	action : the name of the event driver
	 */
	public void choiceOfEventDriver(String action) throws Exception{
		getJGAAP_API().addEventDriver(action);
	}

	/**
	 * Analysis Methods : the method of the classifier 
	 * Refer to JGAAP documentation to know all the analysis methods available.
	 * @param	action : the name of the analysis methods
	 */
	public void choiceOfAnalysisMethod(String action) throws Exception{
		getJGAAP_API().addAnalysisDriver(action);
	}
	
	
	/**
	 * Event Driver : The features extracted from each document will depend on the event driver chosen.
	 * Refer to JGAAP documentation to know all the event drivers available.
	 * @param	action : the name of the event drivers
	 */
	public void process_Classifier() throws Exception{
		getJGAAP_API().execute();
	}
	
	/**
	 * Print results in a file named <doc_result>
	 * @param	doc_result
	 */
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
	
	
	
	public void destructor_StylometryClassifier(){
		getJGAAP_API().removeAllAnalysisDrivers();
		getJGAAP_API().removeAllCanonicizers();
		getJGAAP_API().removeAllDocuments();
		getJGAAP_API().removeAllEventCullers();
		getJGAAP_API().removeAllEventDrivers();
		
	}
	
	
}
