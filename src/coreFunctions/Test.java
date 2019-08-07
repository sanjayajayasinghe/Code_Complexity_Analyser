package coreFunctions;

import java.io.File;

import languageCheckers.JavaSyntaxChecker;

public class Test {

	public static void main(String[] args) {
		
		
		File testFile = new File("C:\\Users\\gisilk\\Downloads\\abc.java");
		JavaSyntaxChecker.compileJava(testFile.getAbsolutePath());
		ComplexityDueToSize test = new ComplexityDueToSize(testFile);
		test.calculateComplexity();

	}

}
