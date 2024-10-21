import java.util.Scanner;

public class FixedCapacityStackOfStrings {
    private String[] s;
    private int N = 0;

    public FixedCapacityStackOfStrings(int capacity) {
        s = new String[capacity];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void push(String item) {
        if (N < s.length) {
            s[N++] = item;
        } else {
            System.out.println("Stack is full. Cannot push more items.");
        }
    }

    public String pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty. Cannot pop.");
            return null;
        }
        String item = s[--N];
        s[N] = null; // Avoid loitering (helps with garbage collection)
        return item;
    }

    // Testing    
    public static void main(String[] args) {
        FixedCapacityStackOfStrings stack = new FixedCapacityStackOfStrings(10);
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