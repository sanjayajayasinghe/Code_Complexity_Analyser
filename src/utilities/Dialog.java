package utilities;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Dialog {
    public static File openFolder(final String title, final String path, final Stage primaryStage) throws Exception {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("JavaFX Projects");
        File defaultDirectory = new File(path);
        chooser.setInitialDirectory(defaultDirectory);
        File selectedDirectory = chooser.showDialog(primaryStage);
        return selectedDirectory;
    }

    public static Object findDialog(Class context,String uiPath) {
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        AnchorPane dialog;
        try {
            FXMLLoader loader = new FXMLLoader(context.getResource(uiPath));
            dialog = (AnchorPane) loader.load();


            dialogStage.setScene(new Scene(dialog));
            dialogStage.show();

            return loader.getController();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public static void alert(String Content){

        Alert alert = new Alert(Alert.AlertType.WARNING, Content, ButtonType.OK);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.show();
    }

}
