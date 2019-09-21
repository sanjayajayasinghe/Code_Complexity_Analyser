package Controllers;

import Models.Data;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportViewController implements Initializable {
    @FXML
    private TabPane tabPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AnchorPane anchorPane=new AnchorPane();
        HBox hBox=new HBox();
        anchorPane.getChildren().add(hBox);
        Tab tab=new Tab("new tab",anchorPane);
        tabPane.getTabs().add(tab);
        TableView<Data> tableView=new TableView<Data>();
        hBox.getChildren().add(tableView);

    }
}
