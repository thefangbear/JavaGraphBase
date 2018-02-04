/**
 * Created by derros on 2/3/18.
 * Represents a vertex/node in a graph. Differs from GraphNode as this stores edges in fixed arrays.
 */
public class GraphVertex {
    public int n;
    public int[] edges;
    public int[] edgeWeights;
    public int weight;

    public GraphVertex(int n, int[] edges, int[] edgeWeights, int weight) {
        this.n = n;
        this.edges = edges;
        this.edgeWeights = edgeWeights;
        this.weight = weight;
    }

    public GraphVertex() {}

    public GraphVertex(int n, int[] edges) {
        this.n = n;
        this.edges = edges;
    }
}
