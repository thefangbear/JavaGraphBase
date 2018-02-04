import java.util.ArrayList;

/**
 * Created by derros on 2/3/18.
 * Represents a node/vertex in a graph.
 */
public class GraphNode {

    public int i;
    public int weight;
    public ArrayList<Integer> edges;
    public ArrayList<Integer> edgeWeights;

    public GraphNode(int i, int weight, ArrayList<Integer> edges, ArrayList<Integer> edgeWeights) {
        this.i = i;
        this.weight = weight;
        this.edges = edges;
        this.edgeWeights = edgeWeights;
    }

    public GraphNode() {
        this.edges = new ArrayList<>();
        this.edgeWeights = new ArrayList<>();
    }

    public GraphNode(int i, ArrayList<Integer> edges) {
        this.i = i;
        this.edges = edges;
    }
}
