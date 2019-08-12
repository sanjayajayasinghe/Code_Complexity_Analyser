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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import uiListners.ClickEventListner;
import uiListners.FileSelectListner;
import utilities.FileUtils;
import utilities.LocalState;

public class FindDialogController implements Initializable {


    @FXML
    private Button find;

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
                fileList = FileUtils.findFilesByWords(keyword, LocalState.getInstance().getLastProject());

                listView.getItems().addAll(fileList.stream().map((data) -> MessageFormat.format("{0}    line{1}", data.getFile().getName(), data.getLine())).collect(Collectors.toList()));

            }
        });
    }


    public void setFileSelectListner(FileSelectListner fileSelectListner) {
        this.fileSelectListner = fileSelectListner;
    }


}
