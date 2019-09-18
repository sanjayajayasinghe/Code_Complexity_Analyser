package Controllers;


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
import java.net.URL;
import java.text.MessageFormat;
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
                        String curentFile = LocalState.getInstance().getCurrentOpenfile() == null ? "none file"
                                : LocalState.getInstance().getCurrentOpenfile().getName();
                        File currentlyOpenedFile = LocalState.getInstance().getCurrentOpenfile();

                        resultViewController.setSampletabContent(getAnalyzedResult(currentlyOpenedFile));
                        resultViewController.setLuckytabContent("lucky:" + curentFile);
                        resultViewController.setGihantabContent("Gihan:" + curentFile);
                        resultViewController.setNishtabContent("nish:" + curentFile);
                        break;
                    case "runOnFileList":
                        LOGGER.log(Level.INFO, "run on file selected");
                        StringBuilder content = new StringBuilder("");
                        LocalState.getInstance().getCurrentSelectedFiles().forEach(file -> LOGGER.log(Level.INFO, "file:" + file.getName()));
                        LocalState.getInstance().getCurrentSelectedFiles().forEach(file -> content.append(file.getName() + "\n"));
                        resultViewController.setGihantabContent(content.toString());
                        resultViewController.setNishtabContent(getFindInheritanceClassesAnalysedResult(LocalState.getInstance().getCurrentSelectedFiles().get(0)));

                        break;

                    case "find":
                        Stage dialogStage = new Stage();
                        dialogStage.initModality(Modality.WINDOW_MODAL);
                        AnchorPane dialog;
//					try {
//						FXMLLoader loader = FXMLLoader.load(getClass().getResource("/UI/findDialog.fxml"));
//						dialog=loader.load();
//						dialogStage.setScene(new Scene(dialog));
//						dialogStage.show();
//						loader.getController();
//						
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//				}
//					
//					

                        Dialog.findDialog(getClass());
//					VBox vbox = new VBox(new Text("Hi"), new Button("Ok."));
//					vbox.setAlignment(Pos.CENTER);
//					vbox.setPadding(new Insets(15));


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
            inheritanceComplexityImpl.findInheritedClasses(folder).forEach((k, v) -> result.append(MessageFormat.format("{0} : {1}\n", k, v)));
            return result.toString();
        } else {
            return "Please open a valid file first...";
        }

    }


}
