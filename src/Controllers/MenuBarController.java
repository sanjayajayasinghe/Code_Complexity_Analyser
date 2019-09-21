package Controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
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
import org.eclipse.jgit.api.Git;
import uiListners.ClickEventListner;
import uiListners.FolderSelectListner;
import utilities.Dialog;
import utilities.LocalState;

import javax.swing.*;

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
    private MenuItem runOnProject;

    @FXML
    private MenuItem find;

    @FXML
    private MenuItem cloneFromUrl;

    @FXML
    private MenuItem about;

    @FXML
    private  MenuItem generateReport;

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private FolderSelectListner FolderSelectListner;
    private ClickEventListner clickEventListner;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        cloneFromUrl.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) menuBarNav.getScene().getWindow();
                File file;
                try {
                    String repoUrl = JOptionPane.showInputDialog("Repository URL : ");
                    file = Dialog.openFolder("Select location to clone", "C:\\", stage);
                    LOGGER.log(Level.INFO, "Cloning from URL");
                    Git.cloneRepository()
                            .setURI(repoUrl)
                            .setDirectory(file)
                            .call();
                    LOGGER.log(Level.INFO, "Cloning Completed");
                    LocalState.getInstance().setLastProject(file);
                    FolderSelectListner.afterFileSelect(file);
                } catch (Exception e) {
                    FolderSelectListner.fileSelectFail(e);
                    e.printStackTrace();
                }
            }
        });

        openProject.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
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

            }
        });


        //set listners to the menu item
        runOnFile.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    clickEventListner.click("runOnFile");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        runOnProject.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    clickEventListner.click("runOnProject");
                    File project=LocalState.getInstance().getLastProject();
                    System.out.println(project.getName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        runOnFileList.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    clickEventListner.click("runOnFileList");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        find.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    clickEventListner.click("find");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });


        about.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                JOptionPane.showMessageDialog(null, "Developed By : \n" +
                        " \n Gihan Saranga \n Sanjaya Jayasinghe \n Nishitha De Silva \n Lakshitha Vithanage","Code Complexity Analyzer V1.0.0",JOptionPane.INFORMATION_MESSAGE);

            }
        });

        generateReport.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    clickEventListner.click("generateReport");
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
