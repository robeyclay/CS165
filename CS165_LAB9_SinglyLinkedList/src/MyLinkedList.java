/** Linked List Lab
 * Made by Toby Patterson 5/29/2020
 * For CS165 at CSU
 */

public class MyLinkedList implements MiniList<Integer>{
    /* Private member variables that you need to declare:
     ** The head pointer
     ** The tail pointer
     */
    private Node head;
    private Node tail;
    private int size;

    public class Node {
        // declare member variables (data and next)
        public int data;
        public Node next;
        // finish these constructors
        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
        public Node(int data) {
            this.data = data;
            this.next = null;
        } // HINT: use this() with next = null

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }
    }

    // Initialize the linked list (set head and tail pointers)
    public MyLinkedList() {
        this.head = null;
        this.tail = null;
        size = 0;
    }

    @Override
    public boolean add(Integer item) {
        Node newNode = new Node(item);
        if(head == null) {
            head = newNode;
            tail = head;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
        return true;
    }

    @Override
    public void add(int index, Integer element) {
        Node curr = head;
        if(index < 0 || index > size)
        {
            throw new IndexOutOfBoundsException();
        }
        for(int i = 0; i <= size; i ++) {
            if(index == 0) {
                Node newNode = new Node(element, curr);
                head = newNode;
                break;
            } else if (index - 1 == i) {
                if(curr == tail) {
                    Node newNode = new Node(element);
                    curr.setNext(newNode);
                    tail = newNode;
                    break;
                } else {
                    Node newNode = new Node(element, curr.getNext());
                    curr.setNext(newNode);
                    break;
                }
            }
            curr = curr.getNext();
        }
        size++;
    }

    @Override
    public Integer remove() {
        Node curr = head;
        head = curr.getNext();
        size--;
        return head.getData();
    }

    @Override
    public Integer remove(int index) {
        Node curr = head;
        Node rtn = head;
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        for( int i = 0; i < size; i++) {
            if(index == 0) {
                head = curr.getNext();
                break;
            } else if (index - 1 == i) {
                if (curr.getNext() == tail) {
                    rtn = curr.getNext();
                    curr.setNext(null);
                    tail = curr;
                    break;
                } else {
                    rtn = curr.getNext();
                    curr.setNext(curr.getNext().getNext());
                    break;
                }
            }
            curr = curr.getNext();
        }
        size--;
        return rtn.getData();
    }

    @Override
    public boolean remove(Integer item) {
        Node curr = head;
        boolean rtn = false;
        for (int i = 0; i < size; i++){
            if(head.getData() == item) {
                head = curr.getNext();
                rtn = true;
                break;
            } else if (curr.getNext() == tail) {
                curr.setNext(null);
                tail = curr;
                rtn = true;
                break;
            } else if (curr.getNext().getData() == item) {
                curr.setNext(curr.getNext().getNext());
                rtn = true;
                break;
            }
            curr = curr.getNext();
        }
        size--;
        return rtn;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
    }

    @Override
    public boolean contains(Integer item) {
        Node curr = head;
        for(int i = 0; i < size; i++) {
            if(curr.getData() == item)
            {
                return true;
            }
            curr = curr.getNext();
        }
        return false;
    }

    @Override
    public Integer get(int index) {
        Node curr = head;
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        for(int i = 0; i < size; i++) {
            if(index == i) {
                return curr.getData();
            }
            curr = curr.getNext();
        }
        return null;
    }

    @Override
    public int indexOf(Integer item) {
        Node curr = head;
        for(int i = 0; i < size; i++)
        {
            if(curr.getData() == item)
            {
                return i;
            }
            curr = curr.getNext();
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        if(head != tail)
        {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    // Uncomment when ready to test
    @Override
    public String toString() {
        String ret = "";
        Node curr = head;
        while (curr != null) {
            ret += curr.data + " ";
            curr = curr.next;
        }
        return ret;
    }

}