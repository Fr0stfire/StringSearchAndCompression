package code;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HuffmanCharacterNode that = (HuffmanCharacterNode) o;
        return frequency == that.frequency &&
                character == that.character &&
                Objects.equals(parent, that.parent);
    }

    @Override
    public int hashCode() {

        return Objects.hash(frequency, parent, character);
    }
}
