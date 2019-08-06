package coreFunctions;

import java.io.File;

public class Test {

	public static void main(String[] args) {
		
		
		File testFile = new File("C:\\Users\\gisilk\\Downloads\\abc.java");
		ComplexityDueToSize test = new ComplexityDueToSize(testFile);
		test.calculateComplexity();

	}

}
