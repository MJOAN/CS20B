import java.util.Stack;

public class MaxStack {
    public static void main(String[] args) {

        MaxStack max = new MaxStack();

        /** Print outs for our MaxStack */
        System.out.println("Examples demonstrating MaxStack functionality: ");
        max.push(47);
        max.push(41);
        max.pop();
        max.push(34);
        max.push(23);
        System.out.println("The top element is: " + max.top());
        max.push(64);
        System.out.println("The max element is: " + max.getMax());
        max.push(89);
        max.push(43);
        max.pop();
        System.out.println("The top element is: " + max.top());
        System.out.println("The max element is: " + max.getMax());
        max.push(97);
        System.out.println("The max element is: " + max.getMax());
        max.push(120);
        max.pop();
        max.push(124);
        System.out.println("The top element is: " + max.top());
        System.out.println("The max element is: " + max.getMax());
        max.push(46);
        max.pop();
        max.push(424);
        max.push(12);
        System.out.println("The max element is: " + max.getMax());
        System.out.println("The top element is: " + max.top());
    }
}

/** Purpose: Stack exception handling for MaxStack class
 *  Parameters: string message
 *  Returns: Exception error message from method call
 */

class StackException extends Exception {
    public StackException( String message ) {
        super(message);
    }
}

/**
 * Purpose: MaxStack builds a LIFO stack using the Java Stack class
 *  with the following methods and global max variable:
 *  maxItemValue: global variable keeps track of max item
 *  push(int x): pushes item on top of stack & maintains maxItemValue
 *  pop(): removes item from top of stack & maintains maxItemValue w/ temp stack
 *  top(): returns top item
 *  getMax(): returns maxItemValue in constant time -> O(1)
 */

class MaxStack  {

    /** primitive types should be "wrapped" in object */
    Stack<Integer> items;

    /** global variable for max value in our MaxStack */
    int maxItemValue = Integer.MIN_VALUE;

     /** instantiate our MaxStack constructor for new Stack items */
    public MaxStack() {
        items = new Stack<Integer>();
    }

    public void push(Integer x) {

        /** if MaxStack size == 0 OR if x is > maxItemValue
         *  set x to new maxItemValue */
        if (items.empty () || x > maxItemValue)
            maxItemValue = x;
        items.push ( x );
    }

    public void pop() {
        if (items.empty()) try {
            throw new StackException ("pop() is called on an empty stack.");
        } catch (StackException e) {
            e.printStackTrace ();
        }

        /** store item to be popped at top of stack */
        Integer itemPopped = items.peek();

        /** native pop() method */
        items.pop();

        /** if itemPopped === maxItemValue create temp stack
         *  to pop() through finding new min & set as
         *  current maxItemValue */
        if (itemPopped == maxItemValue ) {
            Stack<Integer> temp = (Stack<Integer>) items.clone ();
            while (!temp.empty ()) {
                Integer next = temp.pop ();
                if (next > maxItemValue) {
                    maxItemValue = next;
                }
            }
        }
    }

    public Integer top() {
        if (items.empty()) try {
            throw new StackException ( "top() is called on an empty stack." );
        } catch (StackException e) {
            e.printStackTrace ();
        }
        /** native peek() method */
        return items.peek();
    }

    public Integer getMax() {
        if (items.empty()) try {
            throw new StackException ( "getMax() is called on an empty stack.");
        } catch (StackException e) {
            e.printStackTrace ();
        }

        /** O(1) with push() and pop() maintaining maxItemValue */
        return maxItemValue;
    }
}