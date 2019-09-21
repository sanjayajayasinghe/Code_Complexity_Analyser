package coreFunctions;

import Models.ScoreObject;
import actions.CheckOverallCodeComplexityAction;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static utilities.TestFileConstants.FIBONACCI_MAIN;
import static utilities.TestFileConstants.RELATIVE_DIR_PATH;


public class Test {

    public static void main(String[] args) throws IOException {

        File testFile = new File(RELATIVE_DIR_PATH.concat(FIBONACCI_MAIN));

        ComplexityDueToSize complexityDueToSize=new ComplexityDueToSize(testFile);
        final Map<Integer, Integer> createdScoreMap = complexityDueToSize.getCreatedScoreMap();

        System.out.println("======================Size==============================");

        for(Integer line:createdScoreMap.keySet()){
            System.out.println("Line "+line+" -> "+createdScoreMap.get(line));
        }

        ComplexityDueToControlStructures complexityDueToControlStructures=new ComplexityDueToControlStructures();
        Map<Integer, Integer> integerIntegerMap = complexityDueToControlStructures.calculateComplexityForControlStructures(testFile);

        System.out.println("======================Nesting Levels and Control Structure ==============================");
        for(Integer line:integerIntegerMap.keySet()){
            System.out.println("Line "+line+" -> "+integerIntegerMap.get(line));
        }



        ComplexityByRecursion complexityByRecursion=new ComplexityDueToRecursion();
        Map<Integer, Boolean> recursionLines = complexityByRecursion.getRecursionLines(testFile);

        System.out.println("======================Recursion==============================");
        for(Integer line:recursionLines.keySet()){
            System.out.println("Line "+line+" -> "+recursionLines.get(line));
        }

        InheritanceComplexityImpl inheritanceComplexity=new InheritanceComplexityImpl();
        Map<Integer, Integer> inheritanceComplexityCreatedScoreMap = inheritanceComplexity.getCreatedScoreMap(testFile);

        System.out.println("======================Inheritance==============================");
        for(Integer line:inheritanceComplexityCreatedScoreMap.keySet()){
            System.out.println("Line "+line+" -> "+inheritanceComplexityCreatedScoreMap.get(line));
        }


        CheckOverallCodeComplexityAction c = new CheckOverallCodeComplexityAction(testFile);
        Map<Integer, ScoreObject> scoreMap = c.getScoreMap();

        System.out.println("======================Overall Complexity Map==============================");

        for(Integer line:scoreMap.keySet()){
            System.out.println("====================================================");
            System.out.println("Line "+line+" CS -> "+scoreMap.get(line).getCS());
            System.out.println("Line "+line+" CNC-> "+scoreMap.get(line).getCNC());
            System.out.println("Line "+line+" CI-> "+scoreMap.get(line).getCI());
            System.out.println("Line "+line+" TW-> "+scoreMap.get(line).getTW());
            System.out.println("Line "+line+" CR-> "+scoreMap.get(line).getCR());
            System.out.println("====================================================");

        }

    }

}
