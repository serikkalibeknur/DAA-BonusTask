package mst;

import java.util.*;

public class MSTHandler {
    public List<Edge> buildMST(Graph graph) {
        List<Edge> mst = new ArrayList<>();
        List<Edge> allEdges = new ArrayList<>(graph.edges);
        Collections.sort(allEdges);

        UnionFind uf = new UnionFind(graph.vertices);

        for (Edge edge : allEdges) {
            int rootSrc = uf.find(edge.src);
            int rootDest = uf.find(edge.dest);
            if (rootSrc != rootDest) {
                mst.add(edge);
                uf.union(edge.src, edge.dest);
            }
        }
        return mst;
    }


    public List<Edge> removeEdge(List<Edge> mst, Edge edgeToRemove) {
        List<Edge> newMST = new ArrayList<>(mst);
        newMST.remove(edgeToRemove);
        return newMST;
    }


    public List<List<Integer>> findComponents(Graph graph, List<Edge> currentMST) {
        List<List<Integer>> components = new ArrayList<>();
        boolean[] visited = new boolean[graph.vertices];
        List<List<Integer>> adj = buildAdjList(currentMST, graph.vertices);

        for (int i = 0; i < graph.vertices; i++) {
            if (!visited[i]) {
                List<Integer> component = new ArrayList<>();
                dfs(i, adj, visited, component);
                components.add(component);
            }
        }
        return components;
    }

    private List<List<Integer>> buildAdjList(List<Edge> edges, int vertices) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adj.add(new ArrayList<>());
        }
        for (Edge e : edges) {
            adj.get(e.src).add(e.dest);
            adj.get(e.dest).add(e.src);
        }
        return adj;
    }

    private void dfs(int node, List<List<Integer>> adj, boolean[] visited, List<Integer> component) {
        visited[node] = true;
        component.add(node);
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, adj, visited, component);
            }
        }
    }

    public Edge findReplacementEdge(Graph graph, List<Edge> currentMST, List<List<Integer>> components, Edge removedEdge) {
        if (components.size() != 2) {
            throw new IllegalStateException("Removal should split into exactly two components.");
        }

        Set<Integer> comp1 = new HashSet<>(components.get(0));
        Set<Integer> comp2 = new HashSet<>(components.get(1));

        Edge minEdge = null;
        int minWeight = Integer.MAX_VALUE;

        for (Edge candidate : graph.edges) {
            if (candidate.equals(removedEdge)) continue;
            boolean connects =
                    (comp1.contains(candidate.src) && comp2.contains(candidate.dest)) ||
                            (comp2.contains(candidate.src) && comp1.contains(candidate.dest));
            if (!connects) continue;
            if (currentMST.contains(candidate)) continue;
            if (candidate.weight < minWeight) {
                minWeight = candidate.weight;
                minEdge = candidate;
            }
        }
        return minEdge;
    }
}