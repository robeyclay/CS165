/* Binary Search Tree Class
 *  Made by Toby Patterson for CS165 at CSU
 *  6/25/2020
 *  A basic binary search tree of integers, without a remove method.
 */

public class BST implements Tree<Integer> {

    private TreeNode root;
    private int size;

    public class TreeNode<Integer> {
        public Integer element;
        public TreeNode rightChild;
        public TreeNode leftChild;

        /* TODO: finish this constructor
         *  Think: what needs to be initialized, there are three member variables */
        public TreeNode(Integer element) {
            this.element = element;
            rightChild = null;
            leftChild = null;
        }
    }

    public BST() {
        this.root = null;
        size = 0;
    }

    public BST(Integer item) {
        super();
        insert(item);
    }

    public BST(Integer[] items) {
        /* TODO: Insert all of items into this tree */
        for(Integer p : items) {
            insert(p);
        }
    }

    /* Does a binary search.
     *  TODO: complete this method */
    @Override
    public boolean search(Integer item) {
        return searchRecursive(root, item);
    }

    public boolean searchRecursive(TreeNode curr, Integer item) {
        TreeNode newNode = new TreeNode(item);
        if(curr == null) {
            //System.out.println("curr == null");
            return false;
        } else {
            if (item.compareTo((Integer)curr.element) >= 0) {
                //System.out.println("item > curr " + "item: " + item + " curr.element: " + curr.element);
                if (item.compareTo((Integer)curr.element) == 0) {
                    return true;
                } else {
                    searchRecursive(curr.rightChild, item);
                }
            } else if (item.compareTo((Integer) curr.element) <= 0) {
                //System.out.println("item < curr " + "item: " + item + " curr.element: " + curr.element);
                if (item.compareTo((Integer)curr.element) == 0) {
                    return true;
                } else {
                    searchRecursive(curr.leftChild, item);
                }
            }
        }
        return false;
    }

    /* Inserts item into tree, return false if item is already in tree.
     *  Order of the tree is: root.element > left.element &&
     *                        root.element < right.element
     *  TODO: complete this method
     */
    @Override
    public boolean insert(Integer item) {
        TreeNode newNode = new TreeNode(item);
        if(isEmpty()) {
            root = newNode;
            size++;
            return true;
        } else {
            return insertRecursive(root, item);
        }

    }

    public boolean insertRecursive(TreeNode curr, Integer item){
        TreeNode newNode = new TreeNode(item);
        if(curr == null) {
            return false;
        } else {
            if (item.compareTo((Integer) curr.element) > 0) {
                if (curr.rightChild == null) {
                    curr.rightChild = newNode;
                    size++;
                    return true;
                } else {
                    insertRecursive(curr.rightChild, item);
                }
            } else if (item.compareTo((Integer) curr.element) < 0) {
                if (curr.leftChild == null) {
                    curr.leftChild = newNode;
                    size++;
                    return true;
                } else {
                    insertRecursive(curr.leftChild, item);
                }
            }
        }
        return false;
    }
    // for the next lab
//    @Override
//    public boolean remove(Integer item) {
//        return false;
//    }

    /* Getter method for the size of the tree
     *  TODO: complete this method
     */
    @Override
    public int size() {
        return size;
    }

    /* Does an inorder traversal of the tree, printing each visited node
     *  TODO: Complete this method
     */
    @Override
    public void inorder() {
        inorderRecursive(root);
    }

    public void inorderRecursive(TreeNode curr) {
        if(curr == null) {
            return;
        } else {
            inorderRecursive(curr.leftChild);
            System.out.print(curr.element + " ");
            inorderRecursive(curr.rightChild);
        }
    }

    /* Does a postorder traversal of the tree, printing each visited node
     *  TODO: Complete this method
     */
    @Override
    public void postorder() {
        postorderRecursive(root);
    }

    public void postorderRecursive(TreeNode curr) {
        if(curr == null) {
            return;
        } else {
            postorderRecursive(curr.leftChild);
            postorderRecursive(curr.rightChild);
            System.out.print(curr.element + " ");
        }
    }

    /* Does a preorder traversal of the tree, printing each visited node
     *  TODO: Complete this method
     */
    @Override
    public void preorder() {
        preorderRecursive(root);
    }

    public void preorderRecursive(TreeNode curr){
        if(curr == null) {
            return;
        } else {
            System.out.print(curr.element + " ");
            preorderRecursive(curr.leftChild);
            preorderRecursive(curr.rightChild);
        }
    }

    /* Prints true on empty tree, false otherwise
     *  TODO: Complete this method
     */
    @Override
    public boolean isEmpty() {
        if(size == 0) {
            return true;
        }
        return false;
    }

    /* Returns the root node of the tree */
    public TreeNode getRoot() {
        return root;
    }
}
