import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by derros on 2/4/18.
 * Converts between GraphEdge, GraphNode, GraphVertex
 */
public class GraphConverter {

    public static GraphEdge[] toGraphEdge(GraphMatrix mat) {
        ArrayList<GraphEdge> edges = new ArrayList<>();
        int[][] graph = mat.getMatrix();
        for (int i = 0; i < graph.length; ++i)
            for (int j = 0; j < graph[0].length; ++j)
                if (graph[i][j] != 0)
                    edges.add(new GraphEdge(i, j, graph[i][j], 0));
        GraphEdge[] rEdges = new GraphEdge[edges.size()];
        for(int i = 0; i < rEdges.length; ++i) {
            rEdges[i] = edges.get(i);
            rEdges[i].num = i;
        }
        return rEdges;
    }

    public static GraphEdge[] toGraphEdge(GraphNode[] nodes) {
        ArrayList<GraphEdge> edges = new ArrayList<>();
        Queue<GraphNode> queue = new LinkedList<>();
        boolean[] discovered = new boolean[nodes.length];
        Arrays.fill(discovered, false);
        queue.add(nodes[0]);
        while (!queue.isEmpty()) {
            GraphNode curr = queue.remove();
            discovered[curr.i] = true;
            for(int i = 0; i < curr.edges.size(); ++i) {
                int j = curr.edges.get(i);
                if (!discovered[j]) {
                    edges.add(new GraphEdge(curr.i, j, curr.edgeWeights.get(i), 0));
                    if (!queue.contains(nodes[j]))
                        queue.add(nodes[j]);
                }
            }
        }
        GraphEdge[] rEdges = new GraphEdge[edges.size()];
        for(int i = 0; i < rEdges.length; ++i) {
            rEdges[i] = edges.get(i);
            rEdges[i].num = i;
        }
        return rEdges;
    }

    public static GraphEdge[] toGraphEdge(GraphVertex[] vts) {
        ArrayList<GraphEdge> edges = new ArrayList<>();
        Queue<GraphVertex> queue = new LinkedList<>();
        boolean[] discovered = new boolean[vts.length];
        Arrays.fill(discovered, false);
        queue.add(vts[0]);
        while(!queue.isEmpty()) {
            GraphVertex curr = queue.remove();
            discovered[curr.n] = true;
            for(int i = 0; i < curr.edges.length; ++i) {
                int j = curr.edges[i];
                if (!discovered[j]) {
                    edges.add(new GraphEdge(curr.n, j, curr.edgeWeights[i], 0));
                    if (!queue.contains(vts[j]))
                        queue.add(vts[j]);
                }
            }
        }
        GraphEdge[] rEdges = new GraphEdge[edges.size()];
        for(int i = 0; i < rEdges.length; ++i) {
            rEdges[i] = edges.get(i);
            rEdges[i].num = i;
        }
        return rEdges;
    }

    public static GraphNode[] toGraphNode(GraphMatrix mat) {
        ArrayList<GraphNode> nodes = new ArrayList<>();
        int[][] graph = mat.getMatrix();
        int m = graph.length, n = graph[0].length;
        for(int i = 0; i < m; ++i) {
            nodes.add(new GraphNode(i, Integer.MAX_VALUE, new ArrayList<Integer>(), new ArrayList<Integer>()));
            for (int j = 0; j < n; ++j)
                if (graph[i][j] != 0) {
                    nodes.get(i).edges.add(j);
                    nodes.get(i).edgeWeights.add(graph[i][j]);
                }
        }
        GraphNode[] rNodes = new GraphNode[nodes.size()];
        for(int i = 0; i < rNodes.length; ++i) {
            rNodes[i] = nodes.get(i);
        }
        return rNodes;
    }

    public static GraphNode[] toGraphNode(GraphVertex[] vts) {
        GraphNode[] nodes = new GraphNode[vts.length];
        for(int i = 0; i < vts.length; ++i) {
            nodes[i] = new GraphNode(vts[i].n, vts[i].weight, new ArrayList<>(), new ArrayList<>());
            for(int j = 0; j < vts[i].edges.length; ++j) {
                nodes[i].edges.add(vts[i].edges[j]);
                nodes[i].edgeWeights.add(vts[i].edgeWeights[j]);
            }
        }
        return nodes;
    }
}
