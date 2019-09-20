package coreFunctions;

import antlr_parser.JavaParser;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.*;

public class ComplexityDueToControlStructures implements ComplexityByControlStructures {

    private File file;
    private Map<Integer, Integer> scoremap = new HashMap<>();
    @Override
    public int calculateComplexityForControlStructures(File file) throws IOException {

        int complexityTotal = 0;
        int nestingComplexity = 0;

        MethodDeclaration[] methods = JavaParser.getMethods(file);

        for (MethodDeclaration m : methods) {

            List<IfStatement> ifBlocks = JavaParser.getIfConditionsRecursively(m.getBody());
            List<ForStatement> forStatements = JavaParser.getForBlocksRecursively(m.getBody());
            List<WhileStatement> whileStatements = JavaParser.getWhileBlocksRecursively(m.getBody());
            List<DoStatement> doWhileStatements = JavaParser.getDoWhileBlocks(m.getBody());


            for (Object statement : m.getBody().statements()) {
                Statement s = (Statement) statement;
                if(s instanceof IfStatement ){
                    complexityTotal += 1;
                    scoremap.put(JavaParser.getLineNumber(s, file), complexityTotal);
                    List<String> conditionList = JavaParser.getOperatorsInsideIfCondition((IfStatement) s);
                    if (conditionList.contains("==")) {
                        conditionList.remove("==");
                    } else if (conditionList.contains("&&") || conditionList.contains("||") || conditionList.contains("|") || conditionList.contains("&")) {
                        for (String condition : conditionList) {
                            complexityTotal += 1;
                            scoremap.put(JavaParser.getLineNumber(s, file), complexityTotal);
                        }
                    }

                }else if(s instanceof ForStatement){
                    complexityTotal += 2;
                    scoremap.put(JavaParser.getLineNumber(s, file), complexityTotal);
                    List<String> conditionList = JavaParser.getOperatorsInForLoopCondition((ForStatement) s);
                    if (conditionList.contains("==")) {
                        conditionList.remove("==");
                    } else if (conditionList.contains("&&") || conditionList.contains("||") || conditionList.contains("|") || conditionList.contains("&")) {
                        for (String condition : conditionList) {
                            complexityTotal += 2;
                            scoremap.put(JavaParser.getLineNumber(s, file), complexityTotal);
                        }
                    }

                }else if (s instanceof WhileStatement){
                    complexityTotal += 2;
                    scoremap.put(JavaParser.getLineNumber(s, file), complexityTotal);
                    List<String> conditionList = JavaParser.getWhileLoopConditionOperators((WhileStatement) s);
                    if (conditionList.contains("==")) {
                        conditionList.remove("==");
                    } else if (conditionList.contains("&&") || conditionList.contains("||") || conditionList.contains("|") || conditionList.contains("&")) {
                        for (String condition : conditionList) {
                            complexityTotal += 2;
                            scoremap.put(JavaParser.getLineNumber(s, file), complexityTotal);
                        }
                    }


                }else if (s instanceof DoStatement){
                    complexityTotal += 2;
                    scoremap.put(JavaParser.getLineNumber(s, file), complexityTotal);
                    List<String> conditionList = JavaParser.getDoWhileLoopConditionOperators((DoStatement) s);
                    if (conditionList.contains("==")) {
                        conditionList.remove("==");
                    } else if (conditionList.contains("&&") || conditionList.contains("||") || conditionList.contains("|") || conditionList.contains("&")) {
                        for (String condition : conditionList) {
                            complexityTotal += 2;
                            scoremap.put(JavaParser.getLineNumber(s, file), complexityTotal);
                        }
                    }

                }else if(s instanceof TryStatement){
                    List<CatchClause> catchClauses = JavaParser.getCatchClauses((TryStatement) s);
                    for (CatchClause catchClause : catchClauses) {
                        complexityTotal += 1;
                        scoremap.put(JavaParser.getLineNumber(s, file), complexityTotal);
                    }
                }else if(s instanceof SwitchStatement){
                    List<SwitchCase> switchCases = JavaParser.getCaseStatements((SwitchStatement) s);
                    for (SwitchCase caseStatement : switchCases)
                        complexityTotal += 1;
                    scoremap.put(JavaParser.getLineNumber(s, file), complexityTotal);
                }

            }

            //nesting levels
            for (int i = 0; i < ifBlocks.size(); i++) {
                nestingComplexity += i;
                scoremap.put(JavaParser.getLineNumber(ifBlocks.get(i),file),nestingComplexity);
            }

            for (int i = 0; i < forStatements.size(); i++) {
                nestingComplexity += i;
                scoremap.put(JavaParser.getLineNumber(forStatements.get(i),file),nestingComplexity);
            }

            for (int i = 0; i < whileStatements.size(); i++) {
                nestingComplexity += i;
                scoremap.put(JavaParser.getLineNumber(whileStatements.get(i),file),nestingComplexity);
            }

            for (int i = 0; i < doWhileStatements.size(); i++) {
                nestingComplexity += i;
                scoremap.put(JavaParser.getLineNumber(doWhileStatements.get(i),file),nestingComplexity);
            }

            }

        return complexityTotal+nestingComplexity;
    }

   public Map<Integer,Integer> getCreatedScoreMap(File file) throws IOException {
       calculateComplexityForControlStructures(file);
        return scoremap;
   }
    @Override
    public int calculateComplexity() throws IOException {
        return 0;
    }
}
