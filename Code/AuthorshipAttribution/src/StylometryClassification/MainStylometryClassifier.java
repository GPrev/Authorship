package StylometryClassification;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.jgaap.canonicizers.NormalizeASCII;

import csv.Reader;
import FileListBuilding.Article;
import FileListBuilding.ListBuilder;
import Stats.MulticlassStat;
import Stats.Statistician;

public class MainStylometryClassifier {
	

	
	public static void main(String[] args) {
		
		if (args.length != 3){
			System.out.println("Usage : java -jar Stylometry.jar <PathTrainingSet> <PathTestSet> <nbAuthors>");
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
				classifier.addDocToClassifier(path, "");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		
		try {
			classifier.loadDocumentsAdded();
			
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the canonicizer : "
					+ "\n-Normalize ASCII "
					+ "\n-Normalize Whitespace"
					+ "\n-Null Null Canonicizer"
					+ "\n-Unify Case"
					+ "\n-Strip Punctuation"
					+ "\n-Strip Comments"
					+ "\n-Strip Numbers"
					+ "\n ... ");
			String str = sc.nextLine();
			classifier.choiceOfCanonicizer(str);
			
			System.out.println("Enter the event driver : "
					+ "\n-Coarse POS Tagger "
					+ "\n-Generic Event N-gram"
					+ "\n-Coefficient of Variation"
					+ "\n-Lexical Frequencies"
					+ "\n-Sentence length"
					+ "\n ...");
			String str1 = sc.nextLine();
			classifier.choiceOfEventDriver(str1);
			/*
			System.out.println("Enter the event culler : "
					+ "\n-Least Common Events "
					+ "\n-Most Common Events"
					+ "\n-Coefficient of Variation"
					+ "\n-X-treme Culler"
					+ "\n ...");
			String str2 = sc.nextLine();		
			classifier.choiceOfEventCuller(str2);
			*/
			
			System.out.println("Enter the analysis method : "
					+ "\n-LDA "
					+ "\n-SVM"
					+ "\n-Coefficient of Variation"
					+ "\n-WEKA J48 Decision Tree Classifier"
					+ "\n ...");
			String str3 = sc.nextLine();
			classifier.choiceOfAnalysisMethod(str3);
			
			classifier.process_Classifier();
			classifier.getPrintResult("Result.txt");
			
			System.out.println("Analysis succeed.");
			classifier.destructor_StylometryClassifier();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		//Constructor of the matrix of confusion
		try {
			
			Process p = Runtime.getRuntime().exec("perl analyse_result.pl -o="+args[2]);
			BufferedReader in = new BufferedReader(
			                                new InputStreamReader(p.getInputStream()));
			String line = null;
				while ((line = in.readLine()) != null) {
				                System.out.println(line);
				}
				System.out.println("Confusion Matrix generated.");
		} catch (IOException e) {e.printStackTrace();}
		
		
		//Generated the results
		Statistician stats = MulticlassStat.makeMulticlassStat("C:/Users/Rasata Liantsoa/workspace/PartOfSpeech/AuthorshipAttribution/ConfusionMatrix.csv") ;
		if(stats == null){
			System.out.println("Make sure that you have perl installed and execute analyse_result.pl");
		}else{
			try {
				OutputStream outputstream = new FileOutputStream("Statistiques.txt");
				Writer output = new OutputStreamWriter(outputstream);
				output = new BufferedWriter(output);
				try {
					output.write("Statistics results :\n");
					output.write("\naccuracy : "+stats.accuracy());
					output.write("\nprecision : "+stats.precision());
					output.write("\nrecall : "+stats.recall());
					output.write("\nspecificity : "+stats.specificity());
					output.write("\nsilence : "+stats.silence());
					output.write("\nfallout : "+stats.fallout());
					output.write("\nfalseAlarm : "+stats.falseAlarm());
					output.write("\nfalseReject : "+stats.falseReject());
					output.write("\nerrorRate : "+stats.errorRate());
					output.write("\nfMeasure : "+stats.fMeasure());
					
					output.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		

		
	}
		
	

}
