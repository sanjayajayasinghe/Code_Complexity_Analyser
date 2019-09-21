//package actions;
//
//import antlr_parser.JavaParser;
//import coreFunctions.ComplexityDueToControlStructures;
//import coreFunctions.ComplexityDueToSize;
//import coreFunctions.InheritanceComplexityImpl;
//import org.eclipse.jdt.core.dom.MethodDeclaration;
//import org.eclipse.jdt.core.dom.Statement;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
////TODO-javaparser.getLineNo-line number of each line
////todo-method to iterate through each statement
//
//public class CheckOverallCodeComplexityAction {
//
//    private File file;
//
//
//    public CheckOverallCodeComplexityAction(File file) {
//        this.file = file;
//    }
//
//    public File getFile() {
//        return file;
//    }
//
//    public void setFile(File file) {
//        this.file = file;
//    }
//
//    public int getControlStructureComplexity() throws IOException {
//        return new ComplexityDueToControlStructures().calculateComplexityForControlStructures(this.file);
//    }
//
//
//    public int getSizeComplexity() throws IOException {
//        return new ComplexityDueToSize(this.file).calculateComplexity();
//    }
//
//
//    public int getInheritanceComplexity() throws IOException {
//        return new InheritanceComplexityImpl().findInheritanceComplexityForFile(this.file);
//    }
//
//
//    public Map<Integer, Integer> getTWScoreMap() throws IOException {
//
//        Map<Integer, Integer> totalComplexityMap = new HashMap<>();
//        MethodDeclaration[] methodDeclarations = JavaParser.getMethods(getFile());
//
//        ComplexityDueToControlStructures complexityDueToControlStructures = new ComplexityDueToControlStructures();
//        Map<Integer, Integer> complexityScoreMap = complexityDueToControlStructures.getCreatedScoreMap(getFile());
//
//        InheritanceComplexityImpl inheritanceComplexity = new InheritanceComplexityImpl();
//        Map<Integer, Integer> inheritanceScoreMap = inheritanceComplexity.getCreatedScoreMap(getFile());
//
//
//        for (MethodDeclaration methodDeclaration : methodDeclarations) {
//            for (Object statement : methodDeclaration.getBody().statements()) {
//                Statement s = (Statement) statement;
//                totalComplexityMap
//                        .put(JavaParser.getLineNumber(
//                                s, getFile()), (complexityScoreMap.get(s) + inheritanceScoreMap.get(s)));
//            }
//
//        }
//        return totalComplexityMap;
//    }
//
//    public Map<Integer, Integer> getCPSScoreMap() throws IOException {
//
//        Map<Integer, Integer> CPSComplexityMap = new HashMap<>();
//        MethodDeclaration[] methodDeclarations = JavaParser.getMethods(getFile());
//
//        ComplexityDueToSize complexityDueToSize = new ComplexityDueToSize(getFile());
//
//        Map<Integer, Integer> sizeScoreMap = complexityDueToSize.getCreatedScoreMap();
//        Map<Integer, Integer> totalComplexityMap = getTWScoreMap();
//
//        for (MethodDeclaration methodDeclaration : methodDeclarations) {
//            for (Object statement : methodDeclaration.getBody().statements()) {
//                Statement s = (Statement) statement;
//
//                CPSComplexityMap
//                        .put(JavaParser.getLineNumber(
//                                s, getFile()), (sizeScoreMap.get(s) * totalComplexityMap.get(s)));
//
//            }
//
//        }
//
//        return CPSComplexityMap;
//
//    }
//
//
//    public Map<Integer, Integer> getCRMap() throws IOException {
//
//        Map<Integer, Integer> recursionScoreMap = new HashMap<>();
//        Map<Integer, Integer> CPSScoreMap = getCPSScoreMap();
//
//        MethodDeclaration[] methodDeclarations = JavaParser.getMethods(getFile());
//        for (MethodDeclaration methodDeclaration : methodDeclarations) {
//            if (JavaParser.isRecursionAvailable(methodDeclaration)) {
//                for (Object statement : methodDeclaration.getBody().statements()) {//lines
//                    Statement s = (Statement) statement;
//                    recursionScoreMap.put(JavaParser.getLineNumber(
//                            s, getFile()), (CPSScoreMap.get(s) * 2));
//
//                }
//            } else {
//                for (Object statement : methodDeclaration.getBody().statements()) {//lines
//                    Statement s = (Statement) statement;
//                    recursionScoreMap.put(JavaParser.getLineNumber(
//                            s, getFile()), (CPSScoreMap.get(s)));
//
//                }
//            }
//
//
//
//        }
//        return recursionScoreMap;
//    }
//
//    public int calculateTotalValue() throws IOException {
//        int total = 0;
//        for (int line : getCRMap().keySet()) {
//            total += getCRMap().get(line);
//        }
//        return total;
//    }
//
//
//}
