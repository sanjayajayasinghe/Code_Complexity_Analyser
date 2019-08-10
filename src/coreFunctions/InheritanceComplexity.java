package coreFunctions;

import java.io.File;

public interface InheritanceComplexity {

    boolean wordMatchesInheritance(String word, File file);

    Map<String, Integer> findInheritedClasses(File file);
}
