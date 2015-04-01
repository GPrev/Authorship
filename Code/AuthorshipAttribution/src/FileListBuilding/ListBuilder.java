package fileListBuilding;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 */

/**
 * @author jbouvet
 *
 */
public class ListBuilder {
	private static ArrayList<Article> articles = null;
	private static HashMap<String, Integer> authors = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length != 1){
			System.out.println("Usage : java -jar BuildList.jar ARG:Folder of Train Test");
			throw new IllegalArgumentException();
		}
		
		System.out.println(buildList(args[0]));
		System.out.println(authorList().toString());
		
	}
	
	//"../Data/Reuters50_50/C50test/AaronPressman"
	//cet argument génère une nullpointerexception ... Try catch ?
	public static ArrayList<Article> buildList(String path){
		if (articles == null){
			ArrayList<Article> returnList = new ArrayList<Article>();
			if (authors == null)
				authors = new HashMap<String, Integer>();
			File mainFile = new File(path);
			String author;
			int i = 0;
			for (File f : mainFile.listFiles()) {
				
				author = f.getName();
				authors.put(author, i);
				
				for (File ff : f.listFiles()) {
					returnList.add(new Article(ff, author));
				}
				++i;				
			}
			articles = returnList;
		}
		else{
			ArrayList<Article> returnList = new ArrayList<Article>();
			File mainFile = new File(path);
			String author;
			int i = 0;
			for (File f : mainFile.listFiles()) {
				
				author = f.getName();
								
				for (File ff : f.listFiles()) {
					returnList.add(new Article(ff, author));
				}
				++i;				
			}
			articles = returnList;
		}
			return articles;
		
	}
	
	public static HashMap<String, Integer> authorList(){
		return authors;
	}

}
