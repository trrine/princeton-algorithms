public class StackWithMax {
    private Node first;
    Stack helperStack;

    private class Node {
        double item;
        Node next;
    }

    public StackWithMax() {
        first = null;
        helperStack = new Stack();
    }

    public boolean isEmpty() {
        return first == null;
    }
    
    public double pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty. Cannot pop.");
            return Double.NaN;
        }

        helperStack.pop();
        double item = first.item;
        first = first.next;
        return item;
    }

    public void push(double item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;

        if (helperStack.isEmpty()) {
            helperStack.push(item);

        } else {
            double currentMax = helperStack.peek();

            if (item > currentMax) helperStack.push(item);   
            else helperStack.push(currentMax);
        }
    }

    public double max() {
        if (helperStack.isEmpty()) {
            System.out.println("Stack is empty. Cannot get max.");
            return Double.NaN;
        }

        return helperStack.peek();
    }



    private class Stack {
        private Node first = null;
    
        private class Node {
            double item;
            Node next;
        }
    
        public boolean isEmpty() {
            return first == null;
        }
        
        public double pop() {
            if (isEmpty()) {
                System.out.println("Stack is empty. Cannot pop.");
                return Double.NaN;
            }
    
            double item = first.item;
            first = first.next;
            return item;
        }
    
        public void push(double item) {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
        }

        public double peek() {
            if (isEmpty()) {
                System.out.println("Stack is empty. Cannot peek.");
                return Double.NaN;
            }

            return first.item;
        }
    }
}