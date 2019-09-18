package coreFunctions;

import java.io.File;
import java.io.IOException;

public interface ComplexityByRecursion extends CodeComplexity {


    boolean isRecursionFound(File file) throws IOException;

    int getRecursionLines(File file) throws IOException;
}
