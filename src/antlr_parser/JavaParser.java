package antlr_parser;

import org.eclipse.jdt.core.dom.*;
import utilities.FileUtilities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JavaParser {




    public static List<String> getImplementedInterfaceNames(File file) throws IOException {
        List<String> superInterfaces = new ArrayList<>();
        List parse = parse(file);
        TypeDeclaration p = (TypeDeclaration) parse.get(0);
        if (p.superInterfaceTypes().size() > 0) {
            for (Object interf : p.superInterfaceTypes()) {
                superInterfaces.add(interf.toString());
            }
        }
        return superInterfaces;
    }

    public static String getExtendedClassName(File file) throws IOException {
        List parse = parse(file);
        TypeDeclaration p = (TypeDeclaration) parse.get(0);
        if (p.getSuperclassType() != null) {
            return p.getSuperclassType().toString();
        }

        return "";
    }

    public static List<String> getAvailableClassNames(File file) throws IOException{

    	List<String> classNames = new ArrayList<>();
    	List parse = parse(file);
    	for(Object p : parse){
    		TypeDeclaration type = (TypeDeclaration) p;
			classNames.add(type.getName().toString());
		}
		return classNames;
	}

    public static MethodDeclaration[] getMethods(File file) throws IOException {
        List parse = parse(file);
        TypeDeclaration p = (TypeDeclaration) parse.get(0);
        return p.getMethods();
    }

    public static FieldDeclaration[] getClassAttributes(File file) throws IOException{
		List parse = parse(file);
		TypeDeclaration p = (TypeDeclaration) parse.get(0);
		return p.getFields();
	}

	public static List<String> getAttributeModifiers(FieldDeclaration field){
    	List<String> modifierList = new ArrayList<>();
    	for (Object ob : field.modifiers()){
    		modifierList.add(ob.toString());
		}
    	return modifierList;
	}

	private static List<String> getInnerOperators(InfixExpression exp){
        List<String> op = new ArrayList<>();        
        if(exp.getLeftOperand() instanceof InfixExpression){
            InfixExpression leftOperand = (InfixExpression) exp.getLeftOperand();
            op.add(leftOperand.getOperator().toString());
            op.addAll(getInnerOperators(leftOperand));
        }

        if(exp.getRightOperand() instanceof  InfixExpression){
            InfixExpression rightOperand = (InfixExpression)exp.getRightOperand();
            op.add(rightOperand.getOperator().toString());
            op.addAll(getInnerOperators(rightOperand));
        }
        return op;
    }
	
	public static List<String> getOperatorsInsideIfCondition(IfStatement ifStatement){

        List<String> operators = new ArrayList<>();
        InfixExpression expression = (InfixExpression) ifStatement.getExpression();
        operators.add(expression.getOperator().toString());
        operators.addAll(getInnerOperators(expression));
        return operators;
    }

	public static String getAttributeDataType(FieldDeclaration field){
    	return field.getType().toString();
	}

	public static List<IfStatement> getInnerIfStatements(IfStatement ifStatement){
        List<IfStatement> ifStatements = new ArrayList<>() ;
        Block thenStatement = (Block) ((IfStatement) ifStatement).getThenStatement();
        for(Object thenstate : thenStatement.statements()){
            if(thenstate instanceof IfStatement){
                ifStatements.add((IfStatement) thenstate);
                ifStatements.addAll(getInnerIfStatements((IfStatement) thenstate));
            }
        }
      return ifStatements;
    }

	public static List<IfStatement> getIfConditionsRecursively(MethodDeclaration method){
        List<IfStatement> ifStatements = new ArrayList<>() ;
        List statements = method.getBody().statements();
        for(Object statement : statements){
            Statement s = (Statement) statement;
            if(s instanceof IfStatement){
                ifStatements.add((IfStatement) s);
                ifStatements.addAll(getInnerIfStatements((IfStatement) s));
            }
        }
        return ifStatements;
    }

	public static List<IfStatement> getIfConditions(MethodDeclaration method){
        List<IfStatement> ifStatements = new ArrayList<>() ;
        List statements = method.getBody().statements();
        for(Object st : statements){
            Statement s = (Statement) st;
            if(s instanceof IfStatement){
                ifStatements.add((IfStatement) s);
            }
        }
        return ifStatements;
    }


    public static List<String> getMrthodModifiers(MethodDeclaration method) {
        List<String> modifierList = new ArrayList<>();
        for (Object ob : method.modifiers()) {
            modifierList.add(ob.toString());
        }
        return modifierList;
    }

    public static List parse(File file) throws IOException {
        ASTParser parser = ASTParser.newParser(AST.JLS3);
        char[] fileContent = FileUtilities.getFileContent(file).toCharArray();
        parser.setSource(fileContent);
        CompilationUnit cu = (CompilationUnit) parser.createAST(null);
        return cu.types();

    }

    public static boolean isInterface(File file) throws IOException {
        List parse = parse(file);
        TypeDeclaration p = (TypeDeclaration) parse.get(0);
        return p.isInterface();
    }

    public static List<String> getFileModifier(File file) throws IOException {
        List<String> modifierList = new ArrayList<>();
        List parse = parse(file);
        TypeDeclaration p = (TypeDeclaration) parse.get(0);
        final List modifiers = p.modifiers();
        for (Object m : modifiers) {
            modifierList.add(m.toString());
        }
        return modifierList;
    }

}
