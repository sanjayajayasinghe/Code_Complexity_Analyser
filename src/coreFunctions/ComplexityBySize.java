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

import java.io.IOException;
import java.util.Map;

/**
 * @author gisilk
 *
 */
public interface ComplexityBySize extends CodeComplexity {


    //numeric
    Map<Integer, Integer> getCreatedScoreMap() throws IOException;

    boolean isArithmeticOperatorAvailable(String word);

    boolean isRelationalOperatorAvailable(String word);

    boolean isLogicalOperatorAvailable(String word);

    boolean isBitwiseOperatorAvailable(String word);

    boolean isMiscellaneousOperatorAvailable(String word);

    boolean isAssignmentOperatorsAvailable(String word);

    boolean isKeywordsAvailable(String word);

    boolean isManipulatorKeywordsAvailable(String word);

    boolean isTextWithinQuotes(String word);

    //two points
    boolean isSpecialKeywordsAvailable(String word);

    boolean isWordShouldBeConsidered(String word);


}
