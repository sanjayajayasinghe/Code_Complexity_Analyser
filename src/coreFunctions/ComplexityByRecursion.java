package coreFunctions;

import org.eclipse.jdt.core.dom.MethodDeclaration;

import java.io.File;
import java.io.IOException;

public interface ComplexityByRecursion extends CodeComplexity {


    boolean isRecursionFound(MethodDeclaration method) throws IOException;

    int getRecursionLines(File file) throws IOException;
}
