package code;

import java.util.*;

/**
 * A new instance of HuffmanCoding is created for every run. The constructor is
 * passed the full text to be encoded or decoded, so this is a good place to
 * construct the tree. You should store this tree in a field and then use it in
 * the encode and decode methods.
 */
public class HuffmanCoding {

	//The root of the tree after makeTree() has been called
	HuffmanNode root;

	//All of the unique characters that are in a text. Produced from makeUniqueChars()
	ArrayList<HuffmanNode> nodes = new ArrayList<>();

	//Binary codes of all of the unique chars mapped to each character
	Map<Character, String>  binaryCodes = new HashMap<>();

	//All of the nodes in a tree. Useful for iterative search algorithms
	ArrayList<HuffmanNode> tree = new ArrayList<>();


	public HuffmanCoding(){

	}

	public HuffmanCoding(String text) {
		this.nodes = makeUniqueChars(text);
		makeTree(new ArrayList<>(this.nodes)); // copy the array as to preserve the list
		this.root = this.tree.get(0);
		this.binaryCodes = createBinaryTable(root);
		printTable();
	}


	/**
	 * @param text The text in which the algorithm will produce an arraylist of all of the unique characters and their frequency in.
	 * @return The completed arrayList with all of the unique chars and their frequency
	 */
	public ArrayList<HuffmanNode> makeUniqueChars(String text){

		//Text is an empty string
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


	/**
	 * Using an arrayList provided this method finds the two lowest frequency nodes and creates a frequency node as both of their parents.
	 * The parent frequency node is then added to the list into the position of the sum of its children's frequencies.
	 * Keeps doing this iteratively until their is only one node left in the list; which is the root.
	 *
	 * @param characterCounts An arrayList of HuffmanNodes that contains all of the unique chars and their frequency
	 * @return	The root of the tree created by this method
	 */
	public HuffmanNode makeTree(ArrayList<HuffmanNode> characterCounts){

		//stop the loop when you are left with the highest frequency node, the root node
		while(characterCounts.size() != 1) {

			//Make sure the list is sorted
			characterCounts.sort(Comparator.comparingInt(HuffmanNode::getFrequency)); //Could use an anonymous class but this lambda expression is more elegant

			int size = characterCounts.size(); //Keep track of the size in a variable as to reduce how many method calls

			//Combine the two lowest frequency nodes
			HuffmanNode leftNode = characterCounts.get(0);
			HuffmanNode rightNode = characterCounts.get(1);
			int frequency = leftNode.getFrequency() + rightNode.getFrequency();
			HuffmanNode parent = new HuffmanFrequencyNode(frequency,null, leftNode, rightNode);

			//Remove the nodes from the list and add their parent
			characterCounts.remove(leftNode);
			characterCounts.remove(rightNode);
			characterCounts.add(parent);

			leftNode.setParent(parent);
			rightNode.setParent(parent);

		}
		this.tree = characterCounts;
		this.root = characterCounts.get(0);
		return characterCounts.get(0);
	}

	public void printTable(){
		for (Map.Entry<Character, String> entry : this.binaryCodes.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}


	/**
	 * This method gets all leaves of a tree and adds them to a linked list. It iteratively goes from the bottom of the tree back to the root checking if it is the left or right child of its parent node.
	 * 0 for left child, 1 for right child. after it gets to the root the String is reversed to be the correct direction as we were traversing back up the tree not down it.
	 * @param root The root of a HuffmanNode tree
	 * @return A hashmap containing all of the binary codes mapped to the characters
	 */
	public HashMap<Character,String> createBinaryTable(HuffmanNode root){
		HashMap<Character,String> binaryTable = new HashMap<>();

		LinkedList<HuffmanNode> leaves = root.getLeaves(new LinkedList<HuffmanNode>());

		while(!leaves.isEmpty()){

			HuffmanNode currentNode = leaves.poll();
			String binaryCode = "";
			HuffmanCharacterNode temp = (HuffmanCharacterNode)(currentNode);
			Character c = temp.getCharacter();

			while(!root.equals(currentNode)){

				HuffmanNode parent = currentNode.getParent();

				if(parent.getLeftChild().equals(currentNode)){
					binaryCode = binaryCode + "0";
				}
				else if(parent.getRightChild().equals(currentNode)){
					binaryCode = binaryCode + "1";
				}
				else{
					System.out.println("Something went wrong with this node");
				}
				currentNode = currentNode.getParent();
			}
			binaryCode = new StringBuilder(binaryCode).reverse().toString();
			binaryTable.put(c,binaryCode );
		}
		this.binaryCodes = binaryTable;
		return binaryTable;
	}

	/**
	 * Take an input string, text, and encode it with the stored tree. Should
	 * return the encoded text as a binary string, that is, a string containing
	 * only 1 and 0.
	 */
	public String encode(String text) {

		StringBuilder encoded = new StringBuilder("");
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			encoded .append(this.binaryCodes.get(c));
		}


		return encoded.toString();
	}

	/**
	 * Take encoded input as a binary string, decode it using the stored tree,
	 * and return the decoded text as a text string.
	 */
	public String decode(String encoded) {
		StringBuilder decoded = new StringBuilder("");
		HuffmanNode currentNode = this.root;

		System.out.println(this.binaryCodes);
		for (int i = 0; i <= encoded.length(); i++) {
			if (currentNode instanceof HuffmanCharacterNode) {
				decoded.append(((HuffmanCharacterNode) currentNode).getCharacter());
				currentNode = this.root;
				if(i == encoded.length()){
					break;
				}
			}
			char c = encoded.charAt(i);
			if(c == '0'){
				currentNode = currentNode.getLeftChild();
			}
			else if(c == '1'){
				currentNode = currentNode.getRightChild();
			}
			else{
				System.out.println("Unexpected character found in the binary table. Character = " + c);
			}

		}
		return decoded.toString();
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
