package languageCheckers;

import javax.tools.*;
import java.io.File;
import java.util.*;

public class JavaSyntaxChecker {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    public static String compileJava(String path) throws Exception {

        StringBuilder result = new StringBuilder();

        if (!(check(path).size() > 0)) {
            return "No Syntax or Compilation Errors found...\n\n";
        } else {
            result.append("Errors found...!!! \n");
        }
        deleteCompiledFile(path);


        for (SyntaxErrorsHolder ob : check(path)) {

            if (ob.getErrorType().equalsIgnoreCase("ERROR")) {
                //System.out.print("ERROR : " );
            } else {
                //System.out.print(ob.getErrorType() + " : ");
            }

            //System.out.println("Line : " + ob.getLineNumber() + " Position : " + ob.getPosition() + " : " + ob.getMessage());;
            result.append("Line : " + ob.getLineNumber() + " Position : " + ob.getPosition() + " : " + ob.getMessage() + "\n");
        }
        return result.toString();
        //System.out.println("\n");
    }

    private static void deleteCompiledFile(String path) {

        String classPath = path.substring(0, path.lastIndexOf(".")) + ".class";
        File classFile = new File(classPath);
        classFile.delete();
        System.out.println(classPath);

    }

    private static List<SyntaxErrorsHolder> check(String file) throws Exception {

        if(ToolProvider.getSystemJavaCompiler() == null){
            System.setProperty("java.home", getJdkFolderPath());
        }

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> compilationUnits = fileManager
                .getJavaFileObjectsFromStrings(Arrays.asList(file));

        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
        compiler.getTask(null, fileManager, diagnostics, null, null, compilationUnits).call();

        List<SyntaxErrorsHolder> checkers = new ArrayList<SyntaxErrorsHolder>();
        Formatter formatter = new Formatter();
        for (Diagnostic diagnostic : diagnostics.getDiagnostics()) {

            SyntaxErrorsHolder e = new SyntaxErrorsHolder(diagnostic.getKind().toString(), diagnostic.getLineNumber(), diagnostic.getPosition(), diagnostic.getMessage(Locale.ROOT));
            checkers.add(e);

        }

        return checkers;
    }

    private static String getJdkFolderPath() throws Exception {
        File javaFolder = new File("C:\\Program Files\\Java");
        if(javaFolder.exists()){
            File[] files = javaFolder.listFiles();
            for(File f : files){
                if(f.getName().startsWith("jdk")){
                    return f.getAbsolutePath();
                }
            }
        }else{
            throw new Exception("Invalid JDK path");
        }
        return "";
    }

    private static class SyntaxErrorsHolder {

        String errorType;
        long lineNumber;
        long position;
        String message;

        public SyntaxErrorsHolder(String errorType, long lineNumber, long position, String message) {
            super();
            this.errorType = errorType;
            this.lineNumber = lineNumber;
            this.position = position;
            this.message = message;
        }

        public String getErrorType() {
            return errorType;
        }

        public long getLineNumber() {
            return lineNumber;
        }

        public long getPosition() {
            return position;
        }

        public String getMessage() {
            return message;
        }


    }
}
