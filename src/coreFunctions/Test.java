package coreFunctions;

import java.io.File;

import languageCheckers.JavaSyntaxChecker;
import test.coreFunctions.ComplexityTest;

public class Test {

    public static void main(String[] args) {


        File testFile = new File("C:\\Users\\gisilk\\Downloads\\abc.java");
        JavaSyntaxChecker.compileJava(testFile.getAbsolutePath());
        ComplexityDueToSize test = new ComplexityDueToSize(testFile);
        test.calculateComplexity();

//		File testFile = new File("C:\\Users\\Nishitha\\Desktop\\test\\test.java");
//		InheritanceComplexityImpl inheritanceComplexity=new InheritanceComplexityImpl();
//		inheritanceComplexity.findInheritedClasses(testFile);

//		ComplexityTest complexityTest= new ComplexityTest();
//		complexityTest.testFindComplexityDueToSize();
//		complexityTest.testArithmeticOperatorAvailable();
//		complexityTest.testRelationalOperatorAvailable();


    }

}
