import java.util.Scanner;


public class LinkedStackOfStrings {
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
    
    // Testing    
    public static void main(String[] args) {
        StackOfStrings stack = new StackOfStrings();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a value or type - to get the last value:");

        while (scanner.hasNext()) {
            String s = scanner.next();

            if (s.equals("-")) {
                String popped = stack.pop();
                
                if (popped != null) {
                    System.out.println(popped);
                }
            } else if (s.equals("exit")) {
                break;
            } else {
                stack.push(s);
            }   
        }
        scanner.close();
    }
}


