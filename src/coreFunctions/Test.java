package coreFunctions;

import antlr_parser.JavaParser;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SwitchStatement;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static utilities.TestFileConstants.FIBONACCI_MAIN;
import static utilities.TestFileConstants.RELATIVE_DIR_PATH;
//import test.coreFunctions.ComplexityTest;

public class Test {

    public static void main(String[] args) throws IOException {

//        File testFile = new File(RELATIVE_DIR_PATH.concat(FIBONACCI_MAIN));
//        ComplexityDueToControlStructures complexityDueToControlStructures=new ComplexityDueToControlStructures();
//        System.out.println(complexityDueToControlStructures
//                .calculateComplexityForControlStructuresForIfBlockAndConditions(testFile));


//
//        File testFile = new File("C:\\Users\\gisilk\\Downloads\\abc.java");
//        MethodDeclaration[] methods = JavaParser.getMethods(testFile);
//        for(MethodDeclaration m : methods){
//            final List<SwitchStatement> switchStatements = JavaParser.getSwitchBlocks(m);
//
//            for(SwitchStatement ifs : switchStatements){
//                JavaParser.getCaseStatements(ifs);
//                System.out.println();
//            }
//            System.out.println();
//        }



//        JavaSyntaxChecker.compileJava(testFile.getAbsolutePath());
//        ComplexityDueToSize test = new ComplexityDueToSize(testFile);
//        test.calculateComplexity();

//		File testFile = new File(RELATIVE_DIR_PATH.concat(FIBONACCI_MAIN));
//		InheritanceComplexityImpl inheritanceComplexity=new InheritanceComplexityImpl();
//		inheritanceComplexity.findInheritedClasses(testFile);
//        for (String s:inheritanceComplexity.getComplexityDueToInheritanceMap().keySet()) {
//            System.out.println(s+" "+inheritanceComplexity.getComplexityDueToInheritanceMap().get(s));
//        }

        //ComplexityDueToRecursion complexityDueToRecursion=new ComplexityDueToRecursion(testFile);
		//System.out.println(complexityDueToRecursion.isRecursionFound());

//		ComplexityTest complexityTest= new ComplexityTest();
//		complexityTest.testFindComplexityDueToSize();
//		complexityTest.testArithmeticOperatorAvailable();
//		complexityTest.testRelationalOperatorAvailable();


    }

}
