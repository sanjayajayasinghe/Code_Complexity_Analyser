package coreFunctions;

import utilities.FileUtils;
import utilities.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

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
                    if (wordMatchesInheritance(word)) {

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
        return 0;

    }

    @Override
    public boolean wordMatchesInheritance(String word) {
        if (codeFile.getName().endsWith(".java")) {
            return (Arrays.asList(JavaKeywords.INHERITANCE_KEYWORDS).contains(word));
        }
        return false;
    }

    @Override
    public void findInheritedClasses() {

        String str = this.codeFile.getAbsolutePath();
        int index = str.lastIndexOf('\\');
        String dir_path = str.substring(0, index);

        File folder = new File(dir_path);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                System.out.println("File " + listOfFiles[i].getName());
                if (listOfFiles[i].getName().endsWith(".java")) {
                    calculateComplexity();
                }
            } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory " + listOfFiles[i].getName());
            }
        }
        System.out.println("Files scanned:"+listOfFiles.length);
        System.out.println("inherited classes found:"+this.Cs);
    }

    //unused
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


