package code;

import org.junit.jupiter.api.Test;

import java.util.*;


public class HuffmanCodingTest {


    @Test
    public void makeUniqueChars() {

        String text = "Im going to go across the street and get you some orange sherbet";
        HuffmanCoding huffmanCoding = new HuffmanCoding();

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

        nodes.sort(Comparator.comparingInt(HuffmanNode::getFrequency));
        unique.sort(Comparator.comparingInt(HuffmanNode::getFrequency));
        //nodes = HuffmanNode.sort(nodes);
        //unique = HuffmanNode.sort(unique);

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
    void depth(){

        HuffmanCoding huffmanCoding = new HuffmanCoding();

        String textOne = "abccddeeefff";
        String textTwo = "Im going to go across the street and get you some orange sherbet";

        ArrayList<HuffmanNode> nodes = huffmanCoding.makeUniqueChars(textOne);
        HuffmanNode root = huffmanCoding.makeTree(nodes);

        depthOne(root);

        nodes = huffmanCoding.makeUniqueChars(textTwo);
        root = huffmanCoding.makeTree(nodes);

        depthTwo(root);



    }

    public void depthOne(HuffmanNode root){

        int depth = root.getDepth();
        System.out.println("Depth = " + depth);

        assert(root.getLeftChild() != null && root.getRightChild() != null && depth == 3);

    }


    public void depthTwo(HuffmanNode root){
        int depth = root.getDepth();
        System.out.println("Depth = " + depth);

        assert(root.getLeftChild() != null && root.getRightChild() != null);

    }

    public ArrayList<HuffmanNode> breadthFirst(){
        HuffmanCoding huffmanCoding = new HuffmanCoding();
        String textOne = "abccddeeefff";
        String textTwo = "Im going to go across the street and get you some orange sherbet";

        ArrayList<HuffmanNode> nodes = huffmanCoding.makeUniqueChars(textOne);
        HuffmanNode root = huffmanCoding.makeTree(nodes);

        if(root == null){
            return null;
        }

        Queue<HuffmanNode> unvisited = new LinkedList<>();
        Set<HuffmanNode> visited = new HashSet<>();

        ArrayList<HuffmanNode> breadthFirst = new ArrayList<>();


        unvisited.add(root);
        while(!unvisited.isEmpty()){

            HuffmanNode subtreeRoot = unvisited.poll();
            if(subtreeRoot == null){
                break;
            }
            HuffmanNode leftChild = subtreeRoot.getLeftChild();
            HuffmanNode rightChild = subtreeRoot.getRightChild();

            if (leftChild != null && !visited.contains(leftChild)) {
                unvisited.add(leftChild);
            }
            if(rightChild != null && !visited.contains(rightChild)){
                unvisited.add(rightChild);
            }


            visited.add(subtreeRoot);
            breadthFirst.add(subtreeRoot);

        }
        return breadthFirst;
    }


    @Test public void drawTree(){
        HuffmanCoding huffmanCoding = new HuffmanCoding();
        String textOne = "abccddeeefff";
        String textTwo = "Im going to go across the street and get you some orange sherbet";

        ArrayList<HuffmanNode> nodes = huffmanCoding.makeUniqueChars(song());
        HuffmanNode root = huffmanCoding.makeTree(nodes);

        print(root);
    }

    @Test
    public void makeTree() {

        String text = "abccddeeefff";
        HuffmanCoding huffmanCoding = new HuffmanCoding();
        ArrayList<HuffmanNode> nodes = huffmanCoding.makeUniqueChars(text);
        HuffmanNode root = huffmanCoding.makeTree(nodes);


        System.out.println("Root frequency = " + root.getFrequency());
        System.out.println("Total length of text = " + text.length());
        assert(root.getFrequency() == text.length());

        System.out.println("Left child frequency = " + root.getLeftChild().getFrequency());

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
    public static void print(HuffmanNode root)
    {
        List<List<String>> lines = new ArrayList<List<String>>();

        List<HuffmanNode> level = new ArrayList<HuffmanNode>();
        List<HuffmanNode> next = new ArrayList<HuffmanNode>();

        level.add(root);
        int nn = 1;

        int widest = 0;

        while (nn != 0) {
            List<String> line = new ArrayList<String>();

            nn = 0;

            for (HuffmanNode n : level) {
                if (n == null) {
                    line.add(null);

                    next.add(null);
                    next.add(null);
                } else {
                    String aa = n.getInfo();
                    line.add(aa);
                    if (aa.length() > widest) widest = aa.length();

                    next.add(n.getLeftChild());
                    next.add(n.getRightChild());

                    if (n.getLeftChild() != null) nn++;
                    if (n.getRightChild() != null) nn++;
                }
            }

            if (widest % 2 == 1) widest++;

            lines.add(line);

            List<HuffmanNode> tmp = level;
            level = next;
            next = tmp;
            next.clear();
        }

        int perpiece = lines.get(lines.size() - 1).size() * (widest + 4);
        for (int i = 0; i < lines.size(); i++) {
            List<String> line = lines.get(i);
            int hpw = (int) Math.floor(perpiece / 2f) - 1;

            if (i > 0) {
                for (int j = 0; j < line.size(); j++) {

                    // split node
                    char c = ' ';
                    if (j % 2 == 1) {
                        if (line.get(j - 1) != null) {
                            c = (line.get(j) != null) ? '┴' : '┘';
                        } else {
                            if (j < line.size() && line.get(j) != null) c = '└';
                        }
                    }
                    System.out.print(c);

                    // lines and spaces
                    if (line.get(j) == null) {
                        for (int k = 0; k < perpiece - 1; k++) {
                            System.out.print(" ");
                        }
                    } else {

                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? " " : "─");
                        }
                        System.out.print(j % 2 == 0 ? "┌" : "┐");
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? "─" : " ");
                        }
                    }
                }
                System.out.println();
            }

            // print line of numbers
            for (int j = 0; j < line.size(); j++) {

                String f = line.get(j);
                if (f == null) f = "";
                int gap1 = (int) Math.ceil(perpiece / 2f - f.length() / 2f);
                int gap2 = (int) Math.floor(perpiece / 2f - f.length() / 2f);

                // a number
                for (int k = 0; k < gap1; k++) {
                    System.out.print(" ");
                }
                System.out.print(f);
                for (int k = 0; k < gap2; k++) {
                    System.out.print(" ");
                }
            }
            System.out.println();

            perpiece /= 2;
        }
    }
    public String song(){
        return"Hiya Barbie\n" +
                "Hi Ken!\n" +
                "Do you want to go for a ride?\n" +
                "Sure Ken\n" +
                "Jump in\n" +
                "I'm a Barbie girl, in a Barbie world\n" +
                "Life in plastic, it's fantastic\n" +
                "You can brush my hair, undress me everywhere\n" +
                "Imagination, life is your creation\n" +
                "Come on Barbie, let's go party!\n" +
                "I'm a Barbie girl, in a Barbie world\n" +
                "Life in plastic, it's fantastic\n" +
                "You can brush my hair, undress me everywhere\n" +
                "Imagination, life is your creation\n" +
                "I'm a blond bimbo girl, in a fantasy world\n" +
                "Dress me up, make it tight, I'm your dolly\n" +
                "You're my doll, rock'n'roll, feel the glamor in pink\n" +
                "Kiss me here, touch me there, hanky panky\n" +
                "You can touch\n" +
                "you can play\n" +
                "if you say \"I'm always yours\"\n" +
                "I'm a Barbie girl, in a Barbie world\n" +
                "Life in plastic, it's fantastic\n" +
                "You can brush my hair, undress me everywhere\n" +
                "Imagination, life is your creation\n" +
                "Come on Barbie, let's go party! (Ah ah ah yeah)\n" +
                "Come on Barbie, let's go party! (Oh oh)\n" +
                "Come on Barbie, let's go party! (Ah ah ah yeah)\n" +
                "Come on Barbie, let's go party! (Oh oh)\n" +
                "Make me walk, make me talk, do whatever you please\n" +
                "I can act like a star, I can beg on my knees\n" +
                "Come jump in, bimbo friend, let us do it again\n" +
                "Hit the town, fool around, let's go party\n" +
                "You can touch\n" +
                "you can play\n" +
                "If you say \"I'm always yours\"\n" +
                "You can touch\n" +
                "you can play,\n" +
                "If you say \"I'm always yours\"\n" +
                "Come on Barbie, let's go party! (Ah ah ah yeah)\n" +
                "Come on Barbie, let's go party! (Oh oh)\n" +
                "Come on Barbie, let's go party! (Ah ah ah yeah)\n" +
                "Come on Barbie, let's go party! (Oh oh)\n" +
                "I'm a Barbie girl, in a Barbie world\n" +
                "Life in plastic, it's fantastic\n" +
                "You can brush my hair, undress me everywhere\n" +
                "Imagination, life is your creation\n" +
                "I'm a Barbie girl, in a Barbie world\n" +
                "Life in plastic, it's fantastic\n" +
                "You can brush my hair, undress me everywhere\n" +
                "Imagination, life is your creation\n" +
                "Come on Barbie, let's go party! (Ah ah ah yeah)\n" +
                "Come on Barbie, let's go party! (Oh oh)\n" +
                "Come on Barbie, let's go party! (Ah ah ah yeah)\n" +
                "Come on Barbie, let's go party! (Oh oh)\n" +
                "Oh, I'm having so much fun!\n" +
                "Well Barbie, we are just getting started\n" +
                "Oh, I love you Ken";
    }

}
