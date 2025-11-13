package mst;


import java.util.Objects;

public class Edge implements Comparable<Edge> {
    int src, dest, weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge edge)) return false;
        return (src == edge.src && dest == edge.dest && weight == edge.weight) ||
                (src == edge.dest && dest == edge.src && weight == edge.weight);
    }

    @Override
    public int hashCode() {
        int a = Math.min(src, dest);
        int b = Math.max(src, dest);
        return Objects.hash(a, b, weight);
    }

    @Override
    public String toString() {
        return src + " -- " + dest + " (weight: " + weight + ")";
    }
}