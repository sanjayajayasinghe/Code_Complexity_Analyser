package antlr_parser;

import org.eclipse.jdt.core.dom.*;
import utilities.FileUtilities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JavaParser {

//	public static boolean isInterface(File file) throws IOException {
//		List parse = parse(file);
//		TypeDeclaration p = (TypeDeclaration) parse.get(0);
//
//	}

	public static List<String> getImplementedIntefaceNames(File file) throws IOException {
		List<String> superInterfaces = new ArrayList<>();
		List parse = parse(file);
		TypeDeclaration p = (TypeDeclaration) parse.get(0);
		if(p.superInterfaceTypes().size() > 0){
			for( Object interf : p.superInterfaceTypes()){
				superInterfaces.add(interf.toString());
			}
		}
		return superInterfaces;
	}

	public static String getExtendedClassName(File file) throws IOException {
		List parse = parse(file);
		TypeDeclaration p = (TypeDeclaration) parse.get(0);
		if(p.getSuperclassType() != null){
			 return p.getSuperclassType().toString();
		}

		return "";
	}

	public static MethodDeclaration[] getMethods(File file) throws IOException {
		List parse = parse(file);
		TypeDeclaration p = (TypeDeclaration) parse.get(0);
		MethodDeclaration[] methods = p.getMethods();
		return methods;
	}


	public static List parse(File file) throws IOException {
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		char[] fileContent = FileUtilities.getFileContent(file).toCharArray();
		parser.setSource(fileContent);
		CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		return cu.types();

	}

}
