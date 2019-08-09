package Controllers;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import coreFunctions.ComplexityDueToSize;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.MenuBar;
import languageCheckers.JavaSyntaxChecker;
import uiListners.ClickEventListner;
import uiListners.FileSelectListner;
import uiListners.FolderSelectListner;
import utilities.LocalState;

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
					LOGGER.log(Level.INFO,"run on file selected");
					StringBuilder content=new StringBuilder("");
					LocalState.getInstance().getCurrentSelectedFiles().forEach(file->LOGGER.log(Level.INFO,"file:"+file.getName()));
					LocalState.getInstance().getCurrentSelectedFiles().forEach(file->content.append(file.getName()+"\n"));
					resultViewController.setGihantabContent(content.toString());
					break;

				}

			}
		});
	}

	private String getAnalyzedResult(File openedFile) {

		if (openedFile != null) {
			String syntaxErors = JavaSyntaxChecker.compileJava(openedFile.getAbsolutePath());
			ComplexityDueToSize test = new ComplexityDueToSize(openedFile);
			String Cs = test.getComplexityAnalysisResult();
			return syntaxErors + Cs;
		} else {
			return "Please open a valid file first...";
		}

	}

}
