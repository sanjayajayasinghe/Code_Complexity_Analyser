package coreFunctions;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public interface InheritanceComplexity {

    Map<String, Integer> findInheritedClassesForFileList(File baseFile);

    int findInheritanceComplexityForFile(File file) throws IOException;
}
