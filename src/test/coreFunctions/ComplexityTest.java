package test.coreFunctions;

import coreFunctions.ComplexityDueToSize;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComplexityTest {
    public static final String JAVA_FILE_PATH = "F:\\SLIIT\\3rd yr 2nd sem\\SPM project\\spm\\src\\test\\resources\\ComplexityTestFile.java";

    File file = new File(JAVA_FILE_PATH);

    @Test
    public void testFindComplexityDueToSize() {
        File file = new File(JAVA_FILE_PATH);

        ComplexityDueToSize complexityDueToSize = new ComplexityDueToSize(file);
        assertEquals("[ Line Number 6] : 1\n" +
                "[ Line Number 10] : 1\n" +
                "[ Line Number 11] : 1\n" +
                "[ Line Number 12] : 1\n" +
                "[ Line Number 13] : 1\n" +
                "[ Line Number 14] : 4\n" +
                "[ Line Number 15] : 1\n" +
                "[ Line Number 16] : 1\n" +
                "[ Line Number 17] : 1\n" +
                "[ Line Number 18] : 1\n" +
                "[ Line Number 21] : 1\n" +
                "[ Line Number 24] : 1\n" +
                "[ Line Number 27] : 1\n" +
                "[ Line Number 39] : 2\n" +
                "[ Line Number 41] : 3\n" +
                "[ Line Number 44] : 2\n" +
                "[ Line Number 46] : 2\n" +
                "[ Line Number 49] : 2\n" +
                "[ Line Number 51] : 2\n" +
                "[ Line Number 54] : 2\n" +
                "[ Line Number 56] : 2\n" +
                "\n" +
                "\n" +
                "[Total Complexity Score ] : 33\n", complexityDueToSize.getComplexityAnalysisResult());

    }
}
