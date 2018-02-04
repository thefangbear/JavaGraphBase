import java.util.*;

/**
 * Created by derros on 2/3/18.
 * Performs Dijkstra Single-source Shortest-Path algorithm on a graph.
 */
public class Dijkstra {

    private GraphNode[] graph;
    private int[] dist;
    private int[] parents;

    public Dijkstra(GraphNode[] graph) {
        this.graph = graph;
        this.dist = new int[graph.length];
        this.parents = new int[graph.length];
        Arrays.fill(this.dist, Integer.MAX_VALUE);
        Arrays.fill(this.parents, 0);
        for (int i = 0; i < graph.length; ++i) {
            graph[i].weight = Integer.MAX_VALUE;
            graph[i].i = i;
        }
    }

    public void doDijkstra(int src) {
        graph[src].weight = 0;
        parents[src] = -1;
        dist[src] = 0;
        TreeSet<GraphNode> queue = new TreeSet<>(new GraphNodeComparator());
        queue.add(graph[src]);
        while (!queue.isEmpty()) {
            GraphNode curr = queue.pollFirst();
            System.out.printf(" Dijkstra [%d] w(%d)\n", curr.i, curr.weight);
            for (int i = 0; i < curr.edges.size(); ++i) {
                int j = curr.edges.get(i);
                int w = curr.edgeWeights.get(i);
                if (curr.weight + w < graph[j].weight) {
                    graph[j].weight = curr.weight + w;
                    parents[j] = curr.i;
                    dist[j] = graph[j].weight;
                    System.out.printf(" * [%d] w(%d)\n", j, graph[j].weight);
                    if (!queue.contains(graph[j]))
                    queue.add(graph[j]);
                    else {
                        queue.remove(graph[j]);
                        queue.add(graph[j]);
                    }
                }
            }
            try {
                System.out.printf(" ** Next: %d\n", queue.first().i);
            } catch (NoSuchElementException ignored) {}
        }

    }

    public void everyDoDijkstra() {
        for (int i = 0; i < graph.length; ++i) {
            doDijkstra(i);
        }
    }

    public int[] getParents() {
        return this.parents;
    }

    public void printParents() {
        for(int i = 0; i < graph.length; ++i) {
            System.out.printf("| %d ", i);
        }
        System.out.println("|");
        for(int i = 0; i < graph.length; ++i) {
            System.out.printf("| %d ", parents[i]);
        }
        System.out.println("|");
    }

    public int[] getDist() {
        return this.dist;
    }

    public void printDist() {
        for(int i = 0; i < graph.length; ++i) {
            System.out.printf("| %d ", i);
        }
        System.out.println("|");
        for(int i = 0; i < graph.length; ++i) {
            System.out.printf("| %d ", dist[i]);
        }
        System.out.println("|");
    }

    public ArrayList<Integer> reconstructPath(int a, int b) {
        ArrayList<Integer> path = new ArrayList<>();
        int ptr = b;
        while (ptr != -1) {
            path.add(ptr);
            if (ptr == a)
                break;
            ptr = parents[ptr];
        }
        return path;
    }

    private class GraphNodeComparator implements Comparator<GraphNode> {

        @Override
        public int compare(GraphNode o1, GraphNode o2) {
            if (o1.weight < o2.weight)
                return -1;
            if (o1.i == o2.i)
                return 0;
            return 1;
        }
    }
}
