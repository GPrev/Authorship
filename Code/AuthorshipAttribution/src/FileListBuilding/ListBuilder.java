package FileListBuilding;
import java.io.File;
import java.util.ArrayList;

/**
 * 
 */

/**
 * @author jbouvet
 *
 */
public class ListBuilder {
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length != 1){
			System.out.println("Usage : java -jar BuildList.jar ARG:Folder of Train Test");
			throw new IllegalArgumentException();
		}
		
		System.out.println(buildList(args[0]));
		
	}
	
	public static ArrayList<Article> buildList(String path){
			ArrayList<Article> returnList = new ArrayList<Article>();
			
			File mainFile = new File(path);
			String author;
			for (File f : mainFile.listFiles()) {
				
				author = f.getName();
											
				for (File ff : f.listFiles()) {
					returnList.add(new Article(ff, author));
				}
								
			}
			return returnList;
		
	}

}
