package utilities;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import Models.LocalData;
import Models.TableData;
import Models.TestObjectSerialize;
import javafx.scene.control.TreeItem;

public class LocalState  implements Serializable{
    // private instance, so that it can be
    // accessed by only by getInstance() method
    private static LocalState instance;
    private File lastProject;
    private File currentOpenfile;
    private TreeItem<String> currentRoot;
    private List<TableData> tableData;
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

    public static void setInstance(LocalState instance) {
        LocalState.instance = instance;
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

    public List<TableData> getTableData() {
        return tableData;
    }

    public void clearTableData() {
        tableData=new ArrayList<>();
    }

    public void setTableData(List<TableData> tableData) {
        this.tableData = tableData;
    }

    public List<File> getCurrentSelectedFiles() {
        return currentSelectedFiles;
    }

    public void setCurrentSelectedFiles(List<File> currentSelectedFiles) {
        this.currentSelectedFiles = currentSelectedFiles;
    }

    @Override
    public String toString() {
        return "LocalState{" +
                "lastProject=" + lastProject +
                ", currentOpenfile=" + currentOpenfile +
                ", currentRoot=" + currentRoot +
                ", currentSelectedFiles=" + currentSelectedFiles +
                '}';
    }

    public void loadToFile(){
        FileOutputStream fout = null;
        ObjectOutputStream oos = null;
        //LocalData localData=new LocalData( lastProject, currentOpenfile, currentRoot, currentSelectedFiles);
        LocalData localData=new LocalData( lastProject, currentOpenfile, currentSelectedFiles);
        TestObjectSerialize testObjectSerialize=new TestObjectSerialize(null);
        try {
            //fout= File.createTempFile("MyAppName-", ".tmp");
            fout = new FileOutputStream("C:\\Users\\codeComplex\\text.ser");
            oos = new ObjectOutputStream(fout);
            oos.writeObject(localData);

            System.out.println("Done");

        } catch (Exception ex) {

            ex.printStackTrace();

        } finally {

            if (fout != null) {
                try {
                    fout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public void loadFromFile(){
        LocalData localData = null;

        FileInputStream fin = null;
        ObjectInputStream ois = null;

        try {

            fin = new FileInputStream("C:\\Users\\codeComplex\\text.ser");
            ois = new ObjectInputStream(fin);
            localData = (LocalData) ois.readObject();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

            if (fin != null) {
                try {
                    fin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        System.out.println("done data:"+localData.toString());


    }

}
