package coreFunctions;

import antlr_parser.JavaParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//import com.github.javaparser.ast.Node;

public class ComplexityDueToRecursion implements ComplexityByRecursion {

    File codeFile;

    public ComplexityDueToRecursion(File file) {
        this.codeFile = file;
    }

    @Override
    public boolean isRecursionFound() throws IOException {
        MethodDeclaration[] methods = JavaParser.getMethods(codeFile);
        for (MethodDeclaration method : methods) {
            String methodName = method.getName().toString();
            int noOfParameters = method.parameters().size();

            final List<MethodInvocation> methodInvocations = new ArrayList<>();


            method.getBody().accept(new ASTVisitor() {
                @Override
                public boolean visit(MethodInvocation node) {
                    methodInvocations.add(node);
                    return super.visit(node);
                }
            });


            for(MethodInvocation methodInvocation : methodInvocations){
                if(methodInvocation.getName().toString().equalsIgnoreCase(methodName)){
                    if (methodInvocation.arguments().size() == noOfParameters){
                        System.out.println("Recursion found for line number" + method.getStartPosition());
                        return true;
                    }
                }
            }

        }
        return false;


    }

    @Override
    public int calculateComplexity() {
        return 0;
    }
}
