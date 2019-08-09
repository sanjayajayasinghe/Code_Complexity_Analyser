package languageCheckers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class JavaSyntaxChecker {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	
	public static String compileJava(String path) {
		
		StringBuilder result = new StringBuilder();
		
		if(!(check(path).size() > 0)) {
			return "No Syntax or Compilation Errors found...\n\n";
		}else {
			result.append("Errors found...!!! \n");
		}
		
		
		
		for(SyntaxErrorsHolder ob : check(path)) {
			
			if(ob.getErrorType().equalsIgnoreCase("ERROR")){
				//System.out.print("ERROR : " );
			}else {
				//System.out.print(ob.getErrorType() + " : ");
			}
			
			//System.out.println("Line : " + ob.getLineNumber() + " Position : " + ob.getPosition() + " : " + ob.getMessage());;
			result.append("Line : " + ob.getLineNumber() + " Position : " + ob.getPosition() + " : " + ob.getMessage()+"\n");
		}
		return result.toString();
		//System.out.println("\n");
	}

	private static List<SyntaxErrorsHolder> check(String file) {
		System.setProperty("java.home", "C:\\Program Files\\Java\\jdk1.8.0_91");
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

		StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
		Iterable<? extends JavaFileObject> compilationUnits = fileManager
				.getJavaFileObjectsFromStrings(Arrays.asList(file));

		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
		compiler.getTask(null, fileManager, diagnostics, null, null, compilationUnits).call();

		List<SyntaxErrorsHolder> checkers = new ArrayList<SyntaxErrorsHolder>();
		Formatter formatter = new Formatter();
		for (Diagnostic diagnostic : diagnostics.getDiagnostics()) {
			
			SyntaxErrorsHolder e = new SyntaxErrorsHolder(diagnostic.getKind().toString(), diagnostic.getLineNumber(), diagnostic.getPosition(), diagnostic.getMessage(Locale.ROOT));
			checkers.add(e);
			
		}

		return checkers;
	}
	
	private static class SyntaxErrorsHolder{
		
		String errorType;
		long lineNumber;
		long position;
		String message;
		
		public SyntaxErrorsHolder(String errorType, long lineNumber, long position, String message) {
			super();
			this.errorType = errorType;
			this.lineNumber = lineNumber;
			this.position = position;
			this.message = message;
		}

		public String getErrorType() {
			return errorType;
		}

		public long getLineNumber() {
			return lineNumber;
		}

		public long getPosition() {
			return position;
		}

		public String getMessage() {
			return message;
		}
		
		
		
		
	}
}
