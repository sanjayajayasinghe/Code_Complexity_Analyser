package coreFunctions;

import antlr_parser.JavaParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.*;

public class ComplexityDueToControlStructures implements ComplexityByControlStructures {

    private File file;

    @Override
    public int calculateComplexityForControlStructuresForRecursiveConditions(File file) throws IOException {

        int complexityTotal=0;
        int nestingComplexity=0;

        MethodDeclaration[] methods = JavaParser.getMethods(file);

        for(MethodDeclaration m : methods) {
            List<IfStatement> ifBlocks = JavaParser.getIfConditionsRecursively(m.getBody());
           //need to get these recursively like if condition
            List<ForStatement> forStatements=JavaParser.getForBlocksRecursively(m.getBody());
            List<WhileStatement> whileStatements=JavaParser.getWhileBlocksRecursively(m.getBody());
            List<DoStatement> doWhileStatements=JavaParser.getDoWhileBlocks(m.getBody());


            List<TryStatement>  tryClauses=JavaParser.getTryBlocks(m.getBody());
            List<SwitchStatement> switchStatements=JavaParser.getSwitchBlocks(m.getBody());

            for(int i=0;i<ifBlocks.size();i++){
                nestingComplexity+=i;
            }

            for(int i=0;i<forStatements.size();i++){
                nestingComplexity+=i;
            }

            for(int i=0;i<whileStatements.size();i++){
                nestingComplexity+=i;
            }

            for(int i=0;i<doWhileStatements.size();i++){
                nestingComplexity+=i;
            }


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
            for(SwitchStatement switchStatement:switchStatements){
                List <SwitchCase> switchCases=JavaParser.getCaseStatements(switchStatement);
                for(SwitchCase caseStatement:switchCases)
                    complexityTotal+=1;
            }

        }



        return complexityTotal+nestingComplexity;

    }

    @Override
    public int calculateComplexity() {
        return 0;
    }


}
