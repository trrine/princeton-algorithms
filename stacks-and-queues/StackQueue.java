public class StackQueue {
    private Stack pushStack;
    private Stack popStack;

    public StackQueue() {
        pushStack = new Stack();
        popStack = new Stack();
    }

    public void enqueue(String item) {
        pushStack.push(item);
    }

    public String dequeue() {
        if (popStack.isEmpty()) reverse();
        
        return popStack.pop();        
    }

    public boolean isEmpty() {
        return (pushStack.isEmpty() && popStack.isEmpty());
    }

    private void reverse() {
        while (!pushStack.isEmpty()) {
            popStack.push(pushStack.pop());
        }
    }


    private class Stack {
        private Node first = null;
    
        private class Node {
            String item;
            Node next;
        }
    
        public boolean isEmpty() {
            return first == null;
        }
        
        public String pop() {
            if (isEmpty()) {
                System.out.println("Stack is empty. Cannot pop.");
                return null;
            }
    
            String item = first.item;
            first = first.next;
            return item;
        }
    
        public void push(String item) {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
        }
    }
} 