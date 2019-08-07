package utilities;

import java.io.File;

import javafx.stage.DirectoryChooser;
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

}
