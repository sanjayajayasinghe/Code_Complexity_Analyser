package coreFunctions;

import antlr_parser.JavaParser;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.eclipse.jdt.core.dom.*;

public class ComplexityDueToControlStructures implements ComplexityByControlStructures {

    private File file;

    @Override
    public int calculateComplexityForControlStructuresForIfBlockAndConditions(File file) throws IOException {
        int complexityTotal=-1;
        MethodDeclaration[] methods = JavaParser.getMethods(file);
        for(MethodDeclaration m : methods) {
            List<IfStatement> ifBlocks = JavaParser.getIfConditionsRecursively(m.getBody());
            //need to get these recursively like if condition
            List<ForStatement> forStatements=JavaParser.getForLoopBlocks(m.getBody());
            List<WhileStatement> whileStatements=JavaParser.getWhileLoopBlocks(m.getBody());
            List<DoStatement> doWhileStatements=JavaParser.getDoWhileBlocks(m.getBody());


            List<TryStatement>  tryClauses=JavaParser.getTryBlocks(m.getBody());
            List<SwitchStatement> switchStatments=JavaParser.getSwitchBlocks(m.getBody());


           //if
            for(IfStatement statement:ifBlocks){
                complexityTotal+=1;
                List<String> conditionList=JavaParser.getOperatorsInsideIfCondition(statement);
                if(conditionList.contains("==")){
                    conditionList.remove("==");
                }
                else if(conditionList.contains("&&")||conditionList.contains("||")||conditionList.contains("|")||conditionList.contains("&")){
                    for(String condition:conditionList){
                        complexityTotal+=1;
                    }
                }
            }

            //for
            for(ForStatement statement:forStatements){
                complexityTotal+=2;
                List<String> conditionList=JavaParser.getOperatorsInForLoopCondition(statement);
                if(conditionList.contains("==")){
                    conditionList.remove("==");
                }
                else if(conditionList.contains("&&")||conditionList.contains("||")||conditionList.contains("|")||conditionList.contains("&")){
                    for(String condition:conditionList){
                        complexityTotal+=2;
                    }
                }
            }

            //while
            for(WhileStatement statement:whileStatements){
                complexityTotal+=2;
                List<String> conditionList=JavaParser.getWhileLoopConditionOperators(statement);
                if(conditionList.contains("==")){
                    conditionList.remove("==");
                }
                else if(conditionList.contains("&&")||conditionList.contains("||")||conditionList.contains("|")||conditionList.contains("&")){
                    for(String condition:conditionList){
                        complexityTotal+=2;
                    }
                }
            }

            //dowhile
            for(DoStatement statement:doWhileStatements){
                complexityTotal+=2;
                List<String> conditionList=JavaParser.getDoWhileLoopConditionOperators(statement);
                if(conditionList.contains("==")){
                    conditionList.remove("==");
                }
                else if(conditionList.contains("&&")||conditionList.contains("||")||conditionList.contains("|")||conditionList.contains("&")){
                    for(String condition:conditionList){
                        complexityTotal+=2;
                    }
                }
            }

            //catch
            for(TryStatement tryStatement:tryClauses){
                List<CatchClause> catchClauses =JavaParser.getCatchClauses(tryStatement);
                for(CatchClause catchClause:catchClauses){
                    complexityTotal+=1;
                }
            }

            //switch
            for(SwitchStatement switchStatement:switchStatments){
                List <SwitchCase> switchcases=JavaParser.getCaseStatements(switchStatement);
                for(SwitchCase casestatement:switchcases)
                    complexityTotal+=1;
            }

        }



        return complexityTotal;

    }

    @Override
    public int calculateComplexity() {
        return 0;
    }


}