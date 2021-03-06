package antlr_parser;

import org.eclipse.jdt.core.dom.*;
import utilities.FileUtilities;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class JavaParser {

    public static UsedItems getParameterValues(MethodDeclaration method) {
        UsedItems usedItems = new UsedItems();
        List parameters = method.parameters();
        for (Object ob : parameters) {
            if (ob instanceof SingleVariableDeclaration) {
                String name = ((SingleVariableDeclaration) ob).getName().toString();
                String type = ((SingleVariableDeclaration) ob).getType().toString();
                usedItems.getUsedVariables().add(name);
                usedItems.getUsedNumericValues().add(type);

            }
        }
        return usedItems;
    }

    public static UsedItems getUsedAttributeNames(FieldDeclaration fieldDeclaration) {
        UsedItems usedItems = new UsedItems();
        List fragments = fieldDeclaration.fragments();

        for (Object vdf : fragments) {
            if (vdf instanceof VariableDeclarationFragment) {
                String variableName = ((VariableDeclarationFragment) vdf).getName().toString();
                usedItems.getUsedVariables().add(variableName);
                Expression initializer = ((VariableDeclarationFragment) vdf).getInitializer();
                if (initializer != null) {
                    if (initializer instanceof NumberLiteral) {
                        usedItems.getUsedNumericValues().add(((NumberLiteral) initializer).getToken());
                    }
                    if (initializer instanceof InfixExpression) {
                        usedItems.getUsedVariables().addAll(getInnerVariables((InfixExpression) initializer));
                        usedItems.getUsedNumericValues().addAll(getInnerNumerals((InfixExpression) initializer));
                    }
                }
            }
        }

        return usedItems;
    }

    public static UsedItems getUsedVariableNames(Statement statement) {

        UsedItems usedItems = new UsedItems();

        if (statement instanceof VariableDeclarationStatement) {
            List fragments = ((VariableDeclarationStatement) statement).fragments();
            for (Object vdf : fragments) {
                if (vdf instanceof VariableDeclarationFragment) {
                    String variableName = ((VariableDeclarationFragment) vdf).getName().toString();
                    usedItems.getUsedVariables().add(variableName);
                    Expression initializer = ((VariableDeclarationFragment) vdf).getInitializer();
                    if (initializer != null) {
                        if (initializer instanceof NumberLiteral) {
                            usedItems.getUsedNumericValues().add(((NumberLiteral) initializer).getToken());
                        }
                        if (initializer instanceof InfixExpression) {
                            usedItems.getUsedVariables().addAll(getInnerVariables((InfixExpression) initializer));
                            usedItems.getUsedNumericValues().addAll(getInnerNumerals((InfixExpression) initializer));
                        }
                    }
                }
            }
        } else if (statement instanceof ExpressionStatement) {
            Expression expression = ((ExpressionStatement) statement).getExpression();
            if (expression instanceof Assignment) {
                usedItems.getUsedNumericValues().addAll(getInnerNumeralsForAssignement((Assignment) expression));
                usedItems.getUsedVariables().addAll(getInnerVariablesForAssignement((Assignment) expression));
            }

        } else if (statement instanceof IfStatement) {
            Expression expression = ((IfStatement) statement).getExpression();
            if (expression instanceof InfixExpression) {
                usedItems.getUsedVariables().addAll(getInnerVariables((InfixExpression) expression));
                usedItems.getUsedNumericValues().addAll(getInnerNumerals((InfixExpression) expression));
            }
        } else if (statement instanceof WhileStatement) {
            Expression expression = ((WhileStatement) statement).getExpression();
            if (expression instanceof InfixExpression) {
                usedItems.getUsedVariables().addAll(getInnerVariables((InfixExpression) expression));
                usedItems.getUsedNumericValues().addAll(getInnerNumerals((InfixExpression) expression));
            }
        } else if (statement instanceof ForStatement) {
            Expression expression = ((ForStatement) statement).getExpression();
            if (expression instanceof InfixExpression) {
                usedItems.getUsedVariables().addAll(getInnerVariables((InfixExpression) expression));
                usedItems.getUsedNumericValues().addAll(getInnerNumerals((InfixExpression) expression));
            }
        } else if (statement instanceof DoStatement) {
            Expression expression = ((DoStatement) statement).getExpression();
            if (expression instanceof InfixExpression) {
                usedItems.getUsedVariables().addAll(getInnerVariables((InfixExpression) expression));
                usedItems.getUsedNumericValues().addAll(getInnerNumerals((InfixExpression) expression));
            }
        }
        return usedItems;
    }

    private static List<String> getInnerNumeralsForAssignement(Assignment exp) {
        List<String> op = new ArrayList<>();
        if (exp.getLeftHandSide() instanceof InfixExpression) {
            InfixExpression leftOperand = (InfixExpression) exp.getLeftHandSide();
            op.addAll(getInnerNumerals(leftOperand));
        } else if (exp.getLeftHandSide() instanceof NumberLiteral) {
            op.add(exp.getLeftHandSide().toString());
        }

        if (exp.getRightHandSide() instanceof InfixExpression) {
            InfixExpression rightOperand = (InfixExpression) exp.getRightHandSide();
            op.addAll(getInnerNumerals(rightOperand));
        } else if (exp.getRightHandSide() instanceof NumberLiteral) {
            op.add(exp.getRightHandSide().toString());
        }
        return op;
    }

    private static List<String> getInnerVariablesForAssignement(Assignment exp) {
        List<String> op = new ArrayList<>();
        if (exp.getLeftHandSide() instanceof InfixExpression) {
            InfixExpression leftOperand = (InfixExpression) exp.getLeftHandSide();
            op.addAll(getInnerVariables(leftOperand));
        } else if (exp.getLeftHandSide() instanceof SimpleName) {
            op.add(exp.getLeftHandSide().toString());
        }

        if (exp.getRightHandSide() instanceof InfixExpression) {
            InfixExpression rightOperand = (InfixExpression) exp.getRightHandSide();
            op.addAll(getInnerVariables(rightOperand));
        } else if (exp.getRightHandSide() instanceof SimpleName) {
            op.add(exp.getRightHandSide().toString());
        }
        return op;
    }

    private static List<String> getInnerNumerals(InfixExpression exp) {
        List<String> op = new ArrayList<>();
        if (exp.getLeftOperand() instanceof InfixExpression) {
            InfixExpression leftOperand = (InfixExpression) exp.getLeftOperand();
            op.addAll(getInnerNumerals(leftOperand));
        } else if (exp.getLeftOperand() instanceof NumberLiteral) {
            op.add(exp.getLeftOperand().toString());
        }

        if (exp.getRightOperand() instanceof InfixExpression) {
            InfixExpression rightOperand = (InfixExpression) exp.getRightOperand();
            op.addAll(getInnerNumerals(rightOperand));
        } else if (exp.getRightOperand() instanceof NumberLiteral) {
            op.add(exp.getRightOperand().toString());
        }

        if (exp.hasExtendedOperands()) {
            List list = exp.extendedOperands();
            for (Object o : list) {
                if (o instanceof InfixExpression) {
                    op.addAll(getInnerNumerals((InfixExpression) o));
                } else if (o instanceof NumberLiteral) {
                    op.add(o.toString());
                }
            }
        }

        return op;
    }

    private static List<String> getInnerVariables(InfixExpression exp) {
        List<String> op = new ArrayList<>();
        if (exp.getLeftOperand() instanceof InfixExpression) {
            InfixExpression leftOperand = (InfixExpression) exp.getLeftOperand();
            op.addAll(getInnerVariables(leftOperand));
        } else if (exp.getLeftOperand() instanceof SimpleName) {
            op.add(exp.getLeftOperand().toString());
        }

        if (exp.getRightOperand() instanceof InfixExpression) {
            InfixExpression rightOperand = (InfixExpression) exp.getRightOperand();
            op.addAll(getInnerVariables(rightOperand));
        } else if (exp.getRightOperand() instanceof SimpleName) {
            op.add(exp.getRightOperand().toString());
        }

        if (exp.hasExtendedOperands()) {
            List list = exp.extendedOperands();
            for (Object o : list) {
                if (o instanceof InfixExpression) {
                    op.addAll(getInnerVariables((InfixExpression) o));
                } else if (o instanceof SimpleName) {
                    op.add(o.toString());
                }
            }
        }
        return op;
    }

    public static List<String> getOperators(Block body) {

        List<String> operators = new ArrayList<>();
        List<Statement> statements = body.statements();
        for (Statement st : statements) {
            if (st instanceof VariableDeclarationStatement) {
                List fragments = ((VariableDeclarationStatement) st).fragments();
                for (Object vs : fragments) {
                    VariableDeclarationFragment variableDeclarationFragment = (VariableDeclarationFragment) vs;
                    Expression initializer = variableDeclarationFragment.getInitializer();
                    if (initializer != null) {
                        if (initializer instanceof InfixExpression) {
                            String operator = ((InfixExpression) initializer).getOperator().toString();
                            operators.add(operator);
                            operators.addAll(getInnerOperators((InfixExpression) initializer));
                        }
                    }
                }
            }
            if (st instanceof ExpressionStatement) {
                Expression expression = ((ExpressionStatement) st).getExpression();
                if (expression != null) {
                    if (expression instanceof PostfixExpression) {
                        String operator = ((PostfixExpression) expression).getOperator().toString();
                        operators.add(operator);
                    }
                    if (expression instanceof PrefixExpression) {
                        String operator = ((PrefixExpression) expression).getOperator().toString();
                        operators.add(operator);
                    }
                    if (expression instanceof Assignment) {
                        String operator = ((Assignment) expression).getOperator().toString();
                        operators.add(operator);
                    }
                }

            }
        }
        return operators;
    }

    public static Map<String, Integer> getLinesInside(MethodDeclaration method, File file) {

        Map<String, Integer> lines = new HashMap<>();

        int start = getLineNumberForAny(method.getStartPosition(), file);
        int end = getLineNumberForAny(method.getStartPosition() + method.getLength(), file) - 2;

        lines.put("start", start);
        lines.put("end", end);

        return lines;
    }

    public static int getNumberOfLinesInBlock(Block body) {

        String[] split = body.toString().replaceAll("\\{", "").replaceAll("\\}", "").trim().split("\n");
        return split.length;
    }

    public static boolean isRecursionAvailable(MethodDeclaration method) {
        String methodName = method.getName().toString();
        int noOfParameters = method.parameters().size();

        final List<MethodInvocation> methodInvocations = new ArrayList<>();
        method.getBody().accept(new ASTVisitor() {
            @Override
            public boolean visit(MethodInvocation node) {
                methodInvocations.add(node);
                return super.visit(node);
            }
        });
        for (MethodInvocation methodInvocation : methodInvocations) {
            if (methodInvocation.getName().toString().equalsIgnoreCase(methodName)) {
                if (methodInvocation.arguments().size() == noOfParameters) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int getLineNumberForAny(int startPosition, File file) {
        ASTParser parser = ASTParser.newParser(AST.JLS3);
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        String fileText = FileUtilities.filesToString(file);
        parser.setSource(fileText.toCharArray());
        parser.setResolveBindings(true);
        CompilationUnit compilationUnit = (CompilationUnit) parser.createAST(null);
        return compilationUnit.getLineNumber(startPosition);
    }

    public static int getLineNumber(Statement statement, File sourceFile) {
        ASTParser parser = ASTParser.newParser(AST.JLS3);
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        String fileText = FileUtilities.filesToString(sourceFile);
        parser.setSource(fileText.toCharArray());
        parser.setResolveBindings(true);
        CompilationUnit compilationUnit = (CompilationUnit) parser.createAST(null);
        int lineNumber = compilationUnit.getLineNumber(statement.getStartPosition());
        return lineNumber - 1;
    }

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

    public static Map<Integer, String> getAvailableClassNames(File file) throws IOException {

        Map<Integer, String> classNames = new HashMap<>();
        List parse = parse(file);
        for (Object p : parse) {
            TypeDeclaration type = (TypeDeclaration) p;
            classNames.put(getLineNumberForAny(type.getStartPosition(), file), type.getName().toString());
        }
        return classNames;
    }

    public static MethodDeclaration[] getMethods(File file) throws IOException {
        List parse = parse(file);
        TypeDeclaration p = (TypeDeclaration) parse.get(0);
        return p.getMethods();
    }

    public static FieldDeclaration[] getClassAttributes(File file) throws IOException {

        List<FieldDeclaration> fd = new ArrayList<>();
        List parse = parse(file);
        for (Object p : parse) {
            if (p instanceof TypeDeclaration) {
                fd.addAll(Arrays.asList(((TypeDeclaration) p).getFields()));
            }
        }

        return fd.toArray(new FieldDeclaration[0]);
    }

    public static List<String> getAttributeModifiers(FieldDeclaration field) {
        List<String> modifierList = new ArrayList<>();
        for (Object ob : field.modifiers()) {
            modifierList.add(ob.toString());
        }
        return modifierList;
    }

    public static List<String> getOperatorsInForLoopCondition(ForStatement forStatement) {
        List<String> operators = new ArrayList<>();
        if (forStatement.getExpression() != null) {
            InfixExpression expression = (InfixExpression) forStatement.getExpression();
            operators.add(expression.getOperator().toString());
            operators.addAll(getInnerOperators(expression));
        }
        return operators;
    }

    private static List<String> getInnerOperators(InfixExpression exp) {
        List<String> op = new ArrayList<>();
        if (exp.getLeftOperand() instanceof InfixExpression) {
            InfixExpression leftOperand = (InfixExpression) exp.getLeftOperand();
            op.add(leftOperand.getOperator().toString());
            op.addAll(getInnerOperators(leftOperand));
        }

        if (exp.getRightOperand() instanceof InfixExpression) {
            InfixExpression rightOperand = (InfixExpression) exp.getRightOperand();
            op.add(rightOperand.getOperator().toString());
            op.addAll(getInnerOperators(rightOperand));
        }
        return op;
    }

    public static List<String> getOperatorsInsideIfCondition(IfStatement ifStatement) {

        List<String> operators = new ArrayList<>();
        InfixExpression expression = (InfixExpression) ifStatement.getExpression();
        operators.add(expression.getOperator().toString());
        operators.addAll(getInnerOperators(expression));
        return operators;
    }

    public static String getAttributeDataType(FieldDeclaration field) {
        return field.getType().toString();
    }

    private static List<IfStatement> getInnerIfStatements(IfStatement ifStatement) {
        List<IfStatement> ifStatements = new ArrayList<>();
        Block thenStatement = (Block) ((IfStatement) ifStatement).getThenStatement();
        for (Object thenstate : thenStatement.statements()) {
            if (thenstate instanceof IfStatement) {
                ifStatements.add((IfStatement) thenstate);
                ifStatements.addAll(getInnerIfStatements((IfStatement) thenstate));
            }
        }
        return ifStatements;
    }

    public static List<ForStatement> getForBlocksRecursively(Block body) {
        List<ForStatement> forStatements = new ArrayList<>();
        List statements = body.statements();
        for (Object statement : statements) {
            Statement s = (Statement) statement;
            if (s instanceof ForStatement) {
                forStatements.add((ForStatement) s);
                forStatements.addAll(getForBlocksRecursively((Block) ((ForStatement) s).getBody()));
            }
        }
        return forStatements;
    }

    public static List<WhileStatement> getWhileBlocksRecursively(Block body) {
        List<WhileStatement> whileStatements = new ArrayList<>();
        List statements = body.statements();
        for (Object statement : statements) {
            Statement s = (Statement) statement;
            if (s instanceof WhileStatement) {
                whileStatements.add((WhileStatement) s);
                whileStatements.addAll(getWhileBlocksRecursively((Block) ((WhileStatement) s).getBody()));
            }
        }
        return whileStatements;
    }

    public static List<DoStatement> getDoWhileBlocksRecursively(Block body) {
        List<DoStatement> doStatements = new ArrayList<>();
        List statements = body.statements();
        for (Object statement : statements) {
            Statement s = (Statement) statement;
            if (s instanceof DoStatement) {
                doStatements.add((DoStatement) s);
                doStatements.addAll(getDoWhileBlocksRecursively((Block) ((DoStatement) s).getBody()));
            }
        }
        return doStatements;
    }


    public static List<IfStatement> getIfConditionsRecursively(Block body) {
        List<IfStatement> ifStatements = new ArrayList<>();
        List statements = body.statements();
        for (Object statement : statements) {
            Statement s = (Statement) statement;
            if (s instanceof IfStatement) {
                ifStatements.add((IfStatement) s);
                ifStatements.addAll(getIfConditionsRecursively((Block) ((IfStatement) s).getThenStatement()));
            }
        }
        return ifStatements;
    }

    public static List<IfStatement> getIfConditions(Block body) {
        List<IfStatement> ifStatements = new ArrayList<>();
        List statements = body.statements();
        for (Object st : statements) {
            Statement s = (Statement) st;
            if (s instanceof IfStatement) {
                ifStatements.add((IfStatement) s);
            }
        }
        return ifStatements;
    }

    public static List<SwitchStatement> getSwitchBlocks(Block body) {
        List<SwitchStatement> switchStatementList = new ArrayList<>();
        List statements = body.statements();
        for (Object st : statements) {
            Statement s = (Statement) st;
            if (s instanceof SwitchStatement) {
                switchStatementList.add((SwitchStatement) s);
            }
        }
        return switchStatementList;
    }

    public static List<String> getWhileLoopConditionOperators(WhileStatement whileStatement) {
        List<String> operators = new ArrayList<>();
        if (whileStatement.getExpression() != null) {
            InfixExpression expression = (InfixExpression) whileStatement.getExpression();
            operators.add(expression.getOperator().toString());
            operators.addAll(getInnerOperators(expression));
        }
        return operators;
    }

    public static List<TryStatement> getTryBlocks(Block body) {
        List<TryStatement> tryStatementList = new ArrayList<>();
        List statements = body.statements();
        for (Object st : statements) {
            Statement s = (Statement) st;
            if (s instanceof TryStatement) {
                tryStatementList.add((TryStatement) s);
            }
        }
        return tryStatementList;
    }

    public static List<CatchClause> getCatchClauses(TryStatement tryStatement) {
        return tryStatement.catchClauses();

    }

    public static List<String> getDoWhileLoopConditionOperators(DoStatement doStatement) {
        List<String> operators = new ArrayList<>();
        if (doStatement.getExpression() != null) {
            InfixExpression expression = (InfixExpression) doStatement.getExpression();
            operators.add(expression.getOperator().toString());
            operators.addAll(getInnerOperators(expression));
        }
        return operators;
    }

    public static List<WhileStatement> getWhileLoopBlocks(Block body) {
        List<WhileStatement> whileLoopList = new ArrayList<>();
        List statements = body.statements();
        for (Object st : statements) {
            Statement s = (Statement) st;
            if (s instanceof WhileStatement) {
                whileLoopList.add((WhileStatement) s);
            }
        }
        return whileLoopList;
    }

    public static List<DoStatement> getDoWhileBlocks(Block body) {
        List<DoStatement> whileLoopList = new ArrayList<>();
        List statements = body.statements();
        for (Object st : statements) {
            Statement s = (Statement) st;
            if (s instanceof DoStatement) {
                whileLoopList.add((DoStatement) s);
            }
        }
        return whileLoopList;
    }

    public static List<ForStatement> getForLoopBlocks(Block body) {
        List<ForStatement> forLoops = new ArrayList<>();
        List statements = body.statements();
        for (Object st : statements) {
            Statement s = (Statement) st;
            if (s instanceof ForStatement) {
                forLoops.add((ForStatement) s);
            }
        }
        return forLoops;
    }


    public static List<SwitchCase> getCaseStatements(SwitchStatement switchStatement) {
        List<SwitchCase> caseList = new ArrayList<>();
        List statements = switchStatement.statements();
        for (Object ob : statements) {
            if (ob instanceof SwitchCase) {
                caseList.add((SwitchCase) ob);
            }
        }
        return caseList;
    }

    public static List<String> getMethodModifiers(MethodDeclaration method) {
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

    public static class UsedItems {
        List<String> usedVariables = new ArrayList<>();
        List<String> usedNumericValues = new ArrayList<>();

        public List<String> getUsedVariables() {
            return usedVariables;
        }

        public List<String> getUsedNumericValues() {
            return usedNumericValues;
        }
    }

}
