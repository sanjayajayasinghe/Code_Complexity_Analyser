package coreFunctions;

import java.io.File;
import java.util.Map;

public interface InheritanceComplexity {

    boolean wordMatchesInheritance(String word, File file);

    Map<String, Integer> findInheritedClassesForFileList(File baseFile);

    int findInheritanceComplexityForFile(File file);
}
