package fileListBuilding;
// Package renomé : en java, ça commence par des minuscules :)
import java.io.File;


public class Article {
	private File current;
	private String author;
	
	public Article(File f, String s){
		current = f;
		author = s;
		
	}

	// why "current" and not "file" or "text" ?
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
