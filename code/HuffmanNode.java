package code;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Objects;

public abstract class HuffmanNode {

    private int frequency;

    private HuffmanNode parent;
    private HuffmanNode leftChild;
    private HuffmanNode rightChild;

    public String getInfo(){
        return Integer.toString(frequency);
    }

    public HuffmanNode getLeftChild() {
        return leftChild;
    }

    public HuffmanNode getRightChild() {
        return rightChild;
    }

    public int getFrequency() {
        return frequency;
    }

    public HuffmanNode getParent() {
        return parent;
    }

    public void setParent(HuffmanNode parent) {
        this.parent = parent;
    }

    public int getDepth(){
        return 1;
    }
    public String displayTree(){


        return "";
    }



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
    public LinkedList<HuffmanNode> getLeaves(LinkedList<HuffmanNode> leaves){

       if(leftChild != null){
           leftChild.getLeaves(leaves);
       }
       if(rightChild != null){
           rightChild.getLeaves(leaves);
       }

       return leaves;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HuffmanNode)) return false;
        HuffmanNode that = (HuffmanNode) o;
        return getFrequency() == that.getFrequency() &&
                Objects.equals(getParent(), that.getParent()) &&
                Objects.equals(getLeftChild(), that.getLeftChild()) &&
                Objects.equals(getRightChild(), that.getRightChild());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFrequency(), getParent(), getLeftChild(), getRightChild());
    }
}
