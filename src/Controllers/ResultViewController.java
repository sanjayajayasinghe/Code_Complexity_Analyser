package Controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;

public class ResultViewController implements Initializable {

    @FXML
    private TabPane resultView;

    private SampleTabController sampleTabController;

    private TextArea tab1TextArea = new TextArea();
    private TextArea tab2TextArea = new TextArea();
    private TextArea tab3TextArea = new TextArea();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        System.out.println("result view start");
        FXMLLoader loader = new FXMLLoader();
        // Path to the FXML File
        //String fxmlDocPath = "/UI/sampleTab.fxml";
        try {
            //FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);

            // Create the Pane and all Details

            Tab sampleTab = loader.load(getClass().getResource("/UI/sampleTab.fxml").openStream());
            sampleTabController = (SampleTabController) loader.getController();
            resultView.getTabs().add(sampleTab);
            Tab tab1 = new Tab("tab gihan");
            tab1.setContent(tab1TextArea);

            Tab tab2 = new Tab("Inheritance");
            tab2.setContent(tab2TextArea);

            Tab tab3 = new Tab("tab lucku");
            tab3.setContent(tab3TextArea);

            resultView.getTabs().addAll(tab1, tab2, tab3);


            setSampletabContent("log init .....no file selected");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void setSampletabContent(String content) {
        sampleTabController.setText(content);
    }

    public void setGihantabContent(String content) {
        tab1TextArea.setText(content);
    }

    public void setNishtabContent(String content) {
        tab2TextArea.setText(content);
    }

    public void setLuckytabContent(String content) {
        tab3TextArea.setText(content);
    }


}
