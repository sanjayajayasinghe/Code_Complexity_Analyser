package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

public class SampleTabController implements Initializable {
    @FXML
    private TextArea logView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void setText(String text) {
        logView.setText(text);
    }


}
