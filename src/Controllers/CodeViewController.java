package Controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import utilities.FileUtils;

public class CodeViewController implements Initializable{
	
	@FXML
	TextArea codeView;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//try (BufferedReader reader = new BufferedReader(new FileReader(new File("C:\\Users\\sanjaya jayasinghe\\Desktop\\ditributedSystem\\it17012966\\javaFX\\src\\javaFX\\HelloWorld.java")))) {
			
		//codeView.setText();
		System.out.println("code view start");
	}
	
	public void setText(String text) {
		codeView.setText(text);
	}
	
	public void setFileToView(File file) {
		setText(FileUtils.filesToString(file));
		
	}

}
