
public class StudentStack {

    // Node for stack
    private static class Node {
        String action;
        Node next;

        Node(String action) {
            this.action = action;
            this.next = null;
        }
    }

    private Node top;
    private int size;

    // Constructor
    public StudentStack() {
        top = null;
        size = 0;
    }

    // Push a new action
    public void push(String action) {

        if (action == null || action.trim().isEmpty()) {
            System.out.println("Invalid action!");
            return;
        }

        Node newNode = new Node(action);

        newNode.next = top;
        top = newNode;
        size++;

        System.out.println("Action saved: " + action);
    }

    // Remove the most recent action
    public String pop() {

        if (isEmpty()) {
            System.out.println("Action history is empty!");
            return null;
        }

        String action = top.action;
        top = top.next;
        size--;

        return action;
    }

    // View most recent action
    public String peek() {

        if (isEmpty()) {
            return null;
        }

        return top.action;
    }

    // Check whether stack is empty
    public boolean isEmpty() {
        return top == null;
    }

    // Display recent actions
    public void displayRecentActions() {

        if (isEmpty()) {
            System.out.println("No recent actions!");
            return;
        }

        System.out.println("\n===== RECENT ACTION HISTORY =====");

        Node current = top;
        int number = 1;

        while (current != null) {
            System.out.println(number + ". " + current.action);
            current = current.next;
            number++;
        }
    }

    public int getSize() {
        return size;
    }
}
