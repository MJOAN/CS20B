import java.util.Stack;

public class MinStack {
    public static void main(String[] args) {

        MinStack min = new MinStack();

        /** Print outs for our MinStack*/
        System.out.println("Examples demonstrating MinStack functionality: ");
        min.push(10);
        min.push(13);
        min.push(-12);
        System.out.println("The top element is: " + min.top());
        min.pop();
        min.push(-3);
        min.pop();
        min.push(5);
        System.out.println("The top element is: " + min.top());
        System.out.println("The min element is: " + min.getMin ());
        min.push(-7);
        min.push(-14);
        min.pop();
        System.out.println("The top element is: " + min.top());
        System.out.println("The min element is: " + min.getMin());
        min.push(4);
        min.pop();
        System.out.println("The min element is: " + min.getMin());
        min.push(-65);
        System.out.println("The top element is: " + min.top());
        System.out.println("The min element is: " + min.getMin());
        min.push(24);
        min.pop();
        System.out.println("The min element is: " + min.getMin());
        System.out.println("The top element is: " + min.top());

    }
}

/** Purpose: Stack exception handling for MinStack class
 *  Parameters: string message
 *  Returns: Exception error message from method call
 */

class StackException extends Exception {
    public StackException( String message ) {
        super(message);
    }
}

/**
 * Purpose: MinStack builds a LIFO stack using the Java Stack class
 *  with the following methods and global min variable:
 *  minItemValue: global variable keeps track of min item
 *  push(int x): pushes item on top of stack & maintains minItemValue
 *  pop(): removes item from top of stack & maintains minItemValue w/ temp stack
 *  top(): returns item at top of stack
 *  getMin(): returns minItemValue in stack in constant time -> O(1)
 */

class MinStack  {

    /** primitive types should be "wrapped" in object */
    Stack<Integer> items;

    /** global variable for min value in our MinStack */
    int minItemValue = Integer.MIN_VALUE;

    /** instantiate our MinStack constructor for new Stack items */
    public MinStack() {
        items = new Stack<Integer>();
    }

    public void push(Integer x) {

        /** if items size == 0 OR if x is < minItemValue
          * set x to new minItemValue else push(x) */
        if (items.empty () || x < minItemValue)
            minItemValue = x;
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

        /** if itemPopped === minItemValue create temp stack
          * to pop() through and find new min & set as
          * current minItemValue */
        if (itemPopped == minItemValue ) {
            Stack<Integer> temp = (Stack<Integer>) items.clone ();
            while (!temp.empty ()) {
                Integer next = temp.pop ();
                if (next < minItemValue) {
                    minItemValue = next;
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

    public Integer getMin() {
        if (items.empty()) try {
            throw new StackException ( "getMin() is called on an empty stack.");
        } catch (StackException e) {
            e.printStackTrace ();
        }

        /** O(1) with push() and pop() maintaining minItemValue */
        return minItemValue;
    }
}
