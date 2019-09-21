package coreFunctions;

import antlr_parser.JavaParser;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import java.io.File;
import java.io.IOException;
import java.util.Map;

//import com.github.javaparser.ast.Node;

public class ComplexityDueToRecursion implements ComplexityByRecursion {

    @Override
    public int getRecursionLines(File file) throws IOException {
        int no=0;
        MethodDeclaration[] methods = JavaParser.getMethods(file);

        for(MethodDeclaration method:methods) {
            if (JavaParser.isRecursionAvailable(method)) {
                    no = JavaParser.getNumberOfLinesInBlock(method.getBody());
                }
            }

        return no;
        }



    @Override
    public int calculateComplexity() {
        return 0;
    }
}
