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
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Statement;
import utilities.FileUtilities;
import utilities.TextUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author gisilk
 */
public class ComplexityDueToSize implements ComplexityBySize {

    private int Cs;
    private File codeFile;

    public ComplexityDueToSize(File codeFile) {
        this.codeFile = codeFile;
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
    public int calculateComplexity() throws IOException {

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
                i++;
                this.Cs += lineCs;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> classNames = JavaParser.getAvailableClassNames(this.codeFile);
        for (String className : classNames) {
            this.Cs += 1;
        }

        MethodDeclaration[] methodDeclarations = JavaParser.getMethods(this.codeFile);
        for (MethodDeclaration methodDeclaration : methodDeclarations) {
            this.Cs += 1;

            List statements = methodDeclaration.getBody().statements();
            for (Object st : statements) {
                JavaParser.UsedItems usedItems = JavaParser.getUsedVariableNames((Statement) st);

                for(String numericVariable:usedItems.getUsedNumericValues()){
                    this.Cs+=1;
                }
                for(String variable:usedItems.getUsedVariables()){
                    this.Cs+=1;
                }

            }

        }

        return this.Cs;
    }


    @Override
    public boolean isArithmeticOperatorAvailable(String word) {
        if (codeFile.getName().endsWith(".java")) {
            if (Arrays.asList(JavaKeywords.ARITHMETIC_OPERATORS).contains(word)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isRelationalOperatorAvailable(String word) {
        if (codeFile.getName().endsWith(".java")) {
            if (Arrays.asList(JavaKeywords.RELATIONAL_OPERATORS).contains(word)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isLogicalOperatorAvailable(String word) {
        if (codeFile.getName().endsWith(".java")) {
            if (Arrays.asList(JavaKeywords.LOGICAL_OPERATORS).contains(word)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isBitwiseOperatorAvailable(String word) {
        if (codeFile.getName().endsWith(".java")) {
            if (Arrays.asList(JavaKeywords.BITWISE_OPERATORS).contains(word)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isMiscellaneousOperatorAvailable(String word) {
        if (codeFile.getName().endsWith(".java")) {
            if (Arrays.asList(JavaKeywords.MISCELLANEOUS_OPERATORS).contains(word)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isAssignmentOperatorsAvailable(String word) {
        if (codeFile.getName().endsWith(".java")) {
            if (Arrays.asList(JavaKeywords.ASSIGNMENT_OPERATORS).contains(word)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isKeywordsAvailable(String word) {
        if (codeFile.getName().endsWith(".java")) {
            if (Arrays.asList(JavaKeywords.ALL_KEYWORDS).contains(word)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isManipulatorKeywordsAvailable(String word) {
        if (codeFile.getName().endsWith(".java")) {
            if (Arrays.asList(JavaKeywords.MANIPULATOR_KEYWORDS).contains(word)) {
                return true;
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
            if (Arrays.asList(JavaKeywords.SPECIAL_KEYWORDS).contains(word)) {
                return true;
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

}
