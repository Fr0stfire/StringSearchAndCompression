package code;

import java.util.ArrayList;
import java.util.Comparator;

public abstract class HuffmanNode {

    private int frequency;
    HuffmanNode parent;







    /*
    /
    /@returns The character node if found, null if not
    */
    public static HuffmanCharacterNode treeContainsChar(ArrayList<HuffmanNode> nodes, char c){

        //check the list to see if a character node has the current char
        for(HuffmanNode node: nodes){
            if(node instanceof HuffmanCharacterNode) {
                HuffmanCharacterNode characterNode = (HuffmanCharacterNode) (node);
                if (characterNode.getCharacter() == c) {
                    return characterNode;
                }
            }
        }
        return null;
    }

    public static ArrayList<HuffmanNode> sort(ArrayList<HuffmanNode> nodes){

        nodes.sort(new Comparator<HuffmanNode>() {
                       @Override
                       public int compare(HuffmanNode o1, HuffmanNode o2) {
                           if( !(o1 instanceof HuffmanCharacterNode) || !(o2 instanceof HuffmanCharacterNode)) {
                               throw new ClassCastException("Classes " + o1.getClass() + " and " + o2.getClass() + " are not mutually comparable using the specified comparator.");
                           }
                           HuffmanCharacterNode c1 = (HuffmanCharacterNode) (o1);
                           HuffmanCharacterNode c2 = (HuffmanCharacterNode) (o2);
                           return c1.getFrequency() - c2.getFrequency();
                       }
                   }
        );
        return nodes;
    }
}