import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by derros on 2/4/18.
 * The "Improved SPFA" Algorithm as in paper
 * "An Improved SPFA Algorithm for Single-Source Shortest Path Problem Using Forward Star Data Structure"
 * by Xin Zhou. Claimed O(|E|) runtime.
 * Uses a Deque instead of a Queue.
 */
public class ImprovedSPFA {

    private GraphNode[] graph;
    private int[] dist;
    private int[] parents;

    public ImprovedSPFA(GraphNode[] graph) {
        this.graph = graph;
        this.dist = new int[graph.length];
        this.parents = new int[graph.length];
        Arrays.fill(this.dist, Integer.MAX_VALUE);
        Arrays.fill(this.parents, 0);
    }

    public boolean doImprovedSPFA(int src) {
        this.parents[src] = -1;
        this.dist[src] = 0;
        int min = Integer.MAX_VALUE;
        Deque<GraphNode> queue = new LinkedList<>();
        queue.addFirst(graph[src]);
        while (!queue.isEmpty()) {
            GraphNode curr = queue.pollFirst();
            System.out.printf(" ImprovedSPFA [%d] \n", curr.i);
            for (int i = 0; i < curr.edges.size(); ++i) {
                int j = curr.edges.get(i);
                int w = curr.edgeWeights.get(i);
                if (dist[curr.i] + w < dist[j]) {
                    dist[j] = dist[curr.i] + w;
                    parents[j] = curr.i;
                    System.out.printf(" * %d-%d w(%d)\n", curr.i, j, dist[j]);
                    if (!queue.contains(graph[j])) {
                        if (dist[j] < min) {
                            queue.addFirst(graph[j]);
                        } else {
                            queue.addLast(graph[j]);
                        }
                    }
                }
            }
        }
        // sweep through again using BFS to check for negative cycles
        boolean[] discovered = new boolean[graph.length];
        Arrays.fill(discovered, false);
        queue.addFirst(graph[src]);
        while (!queue.isEmpty()) {
            GraphNode curr = queue.pollFirst();
            discovered[curr.i] = true;
            for (int i = 0; i < curr.edges.size(); ++i) {
                int j = curr.edges.get(i);
                int w = curr.edgeWeights.get(i);
                if (dist[curr.i] + w < dist[j]) {
                    // we have a problem
                    System.out.printf("Error: negative cycle found: %d-%d-%d\n", curr.i, j, parents[j]);
                    return false;
                }
                if (!discovered[j] && !queue.contains(graph[j]))
                    queue.addLast(graph[j]);
            }
        }
        return true;
    }

    public int[] getDistance() {
        return this.dist;
    }

    public int[] getParents() {
        return this.parents;
    }
}
