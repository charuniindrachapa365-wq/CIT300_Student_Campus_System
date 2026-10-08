
public class StudentHashTable {

    private static class HashNode {
        Student student;
        HashNode next;

        HashNode(Student student) {
            this.student = student;
        }
    }

    private final HashNode[] table;

    private static final int CAPACITY = 101;

    public StudentHashTable() {
        table = new HashNode[CAPACITY];
    }

    // Calculate hash index
    private int hash(String studentId) {
        return Math.floorMod(studentId.hashCode(), CAPACITY);
    }

    // Insert or update student
    public void insert(Student student) {

        if (student == null ||
                student.getStudentId() == null) {
            return;
        }

        int index = hash(student.getStudentId());

        HashNode current = table[index];

        while (current != null) {

            if (current.student.getStudentId()
                    .equals(student.getStudentId())) {

                current.student = student;
                return;
            }

            current = current.next;
        }

        HashNode newNode = new HashNode(student);

        newNode.next = table[index];
        table[index] = newNode;
    }

    // Search student by ID
    public Student search(String studentId) {

        if (studentId == null) {
            return null;
        }

        int index = hash(studentId);

        HashNode current = table[index];

        while (current != null) {

            if (current.student.getStudentId()
                    .equals(studentId)) {

                return current.student;
            }

            current = current.next;
        }

        return null;
    }

    // Delete student by ID
    public boolean delete(String studentId) {

        if (studentId == null) {
            return false;
        }

        int index = hash(studentId);

        HashNode current = table[index];
        HashNode previous = null;

        while (current != null) {

            if (current.student.getStudentId()
                    .equals(studentId)) {

                if (previous == null) {
                    table[index] = current.next;
                } else {
                    previous.next = current.next;
                }

                return true;
            }

            previous = current;
            current = current.next;
        }

        return false;
    }

    // Display all hash table records
    public void displayAll() {

        System.out.println("\n===== HASH TABLE RECORDS =====");

        boolean found = false;

        for (int i = 0; i < CAPACITY; i++) {

            HashNode current = table[i];

            while (current != null) {

                System.out.println("Bucket Index: " + i);
                current.student.displayStudent();

                found = true;
                current = current.next;
            }
        }

        if (!found) {
            System.out.println("Hash table is empty!");
        }
    }
}
