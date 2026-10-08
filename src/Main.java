
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    // Data structures
    private static final StudentLinkedList studentList = new StudentLinkedList();

    private static final StudentStack actionStack = new StudentStack();

    private static final ServiceQueue serviceQueue = new ServiceQueue();

    private static final StudentBST studentBST = new StudentBST();

    private static final StudentHashTable hashTable = new StudentHashTable();

    private static final CampusGraph campusGraph = new CampusGraph();

    private static int nextRequestNumber = 1;

    public static void main(String[] args) {

        int choice;

        do {
            displayMenu();

            choice = readInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    updateStudent();
                    break;

                case 3:
                    deleteStudent();
                    break;

                case 4:
                    studentList.displayAllStudents();
                    break;

                case 5:
                    addServiceRequest();
                    break;

                case 6:
                    processServiceRequest();
                    break;

                case 7:
                    actionStack.displayRecentActions();
                    break;

                case 8:
                    studentBST.displayInOrder();
                    break;

                case 9:
                    searchStudentHashing();
                    break;

                case 10:
                    addCampusLocation();
                    break;

                case 11:
                    removeCampusLocation();
                    break;

                case 12:
                    addCampusConnection();
                    break;

                case 13:
                    removeCampusConnection();
                    break;

                case 14:
                    campusGraph.displayConnections();
                    break;

                case 15:
                    traverseCampus();
                    break;

                case 16:
                    System.out.println(
                            "\nThank you for using the system!");
                    break;

                default:
                    System.out.println(
                            "Invalid menu choice! Choose 1-16.");
            }

        } while (choice != 16);

        scanner.close();
    }

    // =====================================
    // MAIN MENU
    // =====================================

    private static void displayMenu() {

        System.out.println(
                "\n==========================================");
        System.out.println(
                " UNIVERSITY STUDENT & CAMPUS MANAGEMENT");
        System.out.println(
                "==========================================");

        System.out.println("1.  Add Student Record");
        System.out.println("2.  Update Student Record");
        System.out.println("3.  Delete Student Record");
        System.out.println("4.  Display All Students");
        System.out.println("5.  Add Service Request");
        System.out.println("6.  Process Next Service Request");
        System.out.println("7.  Display Recent Actions");
        System.out.println("8.  Display Students using BST");
        System.out.println("9.  Search Student using Hashing");
        System.out.println("10. Add Campus Location");
        System.out.println("11. Remove Campus Location");
        System.out.println("12. Add Campus Connection");
        System.out.println("13. Remove Campus Connection");
        System.out.println("14. Display Campus Connections");
        System.out.println("15. Traverse Campus using BFS/DFS");
        System.out.println("16. Exit");

        System.out.println(
                "==========================================");
    }

    // =====================================
    // INPUT VALIDATION
    // =====================================

    private static int readInt(String message) {

        while (true) {

            System.out.print(message);
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(
                        "Invalid input! Enter a whole number.");
            }
        }
    }

    private static double readMarks() {

        while (true) {

            System.out.print("Enter Marks (0-100): ");
            String input = scanner.nextLine().trim();

            try {
                double marks = Double.parseDouble(input);

                if (Double.isFinite(marks) &&
                        marks >= 0 && marks <= 100) {

                    return marks;
                }

                System.out.println(
                        "Marks must be between 0 and 100!");

            } catch (NumberFormatException e) {
                System.out.println(
                        "Invalid marks! Enter a valid number.");
            }
        }
    }

    private static String readText(String message) {

        while (true) {

            System.out.print(message);

            String value = scanner.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println(
                    "Input cannot be empty!");
        }
    }

    // =====================================
    // 1. ADD STUDENT
    // =====================================

    private static void addStudent() {

        System.out.println("\n===== ADD STUDENT =====");

        String id = readText("Student ID: ");

        if (studentList.searchStudent(id) != null) {
            System.out.println(
                    "Error: Student ID already exists!");
            return;
        }

        String name = readText("Student Name: ");
        String programme = readText("Programme: ");
        double marks = readMarks();

        Student student = new Student(
                id, name, programme, marks);

        if (studentList.addStudent(student)) {

            studentBST.insert(student);
            hashTable.insert(student);

            actionStack.push("Added Student " + id);
        }
    }

    // =====================================
    // 2. UPDATE STUDENT
    // =====================================

    private static void updateStudent() {

        System.out.println("\n===== UPDATE STUDENT =====");

        String id = readText("Enter Student ID: ");

        Student existing = studentList.searchStudent(id);

        if (existing == null) {
            System.out.println("Student not found!");
            return;
        }

        existing.displayStudent();

        String name = readText("New Name: ");
        String programme = readText("New Programme: ");
        double marks = readMarks();

        if (studentList.updateStudent(
                id, name, programme, marks)) {

            // All indexes reference the same Student object
            actionStack.push("Updated Student " + id);
        }
    }

    // =====================================
    // 3. DELETE STUDENT
    // =====================================

    private static void deleteStudent() {

        System.out.println("\n===== DELETE STUDENT =====");

        String id = readText("Enter Student ID: ");

        Student existing = studentList.searchStudent(id);

        if (existing == null) {
            System.out.println("Student not found!");
            return;
        }

        if (studentList.deleteStudent(id)) {

            studentBST.delete(id);
            hashTable.delete(id);

            actionStack.push("Deleted Student " + id);

            System.out.println(
                    "Student removed from all structures.");
        }
    }

    // =====================================
    // 5. ADD SERVICE REQUEST
    // =====================================

    private static void addServiceRequest() {

        System.out.println("\n===== ADD SERVICE REQUEST =====");

        String studentId = readText("Student ID: ");

        if (studentList.searchStudent(studentId) == null) {
            System.out.println(
                    "Student does not exist!");
            return;
        }

        String description = readText(
                "Service Description: ");

        String requestId = String.format(
                "REQ%03d", nextRequestNumber);

        if (serviceQueue.enqueue(
                requestId, studentId, description)) {

            nextRequestNumber++;

            actionStack.push(
                    "Added Service Request " + requestId);
        }
    }

    // =====================================
    // 6. PROCESS SERVICE REQUEST
    // =====================================

    private static void processServiceRequest() {

        System.out.println(
                "\n===== PROCESS SERVICE REQUEST =====");

        String requestId = serviceQueue.dequeue();

        if (requestId != null) {
            actionStack.push(
                    "Processed Service Request " + requestId);
        }
    }

    // =====================================
    // 9. HASHING SEARCH
    // =====================================

    private static void searchStudentHashing() {

        System.out.println(
                "\n===== HASH TABLE STUDENT SEARCH =====");

        String id = readText("Enter Student ID: ");

        Student student = hashTable.search(id);

        if (student == null) {
            System.out.println("Student not found!");
        } else {
            System.out.println("Student found!");
            student.displayStudent();
        }
    }

    // =====================================
    // 10. ADD CAMPUS LOCATION
    // =====================================

    private static void addCampusLocation() {

        String location = readText("Location Name: ");

        if (campusGraph.addLocation(location)) {
            actionStack.push(
                    "Added Campus Location " + location);
        }
    }

    // =====================================
    // 11. REMOVE CAMPUS LOCATION
    // =====================================

    private static void removeCampusLocation() {

        String location = readText(
                "Location to Remove: ");

        if (campusGraph.removeLocation(location)) {
            actionStack.push(
                    "Removed Campus Location " + location);
        }
    }

    // =====================================
    // 12. ADD CAMPUS CONNECTION
    // =====================================

    private static void addCampusConnection() {

        String from = readText("From Location: ");
        String to = readText("To Location: ");

        if (campusGraph.addConnection(from, to)) {
            actionStack.push(
                    "Added Connection " + from + " - " + to);
        }
    }

    // =====================================
    // 13. REMOVE CAMPUS CONNECTION
    // =====================================

    private static void removeCampusConnection() {

        String from = readText("From Location: ");
        String to = readText("To Location: ");

        if (campusGraph.removeConnection(from, to)) {
            actionStack.push(
                    "Removed Connection " + from + " - " + to);
        }
    }

    // =====================================
    // 15. BFS / DFS TRAVERSAL
    // =====================================

    private static void traverseCampus() {

        System.out.println("\n===== GRAPH TRAVERSAL =====");
        System.out.println("1. BFS Traversal");
        System.out.println("2. DFS Traversal");

        int choice = readInt("Select traversal: ");

        if (choice != 1 && choice != 2) {
            System.out.println(
                    "Invalid traversal option!");
            return;
        }

        String start = readText("Starting Location: ");

        if (choice == 1) {
            campusGraph.bfs(start);
        } else {
            campusGraph.dfs(start);
        }
    }
}
