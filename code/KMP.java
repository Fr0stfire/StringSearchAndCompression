package code;

import java.util.ArrayList;
import java.util.List;

/**
 * A new KMP instance is created for every substring search performed. Both the
 * pattern and the text are passed to the constructor and the search method. You
 * could, for example, use the constructor to create the match table and the
 * search method to perform the search itself.
 */
public class KMP {

	public KMP(String pattern, String text) {
		// TODO maybe fill this in.
	}

	/**
	 * Perform KMP substring search on the given text with the given pattern.
	 *
	 * This should return the starting index of the first substring match if it
	 * exists, or -1 if it doesn't.
	 */
	public int search(String pattern, String text) {
		// TODO fill this in.
		return -1;
	}
	public int KMPSearch(String pattern, String text, int[] partialMatchTable){

		int patternPos = 0;// Position of current character in the pattern (i)
		int currentMatchIndex = 0;// start of the current match in the text (k)
		int textLength = text.length();
		int n = currentMatchIndex + patternPos;

		while (n < textLength){

			if(pattern.substring(currentMatchIndex,currentMatchIndex).equals(text.substring(n,n))){ //Matched substrings

				currentMatchIndex++;

				if(patternPos == textLength){ // found the pattern

					return currentMatchIndex;
				}
			}
			else if(partialMatchTable[patternPos] == -1){ //mismatch no self overlap

				patternPos = 0;

				currentMatchIndex = n + 1;
			}
			else{ // mismatched,  self overlap

				currentMatchIndex = n - partialMatchTable[patternPos]; // match position jumps forward

				patternPos = partialMatchTable[patternPos];
			}
		}


		return -1; // failed to find the pattern in the text
	}
	public int[] buildTable(String text){
		int[] table = new int[text.length()];
		table[0] = -1;
		table[1] = 0;
		int j = 0;
		int pos = 2;

		while(pos < text.length()){
			if(text.substring(pos-1,pos-1).equals(text.substring(j,j))){
				table[pos] = j + 1;
				pos++;
				j++;
			}
			else if(j > 0){
				j = table[j];
			}
			else{
				table[pos] = 0;
				pos++;
			}

		}
		return table;
	}


	/*
	/ @parameter
	*/
	public int bruteforce(String pattern, String text){

		int patternLength = pattern.length();
		int textLength = text.length();
		boolean found = false;

		for(int i = 0;i < textLength - patternLength; i++){
			found = true;

			for(int j = 0; j < patternLength - 1; j++){
				if(!pattern.substring(j,j).equals(text.substring(i+j))){
					found = false;
					break;
				}
			}
			if(found){
				return i;
			}
		}
		return -1;
	}
}
