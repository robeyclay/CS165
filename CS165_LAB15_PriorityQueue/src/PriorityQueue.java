import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class PriorityQueue {

    /* This class is finished for you.
     */
    public static class Customer implements Comparable<Customer> {
        private double donation;

        public Customer(double donation) {
            this.donation = donation;
        }

        public double getDonation() {
            return donation;
        }

        public void donate(double amount) {
            donation += amount;
        }

        public int compareTo(Customer other) {
            double diff = donation - other.donation;
            if (diff < 0) {
                return -1;
            } else if (diff > 0) {
                return 1;
            } else {
                return 0;
            }
        }

        public String toString() {
            return String.format("$%.2f", donation);
        }
    }

    private Customer[] data;
    private int size;
    private int capacity;

    public PriorityQueue(int capacity) {
        data = new Customer[capacity];
        size = 0;
        this.capacity = capacity;
    }

    public PriorityQueue() {
        this(10);
    }

    /* Add a customer to the queue.
     * Remember to do so in a way that keeps the queue in sorted order!
     */
    public void push(Customer customer) {
        if(getSize() == getCapacity()) {
            throw new IllegalStateException();
        }
        data[size] = customer;
        organize();
        size++;
    }

    /* Remove and return the highest priority customer from the queue.
     */
    public Customer pop() {
        if(getSize() == 0) {
            throw new NoSuchElementException();
        }
        Customer temp = data[0];
        if(getSize() == getCapacity()) {
            size--;
            data[size] = null;
            size++;
        } else {
            System.arraycopy(data, 1, data, 0, getSize());
        }
        size--;
        return temp;
    }

    /* Return, but don't remove, the highest priority item from the queue.
     */
    public Customer peek() {
        if(getSize() == 0) {
            throw new NoSuchElementException();
        }
        return data[0];
    }

    /* Given the index of a customer, increase their donation amount, letting
     * them cut in line if necessary.
     *
     * Refer to the Customer class to remind yourself the operations you can do
     * on a customer.
     */
    public void bump(int customerIndex, double amount) {
        data[customerIndex].donate(amount);
        if(getSize() == getCapacity()) {
            size--;
            organize();
            size++;
        } else {
            organize();
        }

    }

    public void organize() {
        for(int i = size; i > 0; i--){
            if(data[i].compareTo(data[i - 1]) > 0) {
                Customer temp = data[i];
                data[i] = data[i - 1];
                data[i - 1] = temp;
            }
        }
    }

    public String toString() {
        return Arrays.toString(data);
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    public static void main(String[] args) {
        PriorityQueue line = new PriorityQueue(6);

        System.out.println("Testing push");

        line.push(new Customer(5.00));
        line.push(new Customer(10.00));
        line.push(new Customer(1.00));

        System.out.println("Line should be:\n[$10.00, $5.00, $1.00, null, null, null]");
        System.out.println(line);

        System.out.println("Line size should be 3 is: " + line.getSize());

        System.out.println();

        System.out.println("Testing pop");

        System.out.println(line.pop());
        System.out.println(line.pop());
        System.out.println(line.pop());

        System.out.println();

        System.out.println("Testing bump");

        line.push(new Customer(15.00));
        line.push(new Customer(20.00));
        line.push(new Customer(7.00));
        line.push(new Customer(5.00));
        line.push(new Customer(2.00));
        line.push(new Customer(49.00));

        System.out.println(line.toString());
        line.bump(1, 30.00);
        line.bump(3, 60.00);
        System.out.println(line.toString());
        System.out.println(line.pop());
        System.out.println(line.peek());
        System.out.println(line.pop());
        System.out.println(line.pop());
        System.out.println(line.pop());
        System.out.println(line.pop());
        System.out.println(line.pop());


        System.out.println();

        line.push(new Customer(7.00));
        line.push(new Customer(8.00));
        line.push(new Customer(9.00));
        line.push(new Customer(7.00));
        line.push(new Customer(10.00));

        System.out.println("Line should be:\n[$10.00, $9.00, $8.00, $7.00, $7.00, null]");
        System.out.println(line);
    }
}