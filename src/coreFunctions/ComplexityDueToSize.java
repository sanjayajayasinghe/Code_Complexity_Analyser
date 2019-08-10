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
import java.io.IOException;
import java.util.Arrays;

import utilities.FileUtils;
import utilities.TextUtils;

/**
 * @author gisilk
 *
 */
public class ComplexityDueToSize implements ComplexityBySize{

	private int Cs;
	private File codeFile;
	
	public ComplexityDueToSize(File codeFile) {
		this.codeFile = codeFile;
	}
	
	public String getComplexityAnalysisResult() {
		
		StringBuilder result = new StringBuilder();
		
		int i = 1;
		try {
			for(String line : FileUtils.convertToLisOfStrings(this.codeFile)) {
				int lineCs = 0;
				for(String word : TextUtils.getWordsDevidedFromSpaces(line)) {
					if(isWordShouldBeConsidered(word)) {
						lineCs += 1;
					}
				}
				if(lineCs > 0) {
					result.append("[ Line Number " + i + "] : " + lineCs +"\n");
				}
				
				i++;
				this.Cs += lineCs;
			}
			
			result.append("\n\n");
			result.append("[Total Complexity Score ] : " +this.Cs);
			result.append("\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result.toString();
	}
	
	@Override
	public int calculateComplexity() {
		
		int i = 1;
		try {
			for(String line : FileUtils.convertToLisOfStrings(this.codeFile)) {
				int lineCs = 0;
				for(String word : TextUtils.getWordsDevidedFromSpaces(line)) {
					if(isWordShouldBeConsidered(word)) {
						lineCs += 1;
					}
					if(isSpecialKeywordsAvailable(word)){
						lineCs += 2;
					}

				}
				System.out.println("Line Score : " + i + " : " + lineCs);
				i++;
				this.Cs += lineCs;
			}
			System.out.println("Total Cs : " + this.Cs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return 0;
	}
	
	

	@Override
	public boolean isArithmeticOperatorAvailable(String word) {
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
			
			if(Arrays.asList(JavaKeywords.ITERATIVE_KEYWORDS).contains(word)) {
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

	@Override
	public boolean isSpecialKeywordsAvailable(String word) {
		if(codeFile.getName().endsWith(".java")) {
			if(Arrays.asList(JavaKeywords.SPECIAL_KEYWORDS).contains(word)) {
				return true;
			}
		}
		return false;
	}



	@Override
	public boolean isWordShouldBeConsidered(String word) {
		return isArithmeticOperatorAvailable(word)
				|| isAssignmentOperatorsAvailable(word)
				|| isKeywordsAvailable(word)
				|| isLogicalOperatorAvailable(word)
				|| isRelationalOperatorAvailable(word);
				
	}



}
