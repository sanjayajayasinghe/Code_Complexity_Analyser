package Controllers;


import java.io.File;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import Models.FindData;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import uiListners.ClickEventListner;
import uiListners.FileSelectListner;
import utilities.Dialog;
import utilities.FileUtilities;
import utilities.LocalState;

public class FindDialogController implements Initializable {


    @FXML
    private Button find;

    @FXML
    private Button okButton;

    @FXML
    private TextField findText;

    @FXML
    private ListView<String> listView;

    private FileSelectListner fileSelectListner;

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        find.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                String keyword = findText.getText();
                LOGGER.log(Level.INFO, "find:", keyword);
                List<FindData> fileList = new ArrayList<>();
                if(LocalState.getInstance().getLastProject()!=null) {
                    fileList = FileUtilities.findFilesByWords(keyword, LocalState.getInstance().getLastProject());

                    listView.getItems().addAll(fileList.stream().map((data) -> MessageFormat.format("{0}    line{1}", data.getFile().getPath(), data.getLine())).collect(Collectors.toList()));
                }else{
                    Dialog.alert("No Project Selected");
                }
            }
        });

        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage=(Stage) okButton.getScene().getWindow();
                stage.close();

            }
        });

        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println("new value:"+newValue.split("line")[0].trim());
                File file  =new File(newValue.split("line")[0].trim());
                LocalState.getInstance().setCurrentOpenfile(file);

            }
        });

    }


    public void setFileSelectListner(FileSelectListner fileSelectListner) {
        this.fileSelectListner = fileSelectListner;
    }


}
