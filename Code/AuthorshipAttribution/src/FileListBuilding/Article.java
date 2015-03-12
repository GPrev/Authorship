package FileListBuilding;
import java.io.File;


public class Article {
	private File current;
	private String author;
	
	public Article(File f, String s){
		current = f;
		author = s;
		
	}

	public File getCurrent() {
		return current;
	}

	public String getAuthor() {
		return author;
	}
	
	public String toString(){
		return "Fichier : "+current.getName()+" Auteur : "+author;
	}
	
}
