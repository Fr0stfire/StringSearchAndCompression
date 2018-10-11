package code;

public class HuffmanFrequencyNode extends HuffmanNode{

    private int frequency;

    private HuffmanNode leftNode;
    private HuffmanNode rightNode;


    public HuffmanFrequencyNode(int frequency, HuffmanNode left, HuffmanNode right){
        this.frequency = frequency;
        this.leftNode = left;
        this.rightNode = right;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public HuffmanNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(HuffmanNode leftNode) {
        this.leftNode = leftNode;
    }

    public HuffmanNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(HuffmanNode rightNode) {
        this.rightNode = rightNode;
    }



}
