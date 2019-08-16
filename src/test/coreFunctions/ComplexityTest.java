package test.coreFunctions;

import coreFunctions.ComplexityDueToSize;
import org.junit.Ignore;
import org.junit.Test;
import utilities.FileUtilities;
import utilities.TextUtils;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static utilities.TestFileConstants.DOG_JAVA_FILE_PATH;
import static utilities.TestFileConstants.RELATIVE_DIR_PATH;


public class ComplexityTest {

    File file=new File(RELATIVE_DIR_PATH.concat(DOG_JAVA_FILE_PATH));
    ComplexityDueToSize complexityDueToSize = new ComplexityDueToSize(file);

    @Test
    public void testFindComplexityDueToSize() {

        assertEquals("[ Line Number 10] : 1\n" +
                "[ Line Number 15] : 1\n" +
                "\n" +
                "\n" +
                "[Total Complexity Score ] : 2\n", complexityDueToSize.getComplexityAnalysisResult());

    }

    @Test
    public void testArithmeticOperatorAvailable() {
        int lineCs = 0;
        try {
            for (String line : FileUtilities.convertToLisOfStrings(file)) {
                for (String word : TextUtils.getWordsDevidedFromSpaces(line)) {
                    if (complexityDueToSize.isArithmeticOperatorAvailable(word)) {
                        lineCs += 1;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(0, lineCs);
    }


    @Test
    public void testRelationalOperatorAvailable() {
        int CS = 0;
        try {
            for (String line : FileUtilities.convertToLisOfStrings(file)) {
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
