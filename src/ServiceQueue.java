
public class ServiceQueue {

    // Queue node
    private static class Node {
        String requestId;
        String studentId;
        String description;
        Node next;

        Node(String requestId, String studentId,
                String description) {

            this.requestId = requestId;
            this.studentId = studentId;
            this.description = description;
            this.next = null;
        }
    }

    private Node front;
    private Node rear;
    private int size;

    public ServiceQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    // Add a service request
    public boolean enqueue(String requestId,
            String studentId,
            String description) {

        if (requestId == null || requestId.trim().isEmpty() ||
                studentId == null || studentId.trim().isEmpty() ||
                description == null || description.trim().isEmpty()) {

            System.out.println("Invalid service request!");
            return false;
        }

        Node newNode = new Node(
                requestId, studentId, description);

        if (rear == null) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }

        size++;

        System.out.println(
                "Service request added: " + requestId);

        return true;
    }

    // Process the next request
    public String dequeue() {

        if (isEmpty()) {
            System.out.println("No pending service requests!");
            return null;
        }

        Node processed = front;
        front = front.next;

        if (front == null) {
            rear = null;
        }

        size--;

        System.out.println("\n===== PROCESSING REQUEST =====");
        System.out.println("Request ID  : " + processed.requestId);
        System.out.println("Student ID  : " + processed.studentId);
        System.out.println("Description : " + processed.description);

        return processed.requestId;
    }

    // Display pending requests
    public void displayQueue() {

        if (isEmpty()) {
            System.out.println("No pending requests!");
            return;
        }

        System.out.println("\n===== PENDING SERVICE REQUESTS =====");

        Node current = front;

        while (current != null) {
            System.out.println("----------------------------");
            System.out.println("Request ID : " + current.requestId);
            System.out.println("Student ID : " + current.studentId);
            System.out.println("Service    : " + current.description);

            current = current.next;
        }
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int getSize() {
        return size;
    }
}
