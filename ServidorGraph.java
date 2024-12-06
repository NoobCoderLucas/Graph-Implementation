import java.util.*;

public class Main {
    private Map<Integer, List<Integer>> adjList;

    public Main() {
        adjList = new HashMap<>();
    }

    public void addVertex(int vertex) {
        adjList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(int source, int destination) {
        adjList.putIfAbsent(source, new ArrayList<>());
        adjList.putIfAbsent(destination, new ArrayList<>());
        adjList.get(source).add(destination);
        adjList.get(destination).add(source); 
    }

    public void deleteVertex(int vertex) {
        if (adjList.containsKey(vertex)) {
            for (int neighbor : adjList.get(vertex)) {
                adjList.get(neighbor).remove(Integer.valueOf(vertex));
            }
            adjList.remove(vertex);
        }
    }

    public void deleteEdge(int source, int destination) {
        if (adjList.containsKey(source)) {
            adjList.get(source).remove(Integer.valueOf(destination));
        }
        if (adjList.containsKey(destination)) {
            adjList.get(destination).remove(Integer.valueOf(source));
        }
    }

    public void dfs(int start) {
        Set<Integer> visited = new HashSet<>();
        dfsRecursive(start, visited);
        System.out.println();
    }

    private void dfsRecursive(int vertex, Set<Integer> visited) {
        if (!visited.contains(vertex)) {
            System.out.print(vertex + " ");
            visited.add(vertex);
            for (int neighbor : adjList.getOrDefault(vertex, new ArrayList<>())) {
                dfsRecursive(neighbor, visited);
            }
        }
    }

    public void printGraph() {
        for (Map.Entry<Integer, List<Integer>> entry : adjList.entrySet()) {
            System.out.print(entry.getKey() + " -> ");
            for (int neighbor : entry.getValue()) {
                System.out.print(neighbor + " "); 
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Main graph = new Main();

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);

        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);

        System.out.println("Graph:");
        graph.printGraph();

        System.out.println("After deleting edge (1, 3):");
        graph.deleteEdge(1, 3);
        graph.printGraph();

        System.out.println("After deleting vertex 4:");
        graph.deleteVertex(4);
        graph.printGraph();
    }
}
