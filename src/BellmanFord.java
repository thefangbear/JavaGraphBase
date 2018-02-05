import java.util.Arrays;

/**
 * Created by derros on 2/3/18.
 * The Bellman-Ford Shortest Path Algorithm.
 */
public class BellmanFord {

    private GraphEdge[] graph;
    private int nV;
    private int[] dist;
    private int[] parents;

    public BellmanFord(GraphEdge[] graph, int nV) {
        this.graph = graph;
        this.dist = new int[nV];
        this.parents = new int[nV];
        Arrays.fill(this.dist, Integer.MAX_VALUE);
        Arrays.fill(this.parents, 0);
        this.nV = nV;
    }

    public boolean doBellmanFord(int src) {
        dist[src] = 0;
        parents[src] = -1;
        // relax
        for (int i = 0; i < nV; ++i)
            for (GraphEdge edge : graph)
                if (dist[edge.vtx1] + edge.weight < dist[edge.vtx2]) {
                    dist[edge.vtx2] = dist[edge.vtx1] + edge.weight;
                    parents[edge.vtx2] = edge.vtx1;
                }
        for (GraphEdge edge : graph)
            if (dist[edge.vtx1] + edge.weight < dist[edge.vtx2]) {
                System.out.printf("Error: %d-%d-%d negative cycle found!\n", edge.vtx1, edge.vtx2, parents[edge.vtx2]);
                return false;
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
