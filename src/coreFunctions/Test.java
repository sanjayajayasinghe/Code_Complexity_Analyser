package coreFunctions;

import java.io.File;
import java.io.IOException;

import antlr_parser.JavaParser;
import languageCheckers.JavaSyntaxChecker;
//import test.coreFunctions.ComplexityTest;

public class Test {

    public static void main(String[] args) throws IOException {


        File testFile = new File("C:\\Users\\Nishitha\\Desktop\\test\\test.java");
        JavaParser.parse(testFile);
//        JavaSyntaxChecker.compileJava(testFile.getAbsolutePath());
//        ComplexityDueToSize test = new ComplexityDueToSize(testFile);
//        test.calculateComplexity();

//		File testFile = new File("C:\\Users\\Nishitha\\Desktop\\test\\test.java");
//		InheritanceComplexityImpl inheritanceComplexity=new InheritanceComplexityImpl();
//		inheritanceComplexity.findInheritedClasses(testFile);

//		ComplexityTest complexityTest= new ComplexityTest();
//		complexityTest.testFindComplexityDueToSize();
//		complexityTest.testArithmeticOperatorAvailable();
//		complexityTest.testRelationalOperatorAvailable();


    }

}
