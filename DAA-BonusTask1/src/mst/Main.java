package mst;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Graph graph = new Graph(5);
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 3, 6);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 8);
        graph.addEdge(1, 4, 5);
        graph.addEdge(2, 4, 7);
        graph.addEdge(3, 4, 9);

        MSTHandler handler = new MSTHandler();

        List<Edge> mst = handler.buildMST(graph);
        System.out.println("Original MST edges:");
        for (Edge e : mst) {
            System.out.println(e);
        }

        Edge edgeToRemove = null;
        for (Edge e : mst) {
            if ((e.src == 1 && e.dest == 4) || (e.src == 4 && e.dest == 1)) {
                edgeToRemove = e;
                break;
            }
        }
        if (edgeToRemove == null) {
            System.out.println("ERROR: Edge 1-4 not found in MST!");
            return;
        }

        System.out.println("\nRemoving edge: " + edgeToRemove);
        List<Edge> afterRemoval = handler.removeEdge(mst, edgeToRemove);
        System.out.println("Edges in MST after removal:");
        for (Edge e : afterRemoval) {
            System.out.println(e);
        }
        System.out.println();
        System.out.println("\nMST after removing edge " + edgeToRemove + ":");

        List<List<Integer>> components = handler.findComponents(graph, afterRemoval);
        System.out.println("Components after removal:");
        for (int i = 0; i < components.size(); i++) {
            System.out.println("Component " + (i + 1) + ": " + components.get(i));
        }

        Edge replacement = handler.findReplacementEdge(graph, afterRemoval, components, edgeToRemove);
        if (replacement != null) {
            afterRemoval.add(replacement);
            System.out.println("\nReplacement edge added: " + replacement);
        } else {
            System.out.println("\nNo replacement edge found.");
        }

        System.out.println("New MST edges:");
        for (Edge e : afterRemoval) {
            System.out.println(e);
        }
    }
}