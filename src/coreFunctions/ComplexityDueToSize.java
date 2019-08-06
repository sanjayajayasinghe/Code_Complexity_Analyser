/**
 * Code Complexity Analayser for SPM Module (2019)
 * All Rights Recieved
 * 
 * This program is protected by copyright law and by international
 * conventions. All licensing, renting, lending or copying (including
 * for private use), and all other use of the program, which is not
 * expressively permitted by the Development Team, is a
 * violation of the rights of IFS. Such violations will be reported to the
 * appropriate authorities.
 * 
 * VIOLATIONS OF ANY COPYRIGHT IS PUNISHABLE BY LAW AND CAN LEAD
 * TO UP TO TWO YEARS OF IMPRISONMENT AND LIABILITY TO PAY DAMAGES.
 * 
 * gisilk
 * Aug 6, 2019
 */
 
package coreFunctions;

import java.io.File;
import java.util.Arrays;

/**
 * @author gisilk
 *
 */
public class ComplexityDueToSize implements ComplexityBySize{

	private int Cs;
	private File codeFile;
	
	public ComplexityDueToSize() {
		
	}
	
	@Override
	public int calculateComplexity(File file) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isArethmaticOperatorAvailable(String word) {		
		if(codeFile.getName().endsWith(".java")) {
			if(Arrays.asList(JavaKeywords.ARITHMETIC_OPERATORS).contains(word)) {
				return true;
			}
		}		
		return false;
	}

	@Override
	public boolean isRelationalOperatorAvailable(String word) {		
		if(codeFile.getName().endsWith(".java")) {
			if(Arrays.asList(JavaKeywords.RELATIONAL_OPERATORS).contains(word)) {
				return true;
			}
		}		
		return false;
	}

	@Override
	public boolean isLogicalOperatorAvailable(String word) {
		if(codeFile.getName().endsWith(".java")) {
			if(Arrays.asList(JavaKeywords.LOGICAL_OPERATORS).contains(word)) {
				return true;
			}
		}		
		return false;
	}

	@Override
	public boolean isAssignmentOperatorsAvailable(String word) {
		if(codeFile.getName().endsWith(".java")) {
			if(Arrays.asList(JavaKeywords.ASSIGNMENT_OPERATORS).contains(word)) {
				return true;
			}
		}		
		return false;
	}

	@Override
	public boolean isKeywordsAvailable(String word) {
		if(codeFile.getName().endsWith(".java")) {
			if(Arrays.asList(JavaKeywords.DATA_TYPES).contains(word)) {
				return true;
			}
			
			if(Arrays.asList(JavaKeywords.CONDITIONAL_KEYWORDS).contains(word)) {
				return true;
			}
			
			if(Arrays.asList(JavaKeywords.ITEARATIVE_KEYWORDS).contains(word)) {
				return true;
			}
		}		
		return false;
	}

	@Override
	public boolean isStringAvailable(String line) {
		// TODO Auto-generated method stub
		return false;
	}



}
