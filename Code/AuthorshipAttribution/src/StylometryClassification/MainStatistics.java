package StylometryClassification;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import Stats.MulticlassStat;
import Stats.Statistician;

public class MainStatistics {
	public static void main(String[] args) {
		
		if (args.length != 1){
			System.out.println("Usage : java -jar MainStatistics.jar <path of file of the confusion matrix> ");
			throw new IllegalArgumentException();
		}
		
		Statistician stats = MulticlassStat.makeMulticlassStat(args[1]) ;
		if(stats == null){
			System.out.println("Make sure of the file path");
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
