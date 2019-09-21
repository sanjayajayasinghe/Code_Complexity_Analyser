/**
 * Code Complexity Analayser for SPM Module (2019)
 * All Rights Recieved
 * <p>
 * This program is protected by copyright law and by international
 * conventions. All licensing, renting, lending or copying (including
 * for private use), and all other use of the program, which is not
 * expressively permitted by the Development Team, is a
 * violation of the rights of IFS. Such violations will be reported to the
 * appropriate authorities.
 * <p>
 * VIOLATIONS OF ANY COPYRIGHT IS PUNISHABLE BY LAW AND CAN LEAD
 * TO UP TO TWO YEARS OF IMPRISONMENT AND LIABILITY TO PAY DAMAGES.
 * <p>
 * gisilk
 * Aug 6, 2019
 */

package coreFunctions;

import antlr_parser.JavaParser;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import utilities.FileUtilities;
import utilities.TextUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author gisilk
 */
public class ComplexityDueToSize implements ComplexityBySize {

    Map<Integer, Integer> scoremap = new HashMap<>();
    private int Cs;
    private File codeFile;

    public ComplexityDueToSize(File codeFile) {
        this.codeFile = codeFile;
    }


    public void getComplexityAnalysisUsingParser() throws IOException {

        Map<Integer, String> availableClassNames = JavaParser.getAvailableClassNames(this.codeFile);
        for (Integer line : availableClassNames.keySet()) {
            updateScoreMap(line, 1);
        }

        FieldDeclaration[] fieldDeclarations = JavaParser.getClassAttributes(this.codeFile);
        for (FieldDeclaration fieldDeclaration : fieldDeclarations) {
            JavaParser.UsedItems usedAttributeNames = JavaParser.getUsedAttributeNames(fieldDeclaration);

            int lineNo = JavaParser.getLineNumberForAny(fieldDeclaration.getStartPosition(), this.codeFile);
            updateScoreMap(lineNo, usedAttributeNames.getUsedNumericValues().size() + usedAttributeNames.getUsedVariables().size());

        }

        MethodDeclaration[] methods = JavaParser.getMethods(this.codeFile);
        for (MethodDeclaration methodDeclaration : methods) {
            JavaParser.UsedItems parameterValues = JavaParser.getParameterValues(methodDeclaration);
            int lineNo = JavaParser.getLineNumberForAny(methodDeclaration.getStartPosition(), this.codeFile);
            updateScoreMap(lineNo, parameterValues.getUsedNumericValues().size() + parameterValues.getUsedVariables().size());
        }
    }


    private void updateScoreMap(int line, int score) {
        if (scoremap.containsKey(line)) {
            Integer oldScore = scoremap.get(line);
            scoremap.put(line, oldScore + score);
        } else {
            scoremap.put(line, score);
        }
    }

    public String getComplexityAnalysisResult() {

        StringBuilder result = new StringBuilder();

        int i = 1;
        try {
            for (String line : FileUtilities.convertToLisOfStrings(this.codeFile)) {
                int lineCs = 0;
                for (String word : TextUtils.getWordsDevidedFromSpaces(line)) {
                    if (isWordShouldBeConsidered(word)) {
                        lineCs += 1;
                    }
                    if (isSpecialKeywordsAvailable(word)) {
                        lineCs += 2;
                    }
                }
                if (lineCs > 0) {
                    result.append("[ Line Number " + i + "] : " + lineCs + "\n");
                }

                i++;
                this.Cs += lineCs;
            }

            result.append("\n\n");
            result.append("[Total Complexity Score ] : " + this.Cs);
            result.append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }

    @Override
    public Map<Integer, Integer> getCreatedScoreMap() throws IOException {

        int i = 1;
        try {
            for (String line : FileUtilities.convertToLisOfStrings(this.codeFile)) {
                int lineCs = 0;
                for (String word : TextUtils.getWordsDevidedFromSpaces(line)) {
                    if(!word.startsWith("//")) {
                        if (isWordShouldBeConsidered(word)) {
                            lineCs += 1;
                            updateScoreMap(i, lineCs);
                        }
                        if (isSpecialKeywordsAvailable(word)) {
                            lineCs += 2;
                            updateScoreMap(i, lineCs);
                        }
                    }
                }
                i++;
                this.Cs += lineCs;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return scoremap;
    }


    @Override
    public boolean isArithmeticOperatorAvailable(String word) {
        if (codeFile.getName().endsWith(".java")) {

            for (String s : JavaKeywords.ARITHMETIC_OPERATORS) {
                if (word.contains(s)) {
                    return true;
                }
            }

        }
        return false;
    }

    @Override
    public boolean isRelationalOperatorAvailable(String word) {
        if (codeFile.getName().endsWith(".java")) {


            for (String s : JavaKeywords.RELATIONAL_OPERATORS) {
                if (word.contains(s)) {
                    return true;
                }
            }

        }
        return false;
    }

    @Override
    public boolean isLogicalOperatorAvailable(String word) {
        if (codeFile.getName().endsWith(".java")) {


            for (String s : JavaKeywords.LOGICAL_OPERATORS) {
                if (word.contains(s)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean isBitwiseOperatorAvailable(String word) {
        if (codeFile.getName().endsWith(".java")) {


            for (String s : JavaKeywords.BITWISE_OPERATORS) {
                if (word.contains(s)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean isMiscellaneousOperatorAvailable(String word) {
        if (codeFile.getName().endsWith(".java")) {

            for (String s : JavaKeywords.MISCELLANEOUS_OPERATORS) {
                if (word.contains(s)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean isAssignmentOperatorsAvailable(String word) {
        if (codeFile.getName().endsWith(".java")) {

            for (String s : JavaKeywords.ASSIGNMENT_OPERATORS) {
                if (word.contains(s)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean isKeywordsAvailable(String word) {
        if (codeFile.getName().endsWith(".java")) {
            for (String s : JavaKeywords.ALL_KEYWORDS) {
                if (word.contains(s)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean isManipulatorKeywordsAvailable(String word) {
        if (codeFile.getName().endsWith(".java")) {
            for (String s : JavaKeywords.MANIPULATOR_KEYWORDS) {
                if (word.contains(s)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean isTextWithinQuotes(String word) {
        return Pattern.compile("\"([^\"]*)\"").matcher(word).matches();
    }


    //two points
    @Override
    public boolean isSpecialKeywordsAvailable(String word) {
        if (codeFile.getName().endsWith(".java")) {
            for (String s : JavaKeywords.SPECIAL_KEYWORDS) {
                if (word.contains(s)) {
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public boolean isWordShouldBeConsidered(String word) {
        return isArithmeticOperatorAvailable(word)
                || isRelationalOperatorAvailable(word)
                || isLogicalOperatorAvailable(word)
                || isBitwiseOperatorAvailable(word)
                || isMiscellaneousOperatorAvailable(word)
                || isAssignmentOperatorsAvailable(word)
                || isKeywordsAvailable(word)
                || isManipulatorKeywordsAvailable(word)
                || isTextWithinQuotes(word);
    }

    public Map<Integer, Integer> getSizeComplexityMap() throws IOException {
        getCreatedScoreMap();
        getComplexityAnalysisUsingParser();

        return scoremap;
    }

    @Override
    public int calculateComplexity() throws IOException {

        return 1;

    }
}
