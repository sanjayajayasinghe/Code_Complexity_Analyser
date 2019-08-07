package Controllers;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.MenuBar;
import uiListners.FolderSelectListner;

public class MainWindowController implements Initializable{
	@FXML
	private MenuBarController menuBarNavController;
	
	@FXML
	private FileTreeViewController fileTreeViewController;
	
	@FXML
	private MenuBar menuBarNav;
	
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		menuBarNavController.setFolderSelectListner(new FolderSelectListner() {
			
			@Override
			public void fileSelectFail(Exception e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterFileSelect(File file) {
				// TODO Auto-generated method stub
				fileTreeViewController.loadTree();
				
			}
		});
		
	}

}
