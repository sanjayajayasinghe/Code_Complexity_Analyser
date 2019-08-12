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

package utilities;

import javax.tools.JavaCompiler;

/**
 * @author gisilk
 *
 */
public class TextUtils {

    public static String[] getWordsDevidedFromSpaces(String line) {

        return line.replaceAll("\\t", "").replaceAll("\\n", "").replaceAll("\\r", "").split(" ");


    }


}
