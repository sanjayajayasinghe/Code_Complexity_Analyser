package Controllers;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import Models.Data;
import Utill.FileUtils;
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
		//TreeItem<String> parent=Data.item;
		//parent.getChildren().add(new TreeItem<String>("c1"));
		//fileTreeView.setRoot(parent);
		final File folder = new File("C:\\Users\\sanjaya jayasinghe\\Desktop\\ditributedSystem\\it17012966\\Code_Complexity_Analyser");
		TreeItem<String> parent=Data.item;
		FileUtils.listFilesForTreeView(folder,parent);
		
		fileTreeView.setRoot(parent);
		
		
		
		
	}
	public void set(){
		//fileTreeView.setRoot(new TreeItem<String>("c3"));
	}

}
