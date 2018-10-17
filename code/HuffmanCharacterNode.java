package code;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

public class HuffmanCharacterNode extends HuffmanNode{

    private int frequency;
    private HuffmanNode parent;
    char character;

    public HuffmanCharacterNode(int frequency, char character,HuffmanNode parent) {
        this.frequency = frequency;
        this.parent = parent;
        this.character = character;
    }
    public String getInfo(){
        return Integer.toString(frequency) + getCharacter();
    }

    public HuffmanNode getParent() {
        return parent;
    }

    public void setParent(HuffmanNode parent) {
        this.parent = parent;
    }

    public int getFrequency() {
        return frequency;
    }

    public char getCharacter() {
        return character;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
    public void incrementFrequency(){
        this.frequency++;
    }

    @Override
    public LinkedList<HuffmanNode> getLeaves(LinkedList<HuffmanNode> leaves) {
         leaves.add(this);
         return leaves;
    }

    @Override
    public int getDepth() {
        return 1;
    }
    public String displayTree(){
        String line = "";
        int depth = this.getDepth();

        for (int i = 0; i < depth; i++) {
            line = line + " ";
        }
        line = line + this.getFrequency();
        line = line + this.getCharacter();

        return line;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        HuffmanCharacterNode that = (HuffmanCharacterNode) o;
        return frequency == that.frequency &&
                character == that.character;
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), frequency, character);
    }
}
