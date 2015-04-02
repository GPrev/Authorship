package csv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reader {
	public static List<List<Integer>> readCSV(String csvFile)
	{
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ";";

		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				List<Integer> l = new ArrayList<Integer>();
				// use comma as separator
				String[] values = line.split(cvsSplitBy);
				for(int i = 0; i < values.length; ++i)
				{
					if(!values[i].equals("") && !values[i].equals(" "))
						l.add(Integer.parseInt(values[i]));
				}
				res.add(l);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return res;
	}
	
	public static void main(String[] args)
	{
		List<List<Integer>> l = readCSV(args[0]);
		System.out.println(l.toString());
	}
}
