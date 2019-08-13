package antlr_parser;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.IfStatement;

import utilities.FileUtilities;

public class JavaParser {
	
	public static void parse(File file) throws IOException {
		System.out.println("Here");
		
		ASTParser parser = ASTParser.newParser(AST.JLS3);		
		char[] fileContent = FileUtilities.getFileContent(file).toCharArray();
		parser.setSource(fileContent);
		CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		cu.accept(new ASTVisitor() {
						
			public boolean visit(IfStatement node) {
//				SimpleName name = node.toString()
//				int lineNumber = cu.getLineNumber(name.getStartPosition());
//				
//				System.out.println("Name : " + name.toString());
//				System.out.println("Line : " + lineNumber);
				System.out.println("---------------------------------");
				return false;
			}	
			
			
		});
		
	}
	
	
	
}
