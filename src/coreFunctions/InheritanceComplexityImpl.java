package coreFunctions;

import antlr_parser.JavaParser;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Statement;
import utilities.FileUtilities;
import utilities.TextUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class InheritanceComplexityImpl implements InheritanceComplexity {

    private Map<Integer, Integer> scoremap = new HashMap<>();
    private Map<String, List<String>> inheritanceMap = new HashMap<>();
    private Map<String, Integer> complexityMap = new HashMap<>();
    private int fileListTotalComplexity = 0;

    private int calculateComplexityForJava(File javaFile) throws IOException {
        String extendedClass = JavaParser.getExtendedClassName(javaFile);
        //List<String> implementedInterfaces = JavaParser.getImplementedInterfaceNames(file);
        int javaFileComplexity = 2;

        if (!(extendedClass.equals(""))) {
            javaFileComplexity++;
            inheritanceMap.computeIfAbsent(javaFile.getName(), k -> new ArrayList<>()).add(extendedClass);
        }
        complexityMap.put(javaFile.getName(), javaFileComplexity);
        System.out.println("\nTotal File Complexity of file " + javaFile.getName() + " ( including object class for java files ) : " + (javaFileComplexity) + "\n");
        fileListTotalComplexity += (javaFileComplexity);
        return fileListTotalComplexity;
    }

    private int calculateComplexityForCPlus(File cPlusFile) {
        int cPlusFileComplexity = 1;
        int i = 1;
        try {
            for (String line : FileUtilities.convertToLisOfStrings(cPlusFile)) {
                int lineCs = 0;
                String[] strings = TextUtils.getWordsDevidedFromSpaces(line);
                for (int j = 0; j < strings.length; j++) {
                    if (wordMatchesInheritance(strings[j], cPlusFile) && strings[j + 1].equals("public")) {
                        lineCs += 1;
                        inheritanceMap.computeIfAbsent(strings[j - 1], k -> new ArrayList<>()).add(strings[j + 2]);
                    }
                }
                System.out.println("Line Score : " + i + " : " + lineCs);
                i++;

                cPlusFileComplexity += lineCs;

            }
            complexityMap.put(cPlusFile.getName(), cPlusFileComplexity);
            System.out.println("\nTotal File Complexity of file " + cPlusFile.getName() + " : " + (cPlusFileComplexity) + "\n");
            fileListTotalComplexity += (cPlusFileComplexity);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileListTotalComplexity;
    }

    private void scanInheritance() {
        for (String string : inheritanceMap.keySet()) {
            System.out.println("\nClass showing inheritance : " + string + " \nParent class(es) : " + inheritanceMap.get(string) + "\n");
        }
    }


    private boolean wordMatchesInheritance(String word, File file) {
        if (file.getName().endsWith(".java") || file.getName().endsWith(".cpp")) {
            return (Arrays.asList(JavaKeywords.INHERITANCE_KEYWORDS).contains(word));
        }
        return false;
    }

    @Override
    public Map<String, Integer> findInheritedClassesForFileList(File baseFile) {

        String str = baseFile.getAbsolutePath();
        int index = str.lastIndexOf('\\');
        String dir_path = str.substring(0, index);

        File folder = new File(dir_path);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                System.out.println(" ==== File ==== " + listOfFiles[i].getName() + " ======== ");
                if (listOfFiles[i].getName().endsWith(".java")) {
                    try {
                        calculateComplexityForJava(listOfFiles[i]);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (listOfFiles[i].getName().endsWith(".cpp")) {
                    calculateComplexityForCPlus(listOfFiles[i]);
                }
            } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory " + listOfFiles[i].getName());
            }
        }
        scanInheritance();

        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("Files scanned ", listOfFiles.length);
        resultMap.put("Total number of classes with inheritance found ", inheritanceMap.keySet().size());
        resultMap.put("Total file complexity of selected files ", this.fileListTotalComplexity);

        System.out.println("Files scanned : " + listOfFiles.length);
        System.out.println("Total number of classes showing inheritance found : " + inheritanceMap.keySet().size());
        System.out.println("Total file complexity of selected files " + this.fileListTotalComplexity);
        return resultMap;
    }

    public Map<String, Integer> getComplexityDueToInheritanceMap() {
        return this.complexityMap;
    }

    @Override
    public int findInheritanceComplexityForFile(File file) throws IOException {
        int inheritanceComplexity = 0;
        if (file.getName().endsWith(".java")) {
            try {
                inheritanceComplexity = calculateComplexityForJava(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (file.getName().endsWith(".cpp")) {
            inheritanceComplexity = calculateComplexityForCPlus(file);
        }

        scanInheritance();
        return inheritanceComplexity;
    }

    public Map<Integer, Integer> getCreatedScoreMap(File file) throws IOException {

        int inheritanceComplexity=findInheritanceComplexityForFile(file);
        List<String> strings=FileUtilities.convertToLisOfStrings(file);
        int x=1;
        for(String s:strings){
            scoremap.put(x,inheritanceComplexity );
            x++;
        }

        return scoremap;

    }

}


