import java.util.List;
import java.util.ArrayList;


public class GenericArrayList <E>{
    /* YOUR CODE HERE
     * Copy your code from your ArrayStringList class, and place it within 
     * this class.
     *
     */

     // Place code here
     /* This field is really important!
      * This is the internal array of data you're going to use to implement the
      * ArrayStringList. This is what actually STORES the Strings in your list.
      * More information about how this should be used is in the lab writeup.
      * Read it first!
      */
     private E[] data;

    /* Storing the amount of valid Strings that are in the array turns out to be
     * fairly useful. This variable is for that.
     */
    private int size;

    /* YOUR CODE HERE
     * From this point onward, you're going to see a bunch of empty methods. It
     * is your job to fill them out (or in fancy CS terminology, "implement"
     * them), so they do what the comments say they should.
     *
     * Remember, the comments are not telling you HOW to implement a method.
     * They are telling you WHAT the method should do. The HOW of each of these
     * methods is going to be a combination of your own creativity and the
     * guidelines laid out in the lab writeup.
     */

    /* This method is mostly here for your own benefit. You may be resizing the
     * array in several places (in both of the add methods, for instance), and
     * whenever we are doing the same thing in multiple places, it's usually a
     * good idea to put it into a function, so it can be easily reused.
     *
     * This method should change the size of that data array to whatever the
     * newSize is. It should keep the original data intact as well. I recommend
     * you start by creating a totally new String[] array of the desired size,
     * then copying over the elements from the data array to this new array,
     * then when that is done, replacing the data array with the new one.
     */
    public void resizeData(int newSize) {
        E[] tempArr = (E[]) new Object[newSize];
        for(int i = 0; i < data.length; i++)
        {
            tempArr[i] = data[i];
        }
        data = tempArr;
    }

    /* Remember: An uninitialized field is a bad one. In this constructor, you
     * should initialize data and size, using the initialCapacity as the,
     * well, initial capacity of the data array.
     *
     * Consider the distinction we make between size and capacity! The capacity
     * is the size of the internal array, while the size is the amount of
     * Strings that are actually in the array.
     */
    public GenericArrayList(int initialCapacity) {
        data =(E[]) new Object[initialCapacity];
        size = 0;

    }

    /* This method should add a string to the END of your ArrayList.
     * For instance, if there are 5 elements, this should go into index 5 (the
     * sixth spot).
     */
    public void add(E str) {
        if(size == data.length) {
            resizeData(data.length * 2);
        }
        data[size] = str;
        size++;
    }

    /* This method should add a string to a specific index in your ArrayList.
     *
     * The index may not be valid. For instance, calling this with an index of
     * 10 on an ArrayList that only has 7 elements is not allowed.
     * If the index is out of bounds, stop the method without doing anything.
     */
    public void add(int index, E str) {
        E[] tempStrArr = (E[]) new Object[size + 1];
        int i = 0;
        if(index > -1 && index < size)
        {
            for(E r : data) {
                if (i == index) {
                    tempStrArr[i] = str;
                } else {
                    tempStrArr[i] = r;
                }
                i++;
            }
            if(data.length == size)
            {
                resizeData((size + 2) * 2);
            }
            data = tempStrArr;
            size++;
        }
    }

    /* This method should return the string stored at a certain index.
     * Like the method above, the index may not be valid. Return null if the
     * index given is out of bounds.
     */
    public E get(int index) {
        if(index > -1 && index < size) {
            return data[index];
        } else {
            return null;
        }
    }

    /* This method should take the string at a given index out of your
     * ArrayList.
     * If the index isn't valid, then stop the method without doing anything.
     */
    public void remove(int index) {
        E[] tempStrArr = (E[]) new Object[data.length];

        int i = 0;
        for(i = 0; i < data.length; i ++) {

            if (i == index) {
                tempStrArr[i] = data[i + 1];
                i++;
            }else if (i > index && i + 1 < data.length){
                tempStrArr[i] = data[i + 1];
            }else {
                tempStrArr[i] = data[i];
            }
        }
        data = tempStrArr;
        size--;
    }

    /* This method should return how many elements are in your ArrayList.
     * Hint: You should already be storing this in a variable called size.
     */
    public int size() {
        return size;
    }

    /* This method should return true if the given string is in your ArrayList,
     * and false otherwise.
     * Remember to use .equals() instead of == when comparing one String
     * with another.
     */
    public boolean contains(E str) {
        boolean set = false;
        for(E r : data) {
            if (r != null) {
                if (r.equals(str)) {
                    set = true;
                    break;
                } else {
                    set = false;
                }
            }
        }
        return set;
    }






    public static void main(String[] args) {
        /* PART 1:
         * Modify the GenericArrayList above so that it can store *any* class,
         * not just strings.
         * When you've done that, uncomment the block of code below, and see if
         * it compiles. If it does, run it. If there are no errors, you did
         * it right!
         */

        // Uncomment me when you're done
        GenericArrayList<Point> pointList = new GenericArrayList<Point>(2);

        pointList.add(new Point(0, 0));
        pointList.add(new Point(2, 2));
        pointList.add(new Point(7, 0));
        pointList.add(new Point(19.16f, 22.32f));



        pointList.remove(0);
        Point p = pointList.get(2);



        if (p.x != 19.16f && p.y != 22.32f) {
            throw new AssertionError("Your GenericArrayList compiled properly "
            + "but is not correctly storing things. Make sure you didn't "
            + "accidentally change any of your ArrayStringList code, aside "
            + "from changing types.");
        }

        GenericArrayList<Float> floatList = new GenericArrayList<Float>(2);

        for (float f = 0.0f; f < 100.0f; f += 4.3f) {
            floatList.add(f);
        }

        float f = floatList.get(19);

        System.out.println("Hurray, everything worked!");



       

    }
}

