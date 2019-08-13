package Controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import utilities.FileUtilities;

public class CodeViewController implements Initializable {

    @FXML
    WebView codeView;

    private WebEngine webEngine;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        webEngine = codeView.getEngine();
        //try (BufferedReader reader = new BufferedReader(new FileReader(new File("C:\\Users\\sanjaya jayasinghe\\Desktop\\ditributedSystem\\it17012966\\javaFX\\src\\javaFX\\HelloWorld.java")))) {

        //codeView.setText();
        System.out.println("code view start");
    }

    public void setText(String text) {

        String htmlString = "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>"
                + "<link href=\"" + getClass().getResource("prism.css") + "\"" + " rel=\"stylesheet\"" + " type=\"text/css\"" + " />\n"
                + "<script src=\"" + getClass().getResource("prism.js") + "\"" + " type=\"text/javascript\"" + "></script>\n"
                + "</head>"
                + "<body>\n"
                + "<pre>"
                + " <code class=\"language-java\">\n"
                + text
                + "</code>\n"
                + "</pre>\n"
                + "</body>\n"
                + "</html>";




        webEngine.loadContent(htmlString);
    }

    public void setFileToView(File file) {
        setText(FileUtilities.filesToString(file));

    }

}
