package Controllers;

import Models.TableData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import utilities.LocalState;

import java.net.URL;
import java.util.ResourceBundle;

public class CalculationController implements Initializable {
    @FXML
    private TableView<TableData> calculationTable;

    @FXML
    private TableColumn<TableData,String> ClassName;

    @FXML
    private TableColumn<TableData,Integer> lineNo;

    @FXML
    private TableColumn<TableData,Integer> CS;

    @FXML
    private TableColumn<TableData,Integer> CNC;

    @FXML
    private TableColumn<TableData,Integer> CI;

    @FXML
    private TableColumn<TableData,Integer> TW;

    @FXML
    private TableColumn<TableData,Integer> CPS;

    @FXML
    private TableColumn<TableData,Integer> CR;

    private ObservableList<TableData> list;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ClassName.setCellValueFactory(new PropertyValueFactory<TableData,String>("ClassName"));
        lineNo.setCellValueFactory(new PropertyValueFactory<TableData,Integer>("lineNo"));

        CS.setCellValueFactory(new PropertyValueFactory<TableData,Integer>("CS"));
        CNC.setCellValueFactory(new PropertyValueFactory<TableData,Integer>("CNC"));
        CI.setCellValueFactory(new PropertyValueFactory<TableData,Integer>("CI"));
        TW.setCellValueFactory(new PropertyValueFactory<TableData,Integer>("TW"));
        CPS.setCellValueFactory(new PropertyValueFactory<TableData,Integer>("CPS"));
        CR.setCellValueFactory(new PropertyValueFactory<TableData,Integer>("CR"));






       // titleView.setText("Total:"+LocalState.getInstance().getTotalCompexity());

        list= FXCollections.observableArrayList(LocalState.getInstance().getTableData());
        calculationTable.setItems(list);
    }
}
