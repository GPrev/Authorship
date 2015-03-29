package StylometryClassification;

import java.io.File;
import java.util.ArrayList;

import com.jgaap.canonicizers.NormalizeASCII;

import FileListBuilding.Article;
import FileListBuilding.ListBuilder;

public class MainStylometryClassifier {
	/*
	Need to add jgaap as an external library
	*/

	
	public static void main(String[] args) {
		if (args.length != 2){
			System.out.println("Usage : java -jar BuildList.jar ARG:Folder of sample train");
			throw new IllegalArgumentException();
		}
		
		ArrayList<Article> list_article = ListBuilder.buildList(args[0]);
		StylometryClassifier classifier = new StylometryClassifier();
		
		
		//Adding KnownAuthor Article 
		for(Article a: list_article){
			String path = args[0]+"/"+a.getAuthor()+"/"+a.getCurrent().getName();
			try {
				classifier.addDocToClassifier(path, a.getAuthor());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		//Adding UnKnownAuthor Article 
		ArrayList<Article> list_article_unknown = ListBuilder.buildList(args[1]);
		for(Article a: list_article_unknown){
			String path = args[1]+"/"+a.getAuthor()+"/"+a.getCurrent().getName();
			try {
				//Adding documents without author --> ""
				classifier.addDocToClassifier(path, "");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/*
		 * Testing statistical methods to the data
		 * Representation of the Data : 
		 * Canonicalization : Normalize ASCII
		 * EventCuller : Most common events (Sort out the N most common events (by average frequency) across all event sets)
		 * EventDriver : FreqEventDriver
		 * Analysis Method Decision Tree Classifier from WEKA
		 */
		
 
		try {
			classifier.loadDocumentsAdded();
			classifier.choiceOfCanonicizer("Normalize ASCII");
			classifier.choiceOfEventCuller("Most Common Events");
			classifier.choiceOfEventDriver("Lexical Frequencies");
			classifier.choiceOfAnalysisMethod("WEKA J48 Decision Tree Classifier");
			classifier.process_Classifier();
			classifier.getPrintResult("Result_Decision_Tree.txt");
			System.out.println("Analysis successed");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
	}
		
	

}
