# University Student Record and Campus Route Management System

## CIT300 – Data Structures and Algorithms
**Graded Practical Assignment 1 – Week 10**

### 1. Project Overview

This project is a Java-based console application developed to demonstrate the practical implementation of fundamental data structures and algorithms in a university management environment.

The system provides student record management, service request processing, action history tracking, student searching, and campus route management.

### 2. Project Objectives

- Implement and demonstrate Linked Lists, Stacks, Queues, Binary Search Trees, Hashing, and Graphs.
- Manage university student records efficiently.
- Process student service requests using FIFO.
- Maintain recent system actions using LIFO.
- Search and organize student records using BST and Hashing.
- Represent campus locations and roads using an adjacency list.
- Perform BFS and DFS graph traversals.
- Provide a menu-driven Java console interface with input validation.

### 3. Technologies Used

- Programming Language: Java
- Development Environment: Visual Studio Code
- Version Control: Git
- Collaboration Platform: GitHub
- Application Type: Console-based application

### 4. Implemented Data Structures

| Data Structure | Implementation |
|---|---|
| Linked List | Student record management |
| Stack | Recent action history |
| Queue | Student service requests |
| Binary Search Tree | Student ID organization and sorted display |
| Hash Table | Student searching by ID |
| Graph | Campus locations and roads |
| BFS and DFS | Campus network traversal |

### 5. Main System Features

The application provides 16 menu options covering student record management, service requests, recent actions, BST display, hashing search, campus location management, road management, BFS/DFS traversal, and application exit.

### 6. Project Structure

```text
CIT300_Student_Campus_System/
├── src/
│   ├── Student.java
│   ├── StudentLinkedList.java
│   ├── StudentStack.java
│   ├── ServiceQueue.java
│   ├── StudentBST.java
│   ├── StudentHashTable.java
│   ├── CampusGraph.java
│   └── Main.java
├── screenshots/
├── README.md
├── .gitignore
└── Group_Members.txt
```

### 7. How to Run the Application

**Prerequisites:** Java JDK 17 or later.

Open the project folder in VS Code.

Compile:

```powershell
javac -d out src/*.java
```

Run:

```powershell
java -cp out Main
```

Select the required menu option and follow the instructions displayed in the terminal.

### 8. Group Members and Contributions

| Member | Student ID | Assigned Responsibility | Individual Contribution |
|---|---|---|---|
| [Full Name 1] | [Student ID 1] | Linked List and Student Management | [Describe actual work completed] |
| [Full Name 2] | [Student ID 2] | Stack and Queue | [Describe actual work completed] |
| [Full Name 3] | [Student ID 3] | BST and Hashing | [Describe actual work completed] |
| [Full Name 4] | [Student ID 4] | Graph and BFS/DFS | [Describe actual work completed] |

All group members participate in integration, testing, debugging, documentation, and demonstration according to their actual contributions.

### 9. Testing and Validation

The system should be tested for:

- Student addition, modification, deletion, and searching.
- Duplicate Student ID handling.
- Invalid marks and empty inputs.
- Stack LIFO operations.
- Queue FIFO operations.
- BST ordering and searching.
- Hash table searching and deletion.
- Campus location and connection management.
- BFS and DFS traversal.
- Invalid menu selections.

Actual testing results and supporting screenshots should be recorded after execution.

### 10. GitHub Collaboration

Repository: [Insert GitHub repository URL]

Group collaboration evidence includes actual commits, branches, and pull requests created during development.

### 11. Demonstration Video

Video Link: [Insert final demonstration video link]

The merged demonstration video presents the completed application and each group member's contribution.

### 12. Conclusion

The University Student Record and Campus Route Management System demonstrates the application of core data structures and algorithms in a Java console environment. The project combines student information management and campus graph operations into a single integrated system.

**Module:** CIT300 – Data Structures and Algorithms

**Assignment:** Graded Practical Assignment 1
