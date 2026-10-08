
import java.util.*;

public class CampusGraph {

    // Adjacency List representation
    private final Map<String, LinkedHashSet<String>> graph;

    public CampusGraph() {
        graph = new LinkedHashMap<>();
    }

    // 1. Add a new campus location
    public boolean addLocation(String location) {

        if (location == null || location.trim().isEmpty()) {
            System.out.println("Invalid location name!");
            return false;
        }

        location = location.trim();

        if (graph.containsKey(location)) {
            System.out.println("Location already exists!");
            return false;
        }

        graph.put(location, new LinkedHashSet<>());

        System.out.println("Location added: " + location);
        return true;
    }

    // 2. Remove a campus location
    public boolean removeLocation(String location) {

        if (!graph.containsKey(location)) {
            System.out.println("Location not found!");
            return false;
        }

        // Remove roads connected to this location
        for (Set<String> neighbours : graph.values()) {
            neighbours.remove(location);
        }

        graph.remove(location);

        System.out.println("Location removed: " + location);
        return true;
    }

    // 3. Add a road between two locations
    public boolean addConnection(String from, String to) {

        if (!graph.containsKey(from) ||
                !graph.containsKey(to)) {
            System.out.println("One or both locations do not exist!");
            return false;
        }

        if (from.equals(to)) {
            System.out.println("Self-connections are not allowed!");
            return false;
        }

        if (graph.get(from).contains(to)) {
            System.out.println("Connection already exists!");
            return false;
        }

        // Undirected graph
        graph.get(from).add(to);
        graph.get(to).add(from);

        System.out.println(
                "Connection added: " + from + " <-> " + to);

        return true;
    }

    // 4. Remove an existing road
    public boolean removeConnection(String from, String to) {

        if (!graph.containsKey(from) ||
                !graph.containsKey(to)) {
            System.out.println("Location not found!");
            return false;
        }

        if (!graph.get(from).contains(to)) {
            System.out.println("Connection does not exist!");
            return false;
        }

        graph.get(from).remove(to);
        graph.get(to).remove(from);

        System.out.println(
                "Connection removed: " + from + " <-> " + to);

        return true;
    }

    // 5. Display adjacency list
    public void displayConnections() {

        if (graph.isEmpty()) {
            System.out.println("Campus graph is empty!");
            return;
        }

        System.out.println("\n===== CAMPUS NETWORK =====");

        for (Map.Entry<String, LinkedHashSet<String>> entry : graph.entrySet()) {

            System.out.print(entry.getKey() + " -> ");

            if (entry.getValue().isEmpty()) {
                System.out.println("No connections");
            } else {
                System.out.println(
                        String.join(", ", entry.getValue()));
            }
        }
    }

    // 6. Breadth-First Search
    public void bfs(String start) {

        if (!graph.containsKey(start)) {
            System.out.println("Starting location not found!");
            return;
        }

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new ArrayDeque<>();

        visited.add(start);
        queue.add(start);

        System.out.println("\n===== BFS TRAVERSAL =====");

        while (!queue.isEmpty()) {

            String current = queue.poll();
            System.out.print(current + " -> ");

            for (String neighbour : graph.get(current)) {

                if (!visited.contains(neighbour)) {
                    visited.add(neighbour);
                    queue.add(neighbour);
                }
            }
        }

        System.out.println("END");
    }

    // 7. Depth-First Search
    public void dfs(String start) {

        if (!graph.containsKey(start)) {
            System.out.println("Starting location not found!");
            return;
        }

        Set<String> visited = new HashSet<>();

        System.out.println("\n===== DFS TRAVERSAL =====");

        dfsRecursive(start, visited);

        System.out.println("END");
    }

    private void dfsRecursive(
            String current, Set<String> visited) {

        visited.add(current);
        System.out.print(current + " -> ");

        for (String neighbour : graph.get(current)) {

            if (!visited.contains(neighbour)) {
                dfsRecursive(neighbour, visited);
            }
        }
    }

    // 8. Count campus locations
    public int countLocations() {
        return graph.size();
    }

    // 9. Count unique roads
    public int countConnections() {

        int total = 0;

        for (Set<String> neighbours : graph.values()) {
            total += neighbours.size();
        }

        // Every undirected edge is stored twice
        return total / 2;
    }
}
