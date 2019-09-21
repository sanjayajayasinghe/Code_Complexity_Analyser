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

    private Tab calculationTab;
    private Tab totalTab;


    private TextArea tab1TextArea = new TextArea();

    private TextArea tab3TextArea = new TextArea();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        System.out.println("result view start");
        FXMLLoader loader = new FXMLLoader();
        // Path to the FXML File
        //String fxmlDocPath = "/UI/sampleTab.fxml";

            //FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);

            // Create the Pane and all Details

//            Tab sampleTab = loader.load(getClass().getResource("/UI/sampleTab.fxml").openStream());
//            sampleTabController = (SampleTabController) loader.getController();
            //resultView.getTabs().add(sampleTab);
            totalTab = new Tab("Total");
            totalTab.setContent(tab1TextArea);



            calculationTab = new Tab("Calcualtion");
            calculationTab.setContent(tab3TextArea);

            resultView.getTabs().add(totalTab);






    }


    public void createCalculationtab(){
        resultView.getTabs().add(calculationTab) ;
    }

    public void setTotaltabContent(String content) {
        tab1TextArea.setText(content);
    }



    public void setCalculationtabContent(String content) {
        tab3TextArea.setText(content);
    }


}
