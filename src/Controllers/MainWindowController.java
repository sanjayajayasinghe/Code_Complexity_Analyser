package Controllers;


import Models.ScoreObject;
import Models.TableData;
import actions.CheckOverallCodeComplexityAction;
import com.sun.javafx.binding.StringFormatter;
import coreFunctions.ComplexityDueToSize;
import coreFunctions.InheritanceComplexityImpl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import languageCheckers.JavaSyntaxChecker;
import uiListners.ClickEventListner;
import uiListners.FileSelectListner;
import uiListners.FolderSelectListner;
import utilities.Dialog;
import utilities.FileUtilities;
import utilities.LocalState;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainWindowController implements Initializable {
    @FXML
    private MenuBarController menuBarNavController;

    @FXML
    private FileTreeViewController fileTreeViewController;

    @FXML
    private MenuBar menuBarNav;

    @FXML
    private CodeViewController codeViewController;

    @FXML
    private ResultViewController resultViewController;

    private Integer finalTotalComplexity = 0;

    private StringBuilder totalTab;

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        menuBarNavController.setFolderSelectListner(new FolderSelectListner() {

            @Override
            public void fileSelectFail(Exception e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterFileSelect(File file) {
                // TODO Auto-generated method stub
                fileTreeViewController.loadTree();

            }
        });

        fileTreeViewController.setFileSelectListner(new FileSelectListner() {

            @Override
            public void fileSelectFail(Exception e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterFileSelect(File file) {
                // TODO Auto-generated method stub
                codeViewController.setFileToView(file);
                LocalState.getInstance().setCurrentOpenfile(file);
                LocalState.getInstance().loadToFile();
                LocalState.getInstance().loadFromFile();
            }
        });

        menuBarNavController.setClickEventListner(new ClickEventListner() {

            @Override
            public void click(String action) {
                // TODO Auto-generated method stub
                switch (action) {
                    case "runOnFile":
                        totalTab = new StringBuilder();
                        resultViewController.createCalculationtab();
                        String curentFile = LocalState.getInstance().getCurrentOpenfile() == null ? "none file"
                                : LocalState.getInstance().getCurrentOpenfile().getName();
                        File currentlyOpenedFile = LocalState.getInstance().getCurrentOpenfile();
                        if (currentlyOpenedFile != null) {
                            int complexity = getCalcualationForFile(currentlyOpenedFile, new StringBuilder());
                            //setTotalComplexity(currentlyOpenedFile, ));
                            appendToTotal(currentlyOpenedFile.getName(), complexity, totalTab);
                            setTotalComplexity(currentlyOpenedFile, complexity, totalTab);

                        } else {
                            //eror
                        }
                        break;
                    case "runOnFileList":
                        LOGGER.log(Level.INFO, "run on file selected");
                        List<File> fileList = LocalState.getInstance().getCurrentSelectedFiles();
                        if (fileList != null) {
                            runOnFileList(fileList);
                        } else {
                            System.out.println("alert file list");
                        }
                        break;

                    case "runOnProject":
                        List<File> projectFiles = new ArrayList<>();
                        FileUtilities.listLeafFiles(LocalState.getInstance().getLastProject(), projectFiles);
                        if (LocalState.getInstance().getLastProject() != null) {
                            runOnFileList(projectFiles);
                        } else {
                            System.out.println("alert file list");
                        }
                        System.out.println("run on project");
                        break;

                    case "find":
                        Stage dialogStage = new Stage();
                        dialogStage.initModality(Modality.WINDOW_MODAL);
                        Dialog.findDialog(getClass(), "/UI/findDialog.fxml");
                        break;
                    case "generateReport":
                        Stage dialogStage2 = new Stage();
                        dialogStage2.initModality(Modality.WINDOW_MODAL);
                        Dialog.findDialog(getClass(), "/UI/reportView.fxml");
                        break;
                }

            }
        });
    }

    private String getAnalyzedResult(File openedFile) {

        if (openedFile != null) {
            String syntaxErors = null;
            try {
                syntaxErors = JavaSyntaxChecker.compileJava(openedFile.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
            }
            ComplexityDueToSize test = new ComplexityDueToSize(openedFile);
            String Cs = test.getComplexityAnalysisResult();
            return syntaxErors + Cs;
        } else {
            return "Please open a valid file first...";
        }

    }

    private String getFindInheritanceClassesAnalysedResult(File folder) {

        if (folder != null) {
            InheritanceComplexityImpl inheritanceComplexityImpl = new InheritanceComplexityImpl();
            StringBuilder result = new StringBuilder("");
            inheritanceComplexityImpl.findInheritedClassesForFileList(folder).forEach((k, v) -> result.append(MessageFormat.format("{0} : {1}\n", k, v)));
            return result.toString();
        } else {
            return "Please open a valid file first...";
        }

    }

    public Integer getCalcualationForFile(File currentlyOpenedFile, StringBuilder calculationcontent) {
        Integer totalComplexity = 0;
        try {
            CheckOverallCodeComplexityAction checkOverallCodeComplexityAction = new CheckOverallCodeComplexityAction(currentlyOpenedFile);

            Map<Integer, ScoreObject> scoreMap = checkOverallCodeComplexityAction.getScoreMap();
            calculationcontent.append(String.format("for %s file\n", currentlyOpenedFile.getName()));

            for (Map.Entry<Integer, ScoreObject> entry : scoreMap.entrySet()) {
                Integer key = entry.getKey();
                ScoreObject value = entry.getValue();
                calculationcontent.append(String.format("\tline\t%d\t->\tCS:\t%d\tCNC:\t%d\tCI:\t%d\tTW:\t%d\tCPS:\t%d\tCR:\t%d\n", key, value.getCS(), value.getCNC(), value.getCI(), value.getTW(), value.getCPS(), value.getCR()));
                TableData tableData=new TableData(currentlyOpenedFile.getName(),key,value.getCS(),value.getCNC(),value.getCI(),value.getTW(),value.getCPS(),value.getCR());
                resultViewController.setCalculationtabContent(calculationcontent.toString());
                if (value.getCR() > 0) {
                    totalComplexity += value.getCR();
                } else {
                    totalComplexity += value.getCPS();
                }
            }


        } catch (IOException io) {
            totalComplexity = 0;
            io.printStackTrace();
        } finally {
            return totalComplexity;
        }


    }

    public void setTotalComplexity(File ProjectOrFile, Integer totalComplexity, StringBuilder oneFileText) {
        StringBuilder lastAppend = new StringBuilder();
        lastAppend.append(String.format("%s \n\tTotal Complexity:%d\n", ProjectOrFile == null ? "Selected File List" : ProjectOrFile.getName(), totalComplexity));
        lastAppend.append(oneFileText);
        resultViewController.setTotaltabContent(lastAppend.toString());

    }

    public void appendToTotal(String fileName, Integer complexity, StringBuilder totalTabText) {
        totalTabText.append(String.format("\t\t%s:\t%d\n", fileName, complexity));
    }


    public void runOnFileList(List<File> fileList) {
        totalTab = new StringBuilder();
        resultViewController.createCalculationtab();
        finalTotalComplexity = 0;
        StringBuilder content = new StringBuilder("");
        fileList.forEach(file -> LOGGER.log(Level.INFO, "file:" + file.getName()));
        fileList.stream().filter(file -> {
                    if (file.getName().endsWith(".java") || file.getName().endsWith(".cpp")) return true;
                    else return false;
                }

        ).forEach(file -> {
            System.out.println(file.getName());
            int complexity = getCalcualationForFile(file, content);
            appendToTotal(file.getName(), complexity, totalTab);
            finalTotalComplexity += complexity;
        });
        setTotalComplexity(null, finalTotalComplexity, totalTab);

    }
}
