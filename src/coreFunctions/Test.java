package coreFunctions;

import antlr_parser.JavaParser;

import java.io.File;
import java.io.IOException;
//import test.coreFunctions.ComplexityTest;

public class Test {

    public static void main(String[] args) throws IOException {


        File testFile = new File("C:\\Users\\gisilk\\Downloads\\abc.java");
        JavaParser.getAvailableClassNames(testFile);


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
