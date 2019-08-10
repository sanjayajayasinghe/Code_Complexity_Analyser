package test.coreFunctions;

import coreFunctions.ComplexityDueToSize;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ComplexityTest {
    public static final String JAVA_FILE_PATH = "F:\\SLIIT\\3rd yr 2nd sem\\SPM project\\spm\\src\\test\\resources\\ComplexityTestFile.java";

    File file = new File(JAVA_FILE_PATH);
    @Test
    public void testFindComplexityDueToSize() {
        File file = new File(JAVA_FILE_PATH);

        ComplexityDueToSize complexityDueToSize = new ComplexityDueToSize(file);
        assertEquals("[ Line Number 5] : 1\n" +
                "[ Line Number 6] : 1\n" +
                "[ Line Number 9] : 1\n" +
                "\n" +
                "\n" +
                "[Total Complexity Score ] : 3\n", complexityDueToSize.getComplexityAnalysisResult());

    }
}
