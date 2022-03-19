/* Binary Search Tree Class
 *  A binary search tree of generic type which extends Comparable
 */

public class BST<E extends Comparable<E>> implements Tree<E> {

    private TreeNode root;
    private int size;

    public class TreeNode<E> {
        public E element;
        public TreeNode rightChild;
        public TreeNode leftChild;

        /* TODO: finish this constructor
         *  Think: what needs to be initialized, there are three member variables */
        public TreeNode(E element) {
            this.element = element;
            rightChild = null;
            leftChild = null;
        }
    }

    public BST() {
        this.root = null;
        size = 0;
    }

    public BST(E item) {
        this();
        insert(item);
    }

    public BST(E[] items) {
        /* TODO: Insert all of items into this tree */
        for(E p : items) {
            insert(p);
        }
    }

    /* TODO: finish this method */
    @Override
    public boolean search(E e) {
        return searchRecursive(root, e);
    }

    public boolean searchRecursive(TreeNode curr, E item) {
        TreeNode newNode = new TreeNode(item);
        if(curr == null) {
            //System.out.println("curr == null");
            return false;
        } else {
            if (item.compareTo((E)curr.element) >= 0) {
                //System.out.println("item > curr " + "item: " + item + " curr.element: " + curr.element);
                if (item.compareTo((E)curr.element) == 0) {
                    return true;
                } else {
                    return searchRecursive(curr.rightChild, item);
                }
            } else if (item.compareTo((E) curr.element) <= 0) {
                //System.out.println("item < curr " + "item: " + item + " curr.element: " + curr.element);
                if (item.compareTo((E)curr.element) == 0) {
                    return true;
                } else {
                    return searchRecursive(curr.leftChild, item);
                }
            }
        }
        return false;
    }

    /* TODO: finish this method */
    @Override
    public boolean insert(E e) {
        TreeNode newNode = new TreeNode(e);
        if(isEmpty()) {
            root = newNode;
            size++;
            return true;
        } else {
            return insertRecursive(root, e);
        }
    }

    public boolean insertRecursive(TreeNode curr, E item){
        TreeNode newNode = new TreeNode(item);
        if(curr == null) {
            return false;
        } else {
            if (item.compareTo((E) curr.element) > 0) {
                if (curr.rightChild == null) {
                    curr.rightChild = newNode;
                    size++;
                    return true;
                } else {
                    insertRecursive(curr.rightChild, item);
                }
            } else if (item.compareTo((E) curr.element) < 0) {
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


    /* TODO: finish this method */
    @Override
    public boolean remove(E e) {
        //System.out.println("Remove: " + root.leftChild.element);
        return removeRecursive(e, root);
    }

    public boolean removeRecursive(E e, TreeNode curr) {
        if (curr == null) {
            return false;
        } if (e.compareTo((E)curr.element) >= 0) {
            if(removeRecursiveHelper(e, curr)) {
               //System.out.println("removeRecursive: " + curr);
                return true;
            } else {
                removeRecursive(e, curr.rightChild);
            }
        } else if (e.compareTo((E)curr.element) <= 0) {
            if(removeRecursiveHelper(e, curr)) {
                return true;
            } else {
                removeRecursive(e, curr.leftChild);
            }
        }
        return false;
    }

    public boolean removeRecursiveHelper (E e, TreeNode curr) {
        if (removeLeaf(e, curr)) {
            return true;
        } else if (removeOneChild(e, curr)) {
            return true;
        } else if (removeTwoChild(e, curr)) {
            //System.out.println("removeRecursiveHelper: " + curr.element);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeTwoChild(E e, TreeNode curr) {
        if(curr.rightChild != null && curr.leftChild != null) {
            //System.out.println("removeTwoChild: " + curr.element);
            TreeNode successor = curr.rightChild;
            TreeNode predecessor = curr.leftChild;
            while (successor.leftChild != null) {
                successor = successor.leftChild;
            }
            //System.out.println("Two Child Successor before Reassign:" + successor.element);
            //System.out.println("Two Child curr before Reassign:" + curr.element);
            TreeNode temp = curr;
            curr = successor;
            curr.leftChild = predecessor;
            size--;
            if(temp.equals(root)) {
                root = curr;
            }
            //System.out.println("Two Child Successor after Reassign:" + successor.element);
            //System.out.println("Two Child curr after Reassign:" + curr.element);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeOneChild(E e, TreeNode curr) {
        if(curr.rightChild != null && curr.leftChild == null) {
            if(e.compareTo((E)curr) == 0) {
                curr.element = curr.rightChild.element;
                curr.rightChild = null;
                size--;
                return true;
            } else {
                return false;
            }
        } else if (curr.leftChild != null && curr.rightChild == null) {
            if(e.compareTo((E)curr.element) == 0) {
                //System.out.println("OneChild before reassign: " + curr.element);
                curr.element = curr.leftChild.element;
                curr.leftChild = null;

                //System.out.println("OneChild after reassign: " + curr.element);
                //System.out.println("OneChild root after reassign: " + root.element);
                size--;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean removeLeaf(E e, TreeNode curr) {
        if(curr.leftChild == null && curr.rightChild == null) {
            TreeNode parent = findParent(root, e);
            if(e.compareTo((E)parent.leftChild.element) == 0) {
                parent.leftChild = null;
                size--;
                return true;
            } else if (e.compareTo((E)parent.rightChild.element) == 0){
                parent.rightChild = null;
                size--;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public TreeNode findParent(TreeNode curr, E target) {
        if(target.compareTo((E)curr.leftChild.element) == 0 || target.compareTo((E)curr.rightChild.element) == 0) {
            return curr;
        } else {
            findParent(curr.leftChild, target);
            findParent(curr.rightChild, target);
        }
        return null;
    }

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
