package test.coreFunctions;

import coreFunctions.ComplexityDueToSize;
import org.junit.jupiter.api.Test;
import utilities.FileUtils;
import utilities.TextUtils;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComplexityTest {
    public static final String JAVA_FILE_PATH = "F:\\SLIIT\\3rd yr 2nd sem\\SPM project\\spm\\src\\test\\resources\\ComplexityTestFile.java";

    File file = new File(JAVA_FILE_PATH);
    ComplexityDueToSize complexityDueToSize = new ComplexityDueToSize(file);

    @Test
    public void testFindComplexityDueToSize() {

        assertEquals("[ Line Number 6] : 1\n" +
                "[ Line Number 8] : 1\n" +
                "[ Line Number 9] : 1\n" +
                "[ Line Number 10] : 1\n" +
                "[ Line Number 11] : 1\n" +
                "[ Line Number 12] : 1\n" +
                "[ Line Number 13] : 4\n" +
                "[ Line Number 14] : 1\n" +
                "[ Line Number 15] : 1\n" +
                "[ Line Number 16] : 1\n" +
                "[ Line Number 17] : 1\n" +
                "[ Line Number 20] : 1\n" +
                "[ Line Number 23] : 1\n" +
                "[ Line Number 26] : 1\n" +
                "[ Line Number 38] : 2\n" +
                "[ Line Number 39] : 3\n" +
                "[ Line Number 43] : 2\n" +
                "[ Line Number 44] : 3\n" +
                "[ Line Number 48] : 2\n" +
                "[ Line Number 49] : 3\n" +
                "[ Line Number 53] : 2\n" +
                "[ Line Number 54] : 3\n" +
                "\n" +
                "\n" +
                "[Total Complexity Score ] : 37\n", complexityDueToSize.getComplexityAnalysisResult());

    }

    @Test
    public void testArithmeticOperatorAvailable() {
        int lineCs = 0;
        try {
            for (String line : FileUtils.convertToLisOfStrings(file)) {
                for (String word : TextUtils.getWordsDevidedFromSpaces(line)) {
                    if (complexityDueToSize.isArithmeticOperatorAvailable(word)) {
                        lineCs += 1;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(4, lineCs);
    }


    @Test
    public void testRelationalOperatorAvailable() {
        int CS = 0;
        try {
            for (String line : FileUtils.convertToLisOfStrings(file)) {
                for (String word : TextUtils.getWordsDevidedFromSpaces(line)) {
                    if (complexityDueToSize.isRelationalOperatorAvailable(word)) {
                        CS += 1;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(0, CS);
    }
}
