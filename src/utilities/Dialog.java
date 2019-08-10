package utilities;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Dialog {
	public static File openFolder(final String title,final String path,final Stage primaryStage) throws Exception {
		DirectoryChooser chooser = new DirectoryChooser();
		chooser.setTitle("JavaFX Projects");
		File defaultDirectory = new File(path);
		chooser.setInitialDirectory(defaultDirectory);
		File selectedDirectory = chooser.showDialog(primaryStage);
		 return selectedDirectory;
	}
	
	public static Object findDialog(Class context) {
		Stage dialogStage = new Stage();
		dialogStage.initModality(Modality.WINDOW_MODAL);
		AnchorPane dialog;
		try {
			FXMLLoader loader = new FXMLLoader(context.getResource("/UI/findDialog.fxml"));
			dialog=(AnchorPane)loader.load();
			
			
			dialogStage.setScene(new Scene(dialog));
			dialogStage.show();
			
			return loader.getController();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
