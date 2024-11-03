// Simple arithmetic expression evaluator program to demonstrate a use case for stacks
// (Dijkstra's two stack algorithm)

import java.util.Scanner;

public class Evaluate {
    public static void main(String[] args) {
        Evaluate.Stack<String> ops = new Evaluate().new Stack<String>();
        Stack<Double> vals = new Evaluate().new Stack<Double>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter an arithmic expression to be evaluated:");
        String expression = scanner.nextLine();
        scanner.close();

        for (int i = 0; i < expression.length(); i++) {
            String element = String.valueOf(expression.charAt(i));   
            
            if (element.equals("(")) ;   // Ignore
            else if (element.equals("+")) ops.push(element);
            else if (element.equals("*")) ops.push(element);
            else if (element.equals(")")) {
                String op = ops.pop();
                if (op.equals("+")) vals.push(vals.pop() + vals.pop());
                else if (op.equals("*")) vals.push(vals.pop() * vals.pop());
            }
            else vals.push(Double.parseDouble(element));
        }

        System.out.println("Result:");
        System.out.println(vals.pop());
                
    }

    public class Stack<Item> {
        private Node first = null;

        private class Node {
            Item item;
            Node next;
        }

        public boolean isEmpty() {
            return first == null;
        }

        public void push(Item item) {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
        }

        public Item pop() {
            Item item = first.item;
            first = first.next;
            return item;
        }
    }
}
