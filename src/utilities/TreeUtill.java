package utilities;



import java.util.List;

import javafx.scene.control.TreeItem;

public class TreeUtill {

	public static void makeBranch(String childTitle,TreeItem<String> parent) {
		TreeItem<String > child=new TreeItem<String>(childTitle);
		parent.getChildren().add(child);
		parent.setExpanded(false);
		
	
		
	}
	
	public static String getItemFilepath(TreeItem<String> leaf,TreeItem<String> root,String path) throws NullPointerException{
		
		
		if(root.equals(leaf)) {
			return path;
		}else {
			return getItemFilepath(leaf.getParent(),root,path)+"\\"+leaf.getValue();
		}
		
	}
}
