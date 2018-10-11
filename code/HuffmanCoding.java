package code;

import java.util.*;

/**
 * A new instance of HuffmanCoding is created for every run. The constructor is
 * passed the full text to be encoded or decoded, so this is a good place to
 * construct the tree. You should store this tree in a field and then use it in
 * the encode and decode methods.
 */
public class HuffmanCoding {

	private Map<Character, Integer> counts = new HashMap<>();

	/**
	 * This would be a good place to compute and store the tree.
	 */
	public HuffmanCoding(String text) {
		//makeUniqueChars(text);
	}

	public ArrayList<HuffmanNode> makeUniqueChars(String text){

		if(text.length() == 0){
			return null;
		}
		ArrayList<HuffmanNode> nodes = new ArrayList<>();


		nodes.add(new HuffmanCharacterNode(1,text.charAt(0),null)); //very first item in text. adds here so we don't have an if statement in the next loop that redundantly checks each iteration

		//Don't start the for loop as if its length 1 we have been through all of the text
		if(text.length() == 1){
			return nodes;
		}

		for(int i = 1; i < text.length(); i++){
			Character c = text.charAt(i);

			HuffmanCharacterNode characterNode = HuffmanNode.treeContainsChar(nodes,c); //Check the tree for a node with the current character

			if(characterNode != null){//Found in the tree

				characterNode.incrementFrequency(); //call method to increment frequency so no redundant variables and method calls
			}
			else{
				nodes.add(new HuffmanCharacterNode(1,c,null));
			}
		}
		return nodes;
	}


	public HuffmanNode makeTree(Map<Character,Integer> characterCounts){

		//combine two least frequently used characters and sum their occurrences


		//todo
		return null;
	}

	/**
	 * Take an input string, text, and encode it with the stored tree. Should
	 * return the encoded text as a binary string, that is, a string containing
	 * only 1 and 0.
	 */
	public String encode(String text) {
		// TODO fill this in.
		return "";
	}

	/**
	 * Take encoded input as a binary string, decode it using the stored tree,
	 * and return the decoded text as a text string.
	 */
	public String decode(String encoded) {
		// TODO fill this in.
		return "";
	}

	/**
	 * The getInformation method is here for your convenience, you don't need to
	 * fill it in if you don't wan to. It is called on every run and its return
	 * value is displayed on-screen. You could use this, for example, to print
	 * out the encoding tree.
	 */
	public String getInformation() {
		return "";
	}
}
