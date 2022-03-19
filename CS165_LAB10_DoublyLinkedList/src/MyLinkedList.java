/** Linked List Lab
 * Made by Toby Patterson 5/31/2020
 * For CS165 at CSU
 */

public class MyLinkedList<E> implements MiniList<E>{
    /* Private member variables that you need to declare:
     ** The head pointer
     ** The tail pointer
     */

    private Node head;
    private Node tail;
    private int size;

    public class Node {
        // declare member variables (data and next)
        public E data;
        public Node next;
        public Node prev;
        // finish these constructors
        public Node(E data, Node prev, Node next) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
        public Node(E data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        } // HINT: use this() with next = null

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public E getData() {
            return data;
        }

        public void setData(E data) {
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
    public boolean add(E item) {
        Node newNode = new Node(item);
        if(head == null) {
            head = newNode;
            head.setPrev(null);
            head.setNext(null);
            tail = head;
        } else {
            Node curr = tail;
            tail.setNext(newNode);
            tail = newNode;
            tail.setPrev(curr);
        }
        size++;
        return true;
    }

    @Override
    public void add(int index, E element) {
        Node curr = head;
        if(index < 0 || index > size)
        {
            throw new IndexOutOfBoundsException();
        }
        for(int i = 0; i <= size; i ++) {
            if(index == 0) {
                Node newNode = new Node(element, null, curr);
                head = newNode;
                break;
            } else if (index - 1 == i) {
                if(curr == tail) {
                    Node newNode = new Node(element);
                    curr.setNext(newNode);
                    tail = newNode;
                    tail.setPrev(curr);
                    break;
                } else {
                    Node newNode = new Node(element, curr.getPrev(), curr.getNext());
                    curr.setNext(newNode);
                    newNode.setPrev(curr);
                    break;
                }
            }
            curr = curr.getNext();
        }
        size++;
    }

    @Override
    public E remove() {
        Node curr = head;
        head = curr.getNext();
        head.setPrev(null);
        size--;
        return head.getData();
    }

    @Override
    public E remove(int index) {
        Node curr = head;
        Node rtn = head;
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        for( int i = 0; i < size; i++) {
            if(index == 0) {
                head = curr.getNext();
                head.setPrev(null);
                break;
            } else if (index - 1 == i) {
                if (curr.getNext() == tail) {
                    rtn = curr.getNext();
                    curr.setNext(null);
                    tail = curr;
                    break;
                } else {
                    rtn = curr.getNext();
                    Node next = curr.getNext().getNext();
                    curr.setNext(next);
                    next.setPrev(curr);
                    break;
                }
            }
            curr = curr.getNext();
        }
        size--;
        return rtn.getData();
    }

    @Override
    public boolean remove(E item) {
        Node curr = head;
        boolean rtn = false;
        for (int i = 0; i < size; i++){
            if(head.getData() == item) {
                head = curr.getNext();
                head.setPrev(null);
                rtn = true;
                break;
            } else if (curr.getNext() == tail) {
                curr.setNext(null);
                tail = curr;
                rtn = true;
                break;
            } else if (curr.getNext().getData() == item) {
                Node next = curr.getNext().getNext();
                curr.setNext(next);
                next.setPrev(curr);
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
        size = 0;
    }

    @Override
    public boolean contains(E item) {
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
    public E get(int index) {
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
    public int indexOf(E item) {
        Node curr = head;
        if(head != null)
        {
            for (int i = 0; i < size; i++)
            {
                if (curr.getData() == item)
                {
                    return i;
                }
                curr = curr.getNext();
            }
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        if(head == null)
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