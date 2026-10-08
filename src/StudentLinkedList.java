
public class StudentLinkedList {

    // Node class
    private static class Node {
        Student student;
        Node next;

        Node(Student student) {
            this.student = student;
            this.next = null;
        }
    }

    private Node head;

    // Add a new student
    public boolean addStudent(Student student) {

        if (student == null ||
                student.getStudentId() == null ||
                student.getStudentId().trim().isEmpty()) {
            System.out.println("Invalid student record!");
            return false;
        }

        if (searchStudent(student.getStudentId()) != null) {
            System.out.println("Error: Student ID already exists!");
            return false;
        }

        if (student.getName() == null ||
                student.getName().trim().isEmpty() ||
                student.getProgramme() == null ||
                student.getProgramme().trim().isEmpty() ||
                !Double.isFinite(student.getMarks()) ||
                student.getMarks() < 0 ||
                student.getMarks() > 100) {
            System.out.println("Invalid student details!");
            return false;
        }

        Node newNode = new Node(student);

        if (head == null) {
            head = newNode;
        } else {
            Node current = head;

            while (current.next != null) {
                current = current.next;
            }

            current.next = newNode;
        }

        System.out.println("Student added successfully!");
        return true;
    }

    // Search student by ID
    public Student searchStudent(String studentId) {

        if (studentId == null) {
            return null;
        }

        Node current = head;

        while (current != null) {
            if (current.student.getStudentId()
                    .equals(studentId)) {
                return current.student;
            }

            current = current.next;
        }

        return null;
    }

    // Update existing student
    public boolean updateStudent(String studentId,
            String name,
            String programme,
            double marks) {

        Student student = searchStudent(studentId);

        if (student == null) {
            System.out.println("Student not found!");
            return false;
        }

        if (name == null || name.trim().isEmpty() ||
                programme == null || programme.trim().isEmpty() ||
                !Double.isFinite(marks) ||
                marks < 0 || marks > 100) {
            System.out.println("Invalid student details!");
            return false;
        }

        student.setName(name);
        student.setProgramme(programme);
        student.setMarks(marks);

        System.out.println("Student updated successfully!");
        return true;
    }

    // Delete student
    public boolean deleteStudent(String studentId) {

        if (head == null) {
            System.out.println("Student list is empty!");
            return false;
        }

        if (studentId == null) {
            System.out.println("Invalid Student ID!");
            return false;
        }

        if (head.student.getStudentId().equals(studentId)) {
            head = head.next;
            System.out.println("Student deleted successfully!");
            return true;
        }

        Node current = head;

        while (current.next != null) {

            if (current.next.student.getStudentId()
                    .equals(studentId)) {

                current.next = current.next.next;

                System.out.println(
                        "Student deleted successfully!");
                return true;
            }

            current = current.next;
        }

        System.out.println("Student not found!");
        return false;
    }

    // Display all students
    public void displayAllStudents() {

        if (head == null) {
            System.out.println("No student records available!");
            return;
        }

        System.out.println("\n===== ALL STUDENT RECORDS =====");

        Node current = head;

        while (current != null) {
            current.student.displayStudent();
            current = current.next;
        }
    }

    // Count total students
    public int countStudents() {

        int count = 0;
        Node current = head;

        while (current != null) {
            count++;
            current = current.next;
        }

        return count;
    }
}
