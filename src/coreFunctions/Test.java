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


        CheckOverallCodeComplexityAction c = new CheckOverallCodeComplexityAction(testFile);
        Map<Integer, ScoreObject> scoreMap = c.getScoreMap();
        System.out.println(scoreMap);


    }

}
