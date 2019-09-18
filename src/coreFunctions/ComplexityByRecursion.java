package coreFunctions;

import org.eclipse.jdt.core.dom.MethodDeclaration;

import java.io.File;
import java.io.IOException;

public interface ComplexityByRecursion extends CodeComplexity {

    int getRecursionLines(File file) throws IOException;
}
