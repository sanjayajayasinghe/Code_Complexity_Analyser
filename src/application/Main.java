package application;

import Controllers.FileTreeViewController;
import Models.Data;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.BorderPane;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;


public class Main extends Application {
    private static final String RELATIVE_DIR_PATH=new File("").getAbsolutePath();
    private static final String IMAGE = "\\src\\application\\chart.png";

    @Override
    public void start(Stage primaryStage) {
        try {

            File initialFile = new File(RELATIVE_DIR_PATH.concat(IMAGE));
            InputStream targetStream = new FileInputStream(initialFile);

            Image image = new Image(targetStream);

            primaryStage.getIcons().add(image);
            BorderPane root = FXMLLoader.load(getClass().getResource("/UI/main.fxml"));
            Scene scene = new Scene(root, 400, 400);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
