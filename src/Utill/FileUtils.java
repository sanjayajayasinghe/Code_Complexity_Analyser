package Utill;

import java.io.File;

import javafx.scene.control.TreeItem;

public class FileUtils {
	public static void listFilesForFolder(final File folder) {
	    for (final File fileEntry : folder.listFiles()) {
	    	//System.out.println(fileEntry.getParent());
            System.out.println(fileEntry.getPath());
	    	if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {
	        	
	        }
	    }
	}
	
	public static void  listFilesForTreeView(final File folder,TreeItem<String> root) {
	    for (final File fileEntry : folder.listFiles()) {
	    	//System.out.println(fileEntry.getParent());
	    	TreeUtill.makeBranch(fileEntry.getName(), root);
            //System.out.println(fileEntry.getPath());
	    	if (fileEntry.isDirectory()) {
	    		TreeItem<String> children=new TreeItem<String>(fileEntry.getName());
	    		listFilesForTreeView(fileEntry,children);
	    		root.getChildren().add(children);
	        } else {
	        	
	        }
	    }
	}
	
	
	
	
	public static void main(String args[]) {
		final File folder = new File("C:\\Users\\sanjaya jayasinghe\\Desktop\\ditributedSystem\\it17012966\\Code_Complexity_Analyser");
		listFilesForFolder(folder);
	}

}
