package utilities;

import javafx.scene.control.TreeItem;

public class TreeUtill {

	public static void makeBranch(String childTitle,TreeItem<String> parent) {
		TreeItem<String > child=new TreeItem<String>(childTitle);
		parent.getChildren().add(child);
		parent.setExpanded(false);
		
	
		
	}
}
