package coreFunctions;

import utilities.FileUtils;
import utilities.TextUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class InheritanceComplexityImpl implements InheritanceComplexity {

    Map<String, List<String>> inheritanceMap = new HashMap<>();
    private int Cs;

    public int calculateComplexity(File file) {

        int i = 1;
        try {
            for (String line : FileUtils.convertToLisOfStrings(file)) {
                int lineCs = 0;
                String[] strings = TextUtils.getWordsDevidedFromSpaces(line);
                for (int j = 0; j < strings.length; j++) {
                    if (wordMatchesInheritance(strings[j], file)) {
                        lineCs += 1;
                        inheritanceMap.computeIfAbsent(strings[j - 1], k -> new ArrayList<>()).add(strings[j + 1]);
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

    public void scanInheritance() {
        for (String string : inheritanceMap.keySet()) {
            System.out.println("\nClass showing inheritance : " + string + " \nParent class(es) : " + inheritanceMap.get(string)+"\n");
        }
    }


    @Override
    public boolean wordMatchesInheritance(String word, File file) {
        if (file.getName().endsWith(".java")) {
            return (Arrays.asList(JavaKeywords.INHERITANCE_KEYWORDS).contains(word));
        }
        return false;
    }

    @Override
    public void findInheritedClasses(File file) {

        String str = file.getAbsolutePath();
        int index = str.lastIndexOf('\\');
        String dir_path = str.substring(0, index);

        File folder = new File(dir_path);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                System.out.println("====File==== " + listOfFiles[i].getName());
                if (listOfFiles[i].getName().endsWith(".java")) {
                    calculateComplexity(listOfFiles[i]);
                    scanInheritance();
                }
            } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory " + listOfFiles[i].getName());
            }
        }
        System.out.println("Files scanned:" + listOfFiles.length);
        System.out.println("Total number of classes showing inheritance found :" + this.Cs);
    }


}


