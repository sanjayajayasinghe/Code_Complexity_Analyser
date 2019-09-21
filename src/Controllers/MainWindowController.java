package Controllers;


import Models.ScoreObject;
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
import utilities.LocalState;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;
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

    private Integer totalComplexity=0;

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
            public void click(String action)  {
                // TODO Auto-generated method stub
                switch (action) {
                    case "runOnFile":
                        resultViewController.createCalculationtab();
                        String curentFile = LocalState.getInstance().getCurrentOpenfile() == null ? "none file"
                                : LocalState.getInstance().getCurrentOpenfile().getName();
                        File currentlyOpenedFile = LocalState.getInstance().getCurrentOpenfile();
                        if(currentlyOpenedFile!=null) {
                            CheckOverallCodeComplexityAction checkOverallCodeComplexityAction = new CheckOverallCodeComplexityAction(currentlyOpenedFile);

                            try {

                                Map<Integer, ScoreObject> scoreMap = checkOverallCodeComplexityAction.getScoreMap();
                                StringBuilder calculationcontent = new StringBuilder(String.format("for %s file\n",currentlyOpenedFile.getName()));

                                scoreMap.forEach((key, value) -> {
                                    calculationcontent.append(String.format("\tline %d->CS:%d\tCNC:%d\tCI:%d\tTW:%d\tCPS:%d\tCR:%d\n", key, value.getCS(), value.getCNC(), value.getCI(), value.getTW(), value.getCPS(), value.getCR()));
                                    resultViewController.setCalculationtabContent(calculationcontent.toString());
                                    if (value.getCR() > 0) {
                                        totalComplexity += value.getCR();
                                    } else {
                                        totalComplexity += value.getCPS();
                                    }
                                    resultViewController.setTotaltabContent(String.format("%s file\n\tTotal Complexity:%d",currentlyOpenedFile.getName(), totalComplexity));
                                });

//                            private int CS = 0;
//                            private int CNC = 0;
//                            private int CI = 0 ;
//                            private int TW = 0;
//                            private int CPS = 0;
//                            private int CR = 0;
//                            int lineNo;


                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }else {
                            //eror
                        }
                        break;
                    case "runOnFileList":
                        LOGGER.log(Level.INFO, "run on file selected");
                        if (LocalState.getInstance().getCurrentSelectedFiles() != null) {
                            StringBuilder content = new StringBuilder("");
                            LocalState.getInstance().getCurrentSelectedFiles().forEach(file -> LOGGER.log(Level.INFO, "file:" + file.getName()));
                            LocalState.getInstance().getCurrentSelectedFiles().forEach(file -> content.append(file.getName() + "\n"));
                        } else {
                            System.out.println("alert file list");
                        }
                        break;

                    case "runOnProject":
                        System.out.println("run on project");
                        break;

                    case "find":
                        Stage dialogStage = new Stage();
                        dialogStage.initModality(Modality.WINDOW_MODAL);
                        Dialog.findDialog(getClass(),"/UI/findDialog.fxml");
                        break;
                    case "generateReport":
                        Stage dialogStage2 = new Stage();
                        dialogStage2.initModality(Modality.WINDOW_MODAL);
                        Dialog.findDialog(getClass(),"/UI/reportView.fxml");
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


}
