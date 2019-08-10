package coreFunctions;

import java.io.File;
import java.util.Map;

public interface InheritanceComplexity {

    boolean wordMatchesInheritance(String word, File file);

    Map<String, Integer> findInheritedClasses(File file);
}
