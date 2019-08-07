package Controllers;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import Models.Data;
import utilities.FileUtils;
import utilities.LocalState;
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
	
		loadTree();
		
	
		
		
		
		
	}
	public void set(){
		//fileTreeView.setRoot(new TreeItem<String>("c3"));
	}
	
	public void loadTree() {
		final File folder = LocalState.getInstance().getLastProject();
		if(folder!=null) {
		TreeItem<String> parent=new TreeItem<String>(folder.getName()) ;
		FileUtils.listFilesForTreeView(folder,parent);
		
		fileTreeView.setRoot(parent);
		}
	}

}
