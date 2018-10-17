package code;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

public class HuffmanFrequencyNode extends HuffmanNode {

    private Integer frequency;

    private HuffmanNode parent;
    private HuffmanNode leftChild;
    private HuffmanNode rightChild;

    public HuffmanFrequencyNode(int frequency, HuffmanNode parent, HuffmanNode leftChild, HuffmanNode rightChild) {
        this.frequency = frequency;
        this.parent = parent;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }
    public String getInfo(){
        return Integer.toString(frequency);
    }

    @Override
    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public HuffmanNode getParent() {
        return parent;
    }

    public void setParent(HuffmanNode parent) {
        this.parent = parent;
    }

    @Override
    public HuffmanNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(HuffmanNode leftChild) {
        this.leftChild = leftChild;
    }

    @Override
    public HuffmanNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(HuffmanNode rightChild) {
        this.rightChild = rightChild;
    }
    public int getDepth(){
        int leftDepth = 0;
        int rightDepth = 0;

        if(leftChild != null){
            leftDepth = this.leftChild.getDepth();
        }
        if(rightChild != null){
            rightDepth = this.rightChild.getDepth();
        }
        return 1 + ((leftDepth < rightDepth) ? leftDepth:rightDepth);
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



    public String displayTree(){
        String line = "";
        int depth = this.getDepth();

        for (int i = 0; i < depth; i++) {
            line = line + " ";
        }
        line = line + this.getFrequency();

        return line;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        HuffmanFrequencyNode that = (HuffmanFrequencyNode) o;
        return frequency.equals(that.frequency);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), frequency);
    }
}
