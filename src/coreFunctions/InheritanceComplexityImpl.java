package coreFunctions;

import utilities.FileUtils;
import utilities.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InheritanceComplexityImpl implements InheritanceComplexity {

    private int Cs;
    private File codeFile;


    public InheritanceComplexityImpl(File codeFile) {
        this.codeFile = codeFile;
    }

    @Override
    public int calculateComplexity() {
        int i = 1;
        try {
            for (String line : FileUtils.convertToLisOfStrings(this.codeFile)) {
                int lineCs = 0;
                for (String word : TextUtils.getWordsDevidedFromSpaces(line)) {
                    if (isFileShowingInheritance(word)) {

                        lineCs += 1;

                    }
                }
                System.out.println("Line Score : " + i + " : " + lineCs);
                i++;
                this.Cs += lineCs;
            }
            System.out.println("Total Cs : " + this.Cs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        readAllFilesInPath(this.codeFile.getAbsolutePath());
        return 0;

    }

    @Override
    public boolean isFileShowingInheritance(String word) {
        if (codeFile.getName().endsWith(".java")) {
            if (Arrays.asList(JavaKeywords.INHERITANCE_KEYWORDS).contains(word)) {
                return true;
            }
        }
        return false;
    }


    public  void readAllFilesInPath(String path){
        String str=path;
        int index=str.lastIndexOf('\\');
        String dir_path=str.substring(0,index);

        File folder = new File(dir_path);
        File[] listOfFiles = folder.listFiles();
        List<File> analyzedFiles=new ArrayList<>();

//TODO STOP ON ANALYSIS
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                System.out.println("File " + listOfFiles[i].getName());
                if(listOfFiles[i].getName().endsWith(".java")){
//                    this.codeFile=new File(listOfFiles[i].toString());
//                    analyzedFiles.add(this.codeFile);
//                    if(!(analyzedFiles.contains(this.codeFile))){
//                        calculateComplexity();
//                    }


                }
            } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory " + listOfFiles[i].getName());
            }
        }

    }

















    public void close(String path) throws IOException {
        String fileName = path;// provide an absolute path here to be sure that file is found
        BufferedReader reader = null;
        try {


            reader = new BufferedReader(new FileReader(new File(fileName)));
            //  }

            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println("Line read: " + line);
            }
        } catch (IOException e) {

        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

}


