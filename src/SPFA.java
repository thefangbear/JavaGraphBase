import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by derros on 2/4/18.
 * Shortest-Path Fastest Algorithm by Fanding Duan.
 */
public class SPFA {

    private GraphNode[] graph;
    private int[] dist;
    private int[] parents;

    public SPFA(GraphNode[] graph) {
        this.graph = graph;
        this.parents = new int[graph.length];
        this.dist = new int[graph.length];
        Arrays.fill(this.parents, 0);
        Arrays.fill(this.dist, Integer.MAX_VALUE);
    }

    public boolean doSPFA(int src) {
        this.dist[src] = 0;
        this.parents[src] = -1;
        Queue<GraphNode> queue = new LinkedList<>();
        queue.add(graph[src]);
        while (!queue.isEmpty()) {
            GraphNode curr = queue.remove();
            System.out.printf(" SPFA[%d]\n", curr.i);
            for (int i = 0; i < curr.edges.size(); ++i) {
                int j = curr.edges.get(i);
                int w = curr.edgeWeights.get(i);
                if (dist[curr.i] + w < dist[j]) {
                    dist[j] = dist[curr.i] + w;
                    parents[j] = curr.i;
                    System.out.printf(" * %d - %d w[%d]\n", curr.i, j, dist[j]);
                    if (!queue.contains(graph[j]))
                        queue.add(graph[j]);
                }
            }
        }
        // do BFS to prove our relaxation is complete
        boolean[] discovered = new boolean[graph.length];
        Arrays.fill(discovered, false);
        queue.add(graph[src]);
        while (!queue.isEmpty()) {
            GraphNode curr = queue.remove();
            discovered[curr.i] = true;
            for (int i = 0; i < curr.edges.size(); ++i) {
                int j = curr.edges.get(i);
                int w = curr.edgeWeights.get(i);
                if (dist[curr.i] + w < dist[j]) {
                    System.out.printf("Error: %d-%d-%d negative cycle found!!\n", curr.i, j, parents[j]);
                    return false;
                }
                if (!discovered[j] && !queue.contains(graph[j]))
                    queue.add(graph[j]);
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
