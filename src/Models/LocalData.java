package Models;

import javafx.scene.control.TreeItem;
import utilities.LocalState;

import java.io.File;
import java.io.Serializable;
import java.util.List;

public class LocalData implements Serializable {

     File lastProject;
     File currentOpenfile;
     //TreeItem<String> currentRoot;
     Object currentSelectedFiles;


    public LocalData(File lastProject, File currentOpenfile, List<File> currentSelectedFiles) {
        this.lastProject = lastProject;
        this.currentOpenfile = currentOpenfile;
        //this.currentRoot = currentRoot;
        this.currentSelectedFiles = currentSelectedFiles;
    }


    @Override
    public String toString() {
        return "LocalData{" +
                "lastProject=" + lastProject +
                ", currentOpenfile=" + currentOpenfile +
                ", currentSelectedFiles=" + currentSelectedFiles +
                '}';
    }
}
