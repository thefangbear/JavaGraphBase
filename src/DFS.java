import java.util.Arrays;

/**
 * Created by derros on 2/3/18.
 * Performs depth-first-search on a graph.
 */
public class DFS {
    private String indent = "";
    private GraphNode[] graph;
    private boolean[] discovered;
    private int[] parents;
    private int[] dist;
    boolean isFinished = false;
    public DFS(GraphNode[] graph) {
        this.graph = graph;
        this.discovered = new boolean[graph.length];
        this.parents = new int[graph.length];
        this.dist = new int[graph.length];
        Arrays.fill(this.discovered, false);
        Arrays.fill(this.parents, 0);
        Arrays.fill(this.dist, Integer.MAX_VALUE - 1);
    }

    public DFS(int[][] graph) {
        this.graph = new GraphNode[graph.length];
        this.discovered = new boolean[graph.length];
        this.parents = new int[graph.length];
        this.dist = new int[graph.length];
        Arrays.fill(this.discovered, false);
        Arrays.fill(this.parents, 0);
        Arrays.fill(this.dist, Integer.MAX_VALUE - 1);
        for (int i = 0; i < graph.length; ++i) {
            this.graph[i] = new GraphNode();
            this.graph[i].i = i;
            for (int j = 0; j < graph[i].length; ++i) {
                this.graph[i].edges.add(graph[i][j]);
            }
        }
    }

    public void doDFSCycles(int i) {
        if (isFinished)
            return;
        discovered[i] = true;
        System.out.printf(indent + "-> DFS[%d]\n", i);
        indent += " ";
        for(int j : graph[i].edges) {
            if (!discovered[j]) {
                System.out.printf(indent + "-> * [%d]\n", j);
                dist[j] = dist[i] + 1 < dist[j] ? dist[i] + 1 : dist[j];
                parents[j] = i;
                doDFSCycles(j);
            } else if (parents[j] != i) {
                System.out.printf("*** Found cycle! %d <-> %d <-> %d\n", i, j, parents[j]);
                isFinished = true;
            }
        }
    }

    public void doDFS(int i) {
        discovered[i] = true;
        System.out.printf(indent + "-> DFS[%d]\n", i);
        indent += " ";
        for(int j = 0; j < graph[i].edges.size(); ++j) {
            if (!discovered[graph[i].edges.get(j)]) {
                System.out.printf(indent + "-> * [%d]\n", graph[i].edges.get(j));
                dist[graph[i].edges.get(j)] = dist[i] + 1 < dist[graph[i].edges.get(j)] ? dist[i] + 1
                        : dist[graph[i].edges.get(j)];
                parents[graph[i].edges.get(j)] = i;
                doDFS(graph[i].edges.get(j));
            }
        }
    }

    public int[] getParents() {  return this.parents; }

    public int[] getDist() { return this.dist; }

    public boolean[] getDiscovered() { return this.discovered; }
}
