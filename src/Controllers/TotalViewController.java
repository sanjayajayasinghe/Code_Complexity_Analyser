package Controllers;

import Models.ComplexityForClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import utilities.LocalState;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class TotalViewController implements Initializable {

    @FXML
    private Text titleView;

    @FXML
    private TableView<ComplexityForClass> classTable;

    @FXML
    private TableColumn<ComplexityForClass,String>  className;

    @FXML
    private TableColumn<ComplexityForClass,Integer>  complexity;

    private ObservableList<ComplexityForClass> list;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        titleView.setText("Total:"+LocalState.getInstance().getTotalCompexity());
        className.setCellValueFactory(new PropertyValueFactory<ComplexityForClass,String>("className"));
        complexity.setCellValueFactory(new PropertyValueFactory<ComplexityForClass,Integer>("complexity"));
        list=FXCollections.observableArrayList(LocalState.getInstance().getComplexityForClasses());
        classTable.setItems(list);

    }
}
