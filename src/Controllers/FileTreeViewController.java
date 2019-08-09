package Controllers;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import Models.Data;
import utilities.FileUtils;
import utilities.LocalState;
import utilities.TreeUtill;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import uiListners.FileSelectListner;



public class FileTreeViewController implements Initializable{

	@FXML
	public TreeView<String> fileTreeView;
	
	private FileSelectListner fileSelectListner;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		System.out.println("tree view init");
	
		loadTree();
		
		fileTreeView.getSelectionModel().selectedItemProperty().addListener( new ChangeListener() {
		        @Override
		        public void changed(ObservableValue observable, Object oldValue,
		                Object newValue) {

		            TreeItem<String> selectedItem = (TreeItem<String>) newValue;
		       
		            String path=TreeUtill.getItemFilepath(selectedItem, fileTreeView.getRoot(),new  String(LocalState.getInstance().getLastProject().getPath()));
		            
		            if(!FileUtils.isDirectory(new File(path)))
		            	fileSelectListner.afterFileSelect(new File(path));
		            else 
		            	fileSelectListner.fileSelectFail(new Exception("not a file"));
						
					
		            //System.out.println("Selected Text : "+ selectedItem.getParent().getParent().getValue()+" "+ selectedItem.getParent().getValue()+" "+selectedItem.getValue());
		            // do what ever you want 
		        }

		      });
		
	
		
		
		
		
	}
	public void set(){
		//fileTreeView.setRoot(new TreeItem<String>("c3"));
	}
	
	public void loadTree() {
		final File folder = LocalState.getInstance().getLastProject();
		if(folder!=null) {
		TreeItem<String> root=new TreeItem<String>(folder.getName()) ;
		FileUtils.listFilesForTreeView(folder,root);
		fileTreeView.setRoot(root);
		LocalState.getInstance().setCurrentRoot(root);
		
		}
	}
	
	public void setFileSelectListner(FileSelectListner fileSelectListner) {
		this.fileSelectListner = fileSelectListner;
	}

}
