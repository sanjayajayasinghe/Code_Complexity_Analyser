package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import utilities.FileUtilities;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class CodeViewController implements Initializable {

    @FXML
    WebView codeView;

    private WebEngine webEngine;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        webEngine = codeView.getEngine();
        System.out.println("code view start");
    }

    public void setText(String text) {

        StringBuilder builder = new StringBuilder();
        String[] split = text.split("\n");
        int i = 1;
        for(String s : split){
            builder.append(i).append("\t").append(s).append("\n");
            i++;
        }


        String htmlString = "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>"
                + "<link href=\"" + getClass().getResource("prism.css") + "\"" + " rel=\"stylesheet\"" + " type=\"text/css\"" + " />\n"
                + "<script src=\"" + getClass().getResource("prism.js") + "\"" + " type=\"text/javascript\"" + "></script>\n"
                + "</head>"
                + "<body>\n"
                + "<pre>"
                + " <code class=\"language-java\">\n"
                + builder.toString()
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
