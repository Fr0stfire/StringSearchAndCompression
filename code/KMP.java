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

	}

	/**
	 * Perform KMP substring search on the given text with the given pattern.
	 *
	 * This should return the starting index of the first substring match if it
	 * exists, or -1 if it doesn't.
	 */
	public int search(String pattern, String text) {

		Long tableTime = System.currentTimeMillis();
		int[] table = buildTable(text);
		tableTime = (System.currentTimeMillis() - tableTime);
		System.out.println("\nTime taken to perform buildTable: " + tableTime);

		Long kmpTime = System.currentTimeMillis();
		int kmp = KMPSearch(pattern, text, table);
		kmpTime = (System.currentTimeMillis() - kmpTime);
		System.out.println("Time taken to perform KMP: " + kmpTime);

		Long bruteTime = System.currentTimeMillis();
		int bruteForce = bruteForce(pattern,text);
		bruteTime = System.currentTimeMillis() - bruteTime;
		System.out.println("Time taken to perform Bruteforce: " + bruteTime);

		assert(bruteForce == kmp);
		return kmp;

	}
	public int KMPSearch(String pattern, String text, int[] partialMatchTable){
		if(pattern.length() == 0 || text.length() == 0|| partialMatchTable == null){
			return -1;
		}

		int j = 0;
		int k = 0;
		int textLength = text.length();

		while (j < textLength){
			if(pattern.charAt(k) == text.charAt(j)){ //Matched substrings
				j++;
				k++;
				if(k == pattern.length()){ // found the pattern
					return j-k;
				}
			}
			else{ //mismatch no self overlap
				k = partialMatchTable[k];
				if(k < 0){
					j++;
					k++;
				}
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
	public int bruteForce(String pattern, String text){

		int patternLength = pattern.length();
		int textLength = text.length();
		boolean found = false;

		for(int i = 0;i < textLength - patternLength; i++){
			found = true;

			for(int j = 0; j < patternLength - 1; j++){

				if(pattern.charAt(j) != text.charAt(i+j)){
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
