package Controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;

public class ResultViewController implements Initializable{

	@FXML
	private TabPane resultView;
	
	private SampleTabController sampleTabController;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		System.out.println("result view start");
		FXMLLoader loader = new FXMLLoader();
        // Path to the FXML File
        //String fxmlDocPath = "/UI/sampleTab.fxml";
        try {
        //FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);
 
        // Create the Pane and all Details
        
			Tab sampleTab =  loader.load(getClass().getResource("/UI/sampleTab.fxml").openStream());
			sampleTabController=(SampleTabController)loader.getController();
			resultView.getTabs().add(sampleTab);
			setSampletabContent("log init .....no file selected");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void setSampletabContent(String content) {
		sampleTabController.setText(content);
	}
	
	

}
