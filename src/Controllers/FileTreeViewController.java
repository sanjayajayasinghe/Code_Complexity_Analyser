package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;



public class FileTreeViewController implements Initializable{

	@FXML
	public TreeView<String> fileTreeView;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		System.out.println("tree view init");
		TreeItem<String> parent=new TreeItem<String>("parent");
		parent.getChildren().add(new TreeItem<String>("c1"));
		//fileTreeView.setRoot(parent);
		fileTreeView.setRoot(parent);
		
		
	}

}
