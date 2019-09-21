package coreFunctions;

import antlr_parser.JavaParser;
import org.eclipse.jdt.core.dom.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComplexityDueToControlStructures implements ComplexityByControlStructures {


    private Map<Integer, Integer> scoremap = new HashMap<>();

    @Override
    public int calculateComplexityForControlStructures(File file) throws IOException {

        MethodDeclaration[] methods = JavaParser.getMethods(file);
        for (MethodDeclaration m : methods) {
            List<SwitchStatement> switchBlocks = JavaParser.getSwitchBlocks(m.getBody());
            for (SwitchStatement st : switchBlocks) {
                List<SwitchCase> caseStatements = JavaParser.getCaseStatements(st);
                int lineNo = JavaParser.getLineNumber(st, file);
                updateScoreMap(lineNo + 1, caseStatements.size());
            }


            for (IfStatement ifs : JavaParser.getIfConditionsRecursively(m.getBody())) {
                checks((Block) ifs.getThenStatement(), file);
                checkNestingLevels(ifs, file);
            }
            for (WhileStatement whi : JavaParser.getWhileBlocksRecursively(m.getBody())) {
                checks((Block) whi.getBody(), file);
                checkNestingLevels(whi, file);
            }
            for (DoStatement doSt : JavaParser.getDoWhileBlocksRecursively(m.getBody())) {
                checks((Block) doSt.getBody(), file);
                checkNestingLevels(doSt, file);
            }
            for (ForStatement forSt : JavaParser.getForBlocksRecursively(m.getBody())) {
                checks((Block) forSt.getBody(), file);
                checkNestingLevels(forSt, file);
            }
        }
        return 0;
    }

    private void checks(Block body, File file) {
        for (WhileStatement whi : JavaParser.getWhileBlocksRecursively(body)) {
            checkNestingLevels(whi, file);
        }
        for (DoStatement doSt : JavaParser.getDoWhileBlocksRecursively(body)) {
            checkNestingLevels(doSt, file);

        }
        for (ForStatement forSt : JavaParser.getForBlocksRecursively(body)) {
            checkNestingLevels(forSt, file);
            checks((Block) forSt.getBody(), file);
        }
        for (IfStatement ifSt : JavaParser.getIfConditionsRecursively(body)) {
            checkNestingLevels(ifSt, file);
        }
    }

    public void checkNestingLevels(Statement statement, File file) {

        int score = 1;
        int lineNumber = JavaParser.getLineNumber(statement, file);

        ASTNode parent = statement.getParent();
        while (parent != null && parent instanceof Statement) {
            if (isControlStructre((Statement) parent)) {
                score++;//TODO CHECK SCORE if while for
            }
            parent = parent.getParent();

        }

        updateScoreMap(lineNumber + 1, score + getConditionsScore(statement));
    }

    private int getConditionsScore(Statement statement) {


        List<String> op = null;
        int score = 0;

        if (statement instanceof IfStatement) {
            op = JavaParser.getOperatorsInsideIfCondition((IfStatement) statement);
            for (String s : op) {
                if (Arrays.asList(JavaKeywords.CONDITION_OPERATORS).contains(s)) {
                    score++;
                }
            }
        } else {
            if (statement instanceof WhileStatement) {
                op = JavaParser.getWhileLoopConditionOperators((WhileStatement) statement);
            } else if (statement instanceof ForStatement) {
                op = JavaParser.getOperatorsInForLoopCondition((ForStatement) statement);
            } else if (statement instanceof DoStatement) {
                op = JavaParser.getDoWhileLoopConditionOperators((DoStatement) statement);
            }
            for (String s : op) {
                if (Arrays.asList(JavaKeywords.CONDITION_OPERATORS).contains(s)) {
                    score += 2;
                }
            }
        }

        return score;
    }


    private boolean isControlStructre(Statement st) {

        return st instanceof IfStatement
                || st instanceof WhileStatement
                || st instanceof ForStatement
                || st instanceof DoStatement
                || st instanceof SwitchStatement;

    }

    private void updateScoreMap(int line, int score) {
        if (scoremap.containsKey(line)) {
            Integer oldScore = scoremap.get(line);
            scoremap.put(line, oldScore + score);
        } else {
            scoremap.put(line, score);
        }
    }

    @Override
    public int calculateComplexity() throws IOException {
        return 0;
    }
}
