package utilities;

import java.io.File;
import java.util.List;

import javafx.scene.control.TreeItem;

public class LocalState {
    // private instance, so that it can be
    // accessed by only by getInstance() method
    private static LocalState instance;
    private File lastProject;
    private File currentOpenfile;
    private TreeItem<String> currentRoot;
    private List<File> currentSelectedFiles;

    public TreeItem<String> getCurrentRoot() {
        return currentRoot;
    }

    public void setCurrentRoot(TreeItem<String> currentRoot) {
        this.currentRoot = currentRoot;
    }

    private LocalState() {
        // private constructor
    }

    public static LocalState getInstance() {
        if (instance == null) {
            // synchronized block to remove overhead
            synchronized (LocalState.class) {
                if (instance == null) {
                    // if instance is null, initialize
                    instance = new LocalState();
                }

            }
        }
        return instance;
    }


    public File getLastProject() {
        return lastProject;
    }


    public void setLastProject(File lastProject) {
        this.lastProject = lastProject;
    }


    public File getCurrentOpenfile() {
        return currentOpenfile;
    }


    public void setCurrentOpenfile(File currentOpenfile) {
        this.currentOpenfile = currentOpenfile;
    }

    public List<File> getCurrentSelectedFiles() {
        return currentSelectedFiles;
    }

    public void setCurrentSelectedFiles(List<File> currentSelectedFiles) {
        this.currentSelectedFiles = currentSelectedFiles;
    }


}
