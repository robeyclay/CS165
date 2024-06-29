import java.util.*;

public class BPlusTree {
    enum Type {
        LEAF,
        INDEX
    }

    /* Do not change this class! */
    private static class Node {
        ArrayList<Integer> keys;
        ArrayList<Node> children;
        boolean isLeaf;

        Node(Type nt) {
            isLeaf = nt == Type.LEAF;

            keys = new ArrayList<>();
            children = new ArrayList<>();
        }

        // Do not change the toString methods!
        private String toString(int depth) {
            StringBuilder s = new StringBuilder();
            String padding = "";
            for (int i = 0; i < depth; i++) {
                padding += "  ";
            }
            s.append(padding + (isLeaf ? "LEAF NODE\n" : "INDEX NODE\n"));
            s.append(padding);
            for (int i = 0; i < keys.size(); i++) {
                s.append(String.format("key %d = %d, ",  i, keys.get(i)));
            }
            s.deleteCharAt(s.length() - 2);
            s.append("\n");
            if (!children.isEmpty()) {
                for (int i = 0; i < children.size(); i++) {
                    s.append(padding + "child " + i + " =\n");
                    s.append(children.get(i).toString(depth + 1));
                }
            }
            return s.toString();
        }

        public String toString() {
            return toString(0);
        }
    }

    int order;
    Node root;

    public BPlusTree(int order) {
        this.order = order;
        root = new Node(Type.LEAF); // The root is initially a leaf node!
    }
    /* YOUR CODE HERE
     * This is a difficult task to insert into a B+ tree as you have to take into account how and when to split nodes.
     * Because of this we have provided some pseudo code for you to implement, as well as a structure of helper methods.
     * You are welcome to go off and implement it your own way if you wish. In fact if you finish the lab early we encourage it.
     *      if insertHelper returns true given (key,root):
     *          make a new root of type Index
     *          add the the current root to the children of the new root
     *          call splitChild to split root, provided that newRoot is now root's parent
     *          make root be the newRoot
     */

    public void insert(int key) {
        if(insertHelper(key, root)) {
            Node newRoot = new Node(Type.INDEX);
            newRoot.children.add(root);
           // System.out.println("New root: " + newRoot.keys.toString());
            splitChild(root, newRoot);
            root = newRoot;
            //System.out.println("Root: " + root.toString());
        }
    }
    /* YOUR CODE HERE
     *   This is a helper method to the insert. It will be a recursive method going down each index node until it can add
     *   to a leaf node.
     *           if the node is a leaf:
     *                add the key to the keys list of the node
     *                sort the keys
     *                return  the keys size > order - 1 (if the node should be split)
     *           else:
     *               get the key child(hint: use the Method you wrote)
     *               if insertHelper of key, keyChild:
     *                   split the child (Think about what the parent and child are here)
     *                   return  the keys size > order - 1 (if the node should be split)
     *
     * */
    private boolean insertHelper(int key, Node node){
        //TODO
        //System.out.println(node.toString());
        //System.out.println("Node Children: " + node.children.toString());
        if(node.isLeaf) {
            node.keys.add(key);
            node.keys.sort(null);
            return node.keys.size() > order - 1;
        } else {
            Node child = getKeyChild(key, node);
            //System.out.println("insertHelper var child = " + child.toString());
            if (insertHelper(key, child)) {
                splitChild(child, node);
                return node.keys.size() > order - 1;
            }
        }
        return false;
    }
    private Node getKeyChild(int key, Node parent){
        return parent.children.get(getFirstIndexGreaterThanKey(parent,key));
    }
    /* YOUR CODE HERE
     * This is a helper function that will allow you to get the first index in the nodes keys list that is greater then the key given
     *  set the start index to 0;
     *       for (index is less then node's keys size; add one to the index)
     *           if the key at the current index is greater then key:
     *               break
     * return index
     *
     * */
    private int getFirstIndexGreaterThanKey(Node node,int key){
        //TODO
        int index;
        for(index = 0; index < node.keys.size(); ++index) {
            if(node.keys.get(index) > key){
                break;
            }
        }
        if(index > node.children.size() - 1) {
            return index - 1;
        }
        return index;
    }
    /*YOUR CODE HERE
     * This is perhaps the most difficult method of this lab, so we have given you a pretty detailed layout of how to do it. Feel free to come to helpdesk if you have questions.
     * NOTE: if you don't want to follow our framework please do not feel like you have to.
     * */
    private void splitChild(Node child, Node parent) {
        if (child.isLeaf) { //Splitting Leaf node

            //split is going to hold the upper half of child's  nodes
            Node split = new Node(Type.LEAF);

            //TODO: remove the last half of the keys on child and add them to split(hint: remember that this is full so size(child.keys) > order - 1))
            int size = child.keys.size();
            int sizeDivTwo = size / 2;
            for(int i = 0; i <= size / 2; i ++) {

                split.keys.add(child.keys.remove(sizeDivTwo));
            }

            //TODO get the index where split node needs to be added to parent's children (hint: getFirstIndexGreaterThenKey)
            int index = getFirstIndexGreaterThanKey(parent, split.keys.get(0));
            //System.out.println(index);
            //TODO add first key in split node to parent keys at index
            parent.keys.add(index, split.keys.get(0));
            //TODO add split node to children of parent at index +1
            parent.children.add(index + 1, split);
        } else { //Splitting Index Node

            Node split = new Node(Type.INDEX);
            //TODO get removedkey from child keys at size/2, this is the middle key of the index node so we push it up opposed to copying it up like in a leaf split
            int size = child.keys.size();
            int sizeDivTwo = size / 2;
            int removedKey = child.keys.remove(sizeDivTwo);
            //TODO removed last half of keys and children of child node and add them to split node
            for(int i = 0; i <= child.keys.size() / 2; i ++) {
                split.keys.add(child.keys.remove(sizeDivTwo));
            }
            // TODO take the pointer of the last child in the 'child' node and move it to the children of the split node(to clarify: this should remove the last child and add it to split)
            int childrenSizeDivTwo = child.children.size() / 2;
            for(int i = 0; i <= size / 2; i ++) {
                Node lastChild = child.children.remove(childrenSizeDivTwo);
                split.children.add(lastChild);
            }

            //TODO get index where the split node to be added to parent(hint: getFirstIndexGreaterThanKey)
            int index = getFirstIndexGreaterThanKey(parent, split.keys.get(0));
            //TODO add  removed key to parent keys at index
            parent.keys.add(index, removedKey);
            //TODO add split node to children of parent at index +1
            parent.children.add(index + 1, split);
        }
    }



    // For debugging purposes.
    public String toString() {
        return root.toString();
    }

    public static void main(String[] args) {
        BPlusTree bpt = new BPlusTree(5);

        bpt.insert(18);
        bpt.insert(23);
        bpt.insert(17);
        bpt.insert(2);
        System.out.println("Test Chunk 1: \n" +bpt);
        // If you're having trouble, it's typically a good idea to check the
        // state of the tree after a few inserts.
        // Feel free to print the tree whenever you want, by invoking
        // System.out.println(bpt);

        bpt.insert(26);
        bpt.insert(5);
        bpt.insert(1);
        bpt.insert(8);
        System.out.println("Test Chunk 2: \n" +bpt);

        bpt.insert(20);
        bpt.insert(4);
        bpt.insert(16);
        bpt.insert(10);
        System.out.println("Test Chunk 3: \n" +bpt);

        bpt.insert(9);
        bpt.insert(0);
        bpt.insert(11);
        bpt.insert(15);
        System.out.println("Test Chunk 4: \n" +bpt);

        bpt.insert(19);
        bpt.insert(13);
        bpt.insert(7);
        bpt.insert(25);
        System.out.println("Test Chunk 5: \n" +bpt);

        System.out.println(bpt);
    }
}
