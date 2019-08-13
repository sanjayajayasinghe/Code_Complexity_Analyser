package Controllers;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import Models.Data;
import utilities.FileUtilities;
import utilities.LocalState;
import utilities.TreeUtill;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyEvent;
import uiListners.FileSelectListner;

public class FileTreeViewController implements Initializable {

    @FXML
    public TreeView<String> fileTreeView;

    private FileSelectListner fileSelectListner;

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

        LOGGER.log(Level.INFO, "tree view init");

        loadTree();
        fileTreeView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        fileTreeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                TreeItem<String> selectedItem = (TreeItem<String>) newValue;

                String path = TreeUtill.getItemFilepath(selectedItem, fileTreeView.getRoot(),
                        new String(LocalState.getInstance().getLastProject().getPath()));

                if (!FileUtilities.isDirectory(new File(path)))
                    fileSelectListner.afterFileSelect(new File(path));
                else
                    fileSelectListner.fileSelectFail(new Exception("not a file"));


            }

        });

        fileTreeView.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<TreeItem>() {

            @Override
            public void onChanged(Change<? extends TreeItem> c) {
                // TODO Auto-generated method stub
                ObservableList<TreeItem<String>> itemList = fileTreeView.getSelectionModel().getSelectedItems();
                if (itemList.size() > 0) {
                    List<String> fileNameList = new ArrayList<>();

                    itemList.forEach(item -> LOGGER.log(Level.INFO, "items:" + item.getValue()));
                    itemList.forEach(item -> fileNameList.add(item.getValue()));


                    TreeItem<String> selectedItem = (TreeItem<String>) itemList.get(0);


                    List<File> selectedFiles = TreeUtill.getSelectedFilesList(itemList, fileTreeView.getRoot(), LocalState.getInstance().getLastProject().getPath());

                    List<File> selectedLeafFiles = new ArrayList<>();
                    selectedFiles.forEach(file -> {
                        FileUtilities.listLeafFiles(file, selectedLeafFiles);


                    });

                    LocalState.getInstance().setCurrentSelectedFiles(selectedLeafFiles);

                }

            }
        });

        // fileTreeView.setOnKeyReleased(value);

    }

    public void set() {
        // fileTreeView.setRoot(new TreeItem<String>("c3"));
    }

    public void loadTree() {
        final File folder = LocalState.getInstance().getLastProject();
        if (folder != null) {
            TreeItem<String> root = new TreeItem<String>(folder.getName());
            TreeUtill.listFilesForTreeView(folder, root);
            fileTreeView.setRoot(root);
            LocalState.getInstance().setCurrentRoot(root);

        }
    }

    public void setFileSelectListner(FileSelectListner fileSelectListner) {
        this.fileSelectListner = fileSelectListner;
    }

}
