package coreFunctions;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public interface ComplexityByRecursion extends CodeComplexity {

    Map<Integer, Boolean> getRecursionLines(File file) throws IOException;
}
