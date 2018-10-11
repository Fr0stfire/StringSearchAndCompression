package code;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.testng.annotations.Test;

import java.util.ArrayList;


public class HuffmanCodingTest {


    @Test
    public void makeUniqueChars() {

        String text = "Im going to go across the street and get you some orange sherbet";
        HuffmanCoding huffmanCoding = new HuffmanCoding(text);

        ArrayList<HuffmanNode> unique = new ArrayList<>();

        HuffmanCharacterNode I = new HuffmanCharacterNode(1,'I',null);
        HuffmanCharacterNode m = new HuffmanCharacterNode(2,'m' ,null);
        HuffmanCharacterNode g = new HuffmanCharacterNode(5,'g',null);
        HuffmanCharacterNode o = new HuffmanCharacterNode(7,'o',null);
        HuffmanCharacterNode i = new HuffmanCharacterNode(1,'i',null);
        HuffmanCharacterNode n = new HuffmanCharacterNode(3,'n',null);
        HuffmanCharacterNode t = new HuffmanCharacterNode(6,'t',null);
        HuffmanCharacterNode a = new HuffmanCharacterNode(3,'a',null);
        HuffmanCharacterNode c = new HuffmanCharacterNode(1,'c',null);
        HuffmanCharacterNode r = new HuffmanCharacterNode(4,'r',null);
        HuffmanCharacterNode s = new HuffmanCharacterNode(5,'s',null);
        HuffmanCharacterNode h = new HuffmanCharacterNode(2,'h',null);
        HuffmanCharacterNode e = new HuffmanCharacterNode(8,'e',null);
        HuffmanCharacterNode d = new HuffmanCharacterNode(1,'d',null);
        HuffmanCharacterNode y = new HuffmanCharacterNode(1,'y',null);
        HuffmanCharacterNode b = new HuffmanCharacterNode(1,'b',null);
        HuffmanCharacterNode u = new HuffmanCharacterNode(1,'u',null);
        HuffmanCharacterNode space = new HuffmanCharacterNode(12,' ',null);


        unique.add(I);
        unique.add(m);
        unique.add(g);
        unique.add(o);
        unique.add(i);
        unique.add(n);
        unique.add(t);
        unique.add(a);
        unique.add(c);
        unique.add(r);
        unique.add(s);
        unique.add(h);
        unique.add(e);
        unique.add(d);
        unique.add(y);
        unique.add(b);
        unique.add(u);
        unique.add(space);

        ArrayList<HuffmanNode> nodes = huffmanCoding.makeUniqueChars(text);

        nodes = HuffmanNode.sort(nodes);
        unique = HuffmanNode.sort(unique);

        System.out.println("Nodes size: " + nodes.size());
        for(HuffmanNode node: nodes){
            HuffmanCharacterNode characterNode = (HuffmanCharacterNode)(node);
            System.out.print(characterNode.getCharacter());
        }

        System.out.println("\nUnique size: " + unique.size());
        for(HuffmanNode node: unique){

            HuffmanCharacterNode characterNode = (HuffmanCharacterNode)(node);
            System.out.print(characterNode.getCharacter());
        }

        assert(nodes.size() == unique.size());

        for(HuffmanNode node: unique){
            assert(nodes.contains(node));
        }


    }

    @Test
    public void makeTree() {
    }

    @Test
    public void encode() {
    }

    @Test
    public void decode() {
    }

    @Test
    public void getInformation() {
    }
}
