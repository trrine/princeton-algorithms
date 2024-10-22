import java.util.Scanner;

public class LinkedQueueOfStrings {
    private Node first, last;

    private class Node {
        String item;
        Node next;
    }

    public LinkedQueueOfStrings() {
        first = null;
        last = null;
    }

    public void enqueue(String item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;

        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
    }

    public String dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty. Cannot dequeue.");
            return null;
        }

        String item = first.item;
        first = first.next;

        if (isEmpty()) {
            last = null;
        }

        return item;
    }

    public boolean isEmpty() {
        return first == null;
    }

    // Testing    
    public static void main(String[] args) {
        LinkedQueueOfStrings queue = new LinkedQueueOfStrings();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a value or type - to get the last value:");

        while (scanner.hasNext()) {
            String s = scanner.next();

            if (s.equals("-")) {
                String elem = queue.dequeue();
                
                if (elem != null) {
                    System.out.println(elem);
                }
            } else if (s.equals("exit")) {
                break;
            } else {
                queue.enqueue(s);
            }   
        }
        scanner.close();
    }
}