import java.util.Arrays;

/**
 * Created by derros on 2/4/18.
 * The Adjacency Matrix-type representation of graph.
 */
public class GraphMatrix {

    private int[][] graph;

    public GraphMatrix(int size) {
        this.graph = new int[size][size];
        for (int i = 0; i < size; ++i)
            Arrays.fill(this.graph[i], 0);
    }

    public GraphMatrix(int m, int n) {
        this.graph = new int[m][n];
        for (int i = 0; i < m; ++i)
            Arrays.fill(this.graph[i], 0);
    }

    public GraphMatrix(GraphNode[] nodes, boolean isWeighted) {
        if (isWeighted)
            for (int i = 0; i < nodes.length; ++i) {
                for (int j = 0; j < nodes[i].edges.size(); ++j) {
                    graph[i][nodes[i].edges.get(j)] = nodes[i].edgeWeights.get(j);
                }
            }
        else
            for (int i = 0; i < nodes.length; ++i)
                for (int j : nodes[i].edges)
                    graph[i][j] = 1;
    }

    public GraphMatrix(GraphVertex[] vs, boolean isWeighted) {
        if (isWeighted)
            for (int i = 0; i < vs.length; ++i)
                for (int j = 0; j < vs.length; ++j)
                    graph[i][vs[i].edges[j]] = vs[i].edgeWeights[j];
        else
            for (int i = 0; i < vs.length; ++i)
                for (int j = 0; j < vs.length; ++j)
                    graph[i][vs[i].edges[j]] = 1;
    }

    public GraphMatrix(GraphEdge[] edges, boolean isWeighted, boolean isDirected) {
        if (isWeighted && isDirected)
            for (GraphEdge edge : edges)
                graph[edge.vtx1][edge.vtx2] = edge.weight;
        if (isWeighted && !isDirected)
            for (GraphEdge edge : edges) {
                graph[edge.vtx1][edge.vtx2] = edge.weight;
                graph[edge.vtx2][edge.vtx1] = edge.weight;
            }
        if (!isWeighted && isDirected)
            for (GraphEdge edge : edges)
                graph[edge.vtx1][edge.vtx2] = 1;
        if (!isWeighted && !isDirected)
            for (GraphEdge edge : edges) {
                graph[edge.vtx1][edge.vtx2] = 1;
                graph[edge.vtx2][edge.vtx1] = 1;
            }
    }

    public int[][] getMatrix() {
        return this.graph;
    }

    public void set(int i, int j, int w) {
        graph[i][j] = w;
    }

    public void symmetricSet(int i, int j, int w) {
        graph[i][j] = w;
        graph[j][i] = w;
    }

    public int get(int i, int j) {
        return this.graph[i][j];
    }

    public void exp(int e) {
        assert this.graph.length == this.graph[0].length;
        int[][] a = new int[graph.length][graph.length];
        int n = this.graph.length;
        for (int i = 0; i < n; ++i)
            System.arraycopy(graph[i], 0, a[i], 0, n);
        while (e > 0)
            if ((e & 1) == 0) {
                for (int i = 0; i < n; ++i)
                    for (int k = 0; k < n; ++k)
                        for (int j = 0; j < n; ++j)
                            graph[i][j] = graph[i][k] * graph[k][j];


                e >>= 1;
            } else {
                for(int i =0; i < n; ++i)
                    for(int k = 0; k < n; ++k)
                        for(int j = 0; j < n; ++j)
                            graph[i][j] = graph[i][k] * a[k][j];
                --e;
            }
    }
}
