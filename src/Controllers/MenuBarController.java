package Controllers;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import uiListners.ClickEventListner;
import uiListners.FolderSelectListner;
import utilities.Dialog;
import utilities.LocalState;

public class MenuBarController implements Initializable {

    @FXML
    public MenuBar menuBarNav;

    @FXML
    private MenuItem openProject;

    @FXML
    private MenuItem runOnFile;

    @FXML
    private MenuItem runOnFileList;

    @FXML
    private MenuItem find;


    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private FolderSelectListner FolderSelectListner;
    private ClickEventListner clickEventListner;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

        System.out.println("menu bar init");

        openProject.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub

                Stage stage = (Stage) menuBarNav.getScene().getWindow();
                File file;
                try {
                    file = Dialog.openFolder("Select Project", "C:\\", stage);

                    LocalState.getInstance().setLastProject(file);
                    FolderSelectListner.afterFileSelect(file);
                } catch (Exception e) {
                    FolderSelectListner.fileSelectFail(e);
                    e.printStackTrace();
                }
                // System.out.println("file "+file.getName());
                //
            }
        });


        //set listners to the menu item
        runOnFile.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                clickEventListner.click("runOnFile");

            }
        });

        runOnFileList.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                clickEventListner.click("runOnFileList");

            }
        });

        find.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                clickEventListner.click("find");

            }
        });

    }


    public void setFolderSelectListner(FolderSelectListner folderSelectListner) {
        FolderSelectListner = folderSelectListner;
    }

    public void setClickEventListner(ClickEventListner clickEventListner) {
        this.clickEventListner = clickEventListner;
    }

    public void change() {
        openProject.setText("open works");
    }

    //

}
