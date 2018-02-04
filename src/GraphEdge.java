/**
 * Created by derros on 2/3/18.
 * Represents an edge in a graph.
 */
public class GraphEdge {
    public int vtx1;
    public int vtx2;
    public int weight;
    public int num;

    public GraphEdge(int vtx1, int vtx2, int weight, int num) {
        this.vtx1 = vtx1;
        this.vtx2 = vtx2;
        this.weight = weight;
    }

    public GraphEdge() {

    }

    public GraphEdge(int vtx1, int vtx2, int num) {
        this.vtx1 = vtx1;
        this.vtx2 = vtx2;
        this.num = num;
    }
}
