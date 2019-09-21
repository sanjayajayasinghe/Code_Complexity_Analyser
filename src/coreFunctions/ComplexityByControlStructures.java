package coreFunctions;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public interface ComplexityByControlStructures extends CodeComplexity {

    Map<Integer, Integer> calculateComplexityForControlStructures(File file) throws IOException;

}
