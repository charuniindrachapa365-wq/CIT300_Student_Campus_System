
public class StudentBST {

    private static class Node {
        Student student;
        Node left;
        Node right;

        Node(Student student) {
            this.student = student;
        }
    }

    private Node root;

    // Insert student into BST
    public void insert(Student student) {
        if (student == null) {
            return;
        }

        root = insertRecursive(root, student);
    }

    private Node insertRecursive(Node node, Student student) {

        if (node == null) {
            return new Node(student);
        }

        int comparison = student.getStudentId()
                .compareTo(node.student.getStudentId());

        if (comparison < 0) {
            node.left = insertRecursive(node.left, student);
        } else if (comparison > 0) {
            node.right = insertRecursive(node.right, student);
        } else {
            // Replace existing student with same ID
            node.student = student;
        }

        return node;
    }

    // Search by Student ID
    public Student search(String studentId) {

        if (studentId == null) {
            return null;
        }

        Node current = root;

        while (current != null) {

            int comparison = studentId.compareTo(
                    current.student.getStudentId());

            if (comparison == 0) {
                return current.student;
            } else if (comparison < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    // Delete student from BST
    public void delete(String studentId) {

        if (studentId == null) {
            return;
        }

        root = deleteRecursive(root, studentId);
    }

    private Node deleteRecursive(Node node, String studentId) {

        if (node == null) {
            return null;
        }

        int comparison = studentId.compareTo(
                node.student.getStudentId());

        if (comparison < 0) {
            node.left = deleteRecursive(node.left, studentId);

        } else if (comparison > 0) {
            node.right = deleteRecursive(node.right, studentId);

        } else {

            // No left child
            if (node.left == null) {
                return node.right;
            }

            // No right child
            if (node.right == null) {
                return node.left;
            }

            // Two children: find inorder successor
            Node successor = findMin(node.right);

            node.student = successor.student;

            node.right = deleteRecursive(
                    node.right,
                    successor.student.getStudentId());
        }

        return node;
    }

    private Node findMin(Node node) {

        while (node.left != null) {
            node = node.left;
        }

        return node;
    }

    // Display in ascending Student ID order
    public void displayInOrder() {

        if (root == null) {
            System.out.println("BST is empty!");
            return;
        }

        System.out.println("\n===== STUDENTS USING BST =====");
        inOrderRecursive(root);
    }

    private void inOrderRecursive(Node node) {

        if (node != null) {
            inOrderRecursive(node.left);
            node.student.displayStudent();
            inOrderRecursive(node.right);
        }
    }
}
