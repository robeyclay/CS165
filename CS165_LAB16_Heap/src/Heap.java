import java.util.Arrays;

interface PriorityQueue<T extends Comparable<T>> {
    public void push(T item);
    public T pop();
    public T peek();
}

public class Heap<T extends Comparable<T>> implements PriorityQueue<T> {

    private T[] heap;
    private int size;

    public Heap(int capacity) {
        heap = (T[]) new Comparable[capacity];
    }

    /* Given an index in the heap array, calculate what the parent node's
     * index is.
     */
    private int parent(int index) {
        return (index - 1) / 2;
    }

    /* Given an index in the heap array, calculate what the right child's
     * index is.
     */
    private int rchild(int index) {
        return 2 * (index + 1);
    }

    /* Given an index in the heap array, calculate what the left child's
     * index is.
     */
    private int lchild(int index) {
        return 2 * index + 1;
    }

    private boolean hasLeftChild(int index) {
        int lchild = lchild(index);
        if(lchild <= size && lchild >= 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean hasRightChild(int index) {
        int rchild = rchild(index);
        if(rchild <= size && rchild >= 0) {
            return true;
        } else {
            return false;
        }
    }

    /* Swap the items in the array at index1 and index2.
     */
    private void swap(int index1, int index2) {
        T temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    /* Perform a heapify starting at the given index.
     * Check the index's two children to see if you should swap the node
     * with either of these children. If you should, do the swap, and call
     * another bubbleDown on the index you swapped to.
     */
    private void bubbleDown(int index) {
        int rchild = rchild(index);
        int lchild = lchild(index);
        if(hasLeftChild(index) && hasRightChild(index) && size > rchild && size > lchild) {
            if(heap[lchild].compareTo(heap[rchild]) > 0 && lchild < size) {
                if(heap[lchild].compareTo(heap[index]) > 0) {
                    swap(index, lchild);
                    bubbleDown(lchild);
                }
            } else if(heap[rchild].compareTo(heap[lchild]) > 0 && rchild < size){
                if(heap[rchild].compareTo(heap[index]) > 0) {
                    swap(index, rchild);
                    bubbleDown(rchild);
                }
            }
        } else if (hasLeftChild(index) && lchild < size) {
            if (heap[lchild].compareTo(heap[index]) > 0) {
                swap(index, lchild);
                bubbleDown(lchild);
            }
        }
    }

    /* Perform a "reverse-heapify" starting at the current index.
     * Check the index's parent to see if you should swap the two; If you
     * should, do a swap and call another bubbleUp on the index you swapped to.
     *
     * This should be a significantly simpler method than bubbleDown.
     */
    private void bubbleUp(int index) {
        int parent = parent(index);
        if(heap[index].compareTo(heap[parent]) > 0) {
            swap(index, parent);
            bubbleUp(parent);
        }
    }

    /* Add an item to the queue.
     * Add the item at the end of the array, then bubble it up.
     * Assume that the heap will have space.
     */
    public void push(T item) {
        if(size == 0) {
            heap[0] = item;
        }
        heap[size] = item;
        bubbleUp(size);
        size++;
    }

    /* Remove the highest priority item from the queue.
     * Replace the item at the root (index 0) with the last item
     * in the array, then bubble it down.
     * Assume that the heap won't be empty.
     */
    public T pop() {
        T temp = heap[0];
        heap[0] = heap[size - 1];
        heap[size - 1] = temp;
        size--;
        bubbleDown(0);
        return temp;
    }

    /* Return the highest priority item from the queue.
     */
    public T peek() {
        return heap[0];
    }

    public String toString() {
        return Arrays.toString(heap);
    }

    public static void main(String[] args) {
        Heap<String> colors = new Heap<String>(10);

        colors.push("lime");
        System.out.println("push lime       -> " + colors);
        colors.push("fuchsia");
        System.out.println("push fuchsia    -> " + colors);
        colors.push("cyan");
        System.out.println("push cyan       -> " + colors);
        colors.push("yellow");
        System.out.println("push yellow     -> " + colors);
        colors.push("maroon");
        System.out.println("push maroon     -> " + colors);

        System.out.println();
        System.out.printf( "pop %-12s<- " + colors + "\n", colors.pop());
        System.out.println(colors);
        System.out.printf( "pop %-12s<- ", colors.pop());
        System.out.println(colors);
        System.out.printf( "pop %-12s<- ", colors.pop());
        System.out.println(colors);

        System.out.println();
        System.out.printf( "peek %-11s<- ", colors.peek());
        System.out.println(colors);
        System.out.printf( "peek %-11s<- ", colors.peek());
        System.out.println(colors);

        System.out.println();
        colors.push("olive");
        System.out.println("push olive      -> " + colors);
        colors.push("icterine");
        System.out.println("push icterine   -> " + colors);
        colors.push("sienna");
        System.out.println("push sienna     -> " + colors);
        colors.push("silver");
        System.out.println("push silver     -> " + colors);
        colors.push("teal");
        System.out.println("push teal       -> " + colors);
        System.out.printf( "pop %-12s<- ", colors.pop());
        System.out.println(colors);
        System.out.printf( "pop %-12s<- ", colors.pop());
        System.out.println(colors);
        colors.push("slate");
        System.out.println("push slate      -> " + colors);
        System.out.printf( "pop %-12s<- ", colors.pop());
        System.out.println(colors);
        System.out.printf( "peek %-11s<- ", colors.peek());
        System.out.println(colors);
        System.out.printf( "pop %-12s<- ", colors.pop());
        System.out.println(colors);
        System.out.printf( "peek %-11s<- ", colors.peek());
        System.out.println(colors);
        System.out.printf( "pop %-12s<- ", colors.pop());
        System.out.println(colors);
        System.out.printf( "pop %-12s<- ", colors.pop());
        System.out.println(colors);
        System.out.printf( "pop %-12s<- ", colors.pop());
        System.out.println(colors);
        System.out.printf( "pop %-12s<- ", colors.pop());
        System.out.println(colors);
        System.out.printf( "peek %-11s<- ", colors.peek());
        System.out.println(colors);
    }

}
