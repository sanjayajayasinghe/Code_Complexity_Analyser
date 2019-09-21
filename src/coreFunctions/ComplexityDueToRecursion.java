package coreFunctions;

import antlr_parser.JavaParser;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import utilities.FileUtilities;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class ComplexityDueToRecursion implements ComplexityByRecursion {

    Map<Integer,Boolean> scoreMap = new HashMap<>();

    @Override
    public Map<Integer, Boolean> getRecursionLines(File file) throws IOException {
        initializeMap(file);
        MethodDeclaration[] methods = JavaParser.getMethods(file);
        for (MethodDeclaration method : methods) {
            if (JavaParser.isRecursionAvailable(method)) {
                Map<String, Integer> linesInside = JavaParser.getLinesInside(method, file);
                for(int i = linesInside.get("start"); i <= linesInside.get("end");i++){
                    scoreMap.put(i,true);
                }
            }
        }

        return scoreMap;
    }

    private void initializeMap(File file) throws IOException {
        List<String> strings = FileUtilities.convertToLisOfStrings(file);
        int i = 1;
        for(String s : strings){
            scoreMap.put(i,false);
            i++;
        }
    }

    @Override
    public int calculateComplexity() {
        return 0;
    }
}
