package coreFunctions;

import java.io.File;
import java.io.IOException;

public interface ComplexityByControlStructures extends CodeComplexity {

    int calculateComplexityForControlStructuresForIfBlockAndConditions(File file) throws IOException;

}
