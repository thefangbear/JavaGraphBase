import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by derros on 2/3/18.
 * Performs Breadth-First Search on a graph.
 */
public class BFS {

    private GraphNode[] graph;
    private int[] dist;
    private int[] parents;
    private boolean[] discovered;

    public BFS(GraphNode[] graph) {
        this.graph = graph;
        this.dist = new int[graph.length];
        this.parents = new int[graph.length];
        this.discovered = new boolean[graph.length];
        Arrays.fill(dist, Integer.MAX_VALUE - 1);
        Arrays.fill(parents, 0);
        Arrays.fill(discovered, false);
    }

    public BFS(int[][] graph) {
        this.graph = new GraphNode[graph.length];
        this.dist = new int[graph.length];
        this.parents = new int[graph.length];
        this.discovered = new boolean[graph.length];
        Arrays.fill(dist, Integer.MAX_VALUE - 1);
        Arrays.fill(parents, 0);
        Arrays.fill(discovered, false);
        for (int i = 0; i < graph.length; ++i) {
            this.graph[i] = new GraphNode();
            this.graph[i].i = i;
            for (int j = 0; j < graph[i].length; ++i) {
                this.graph[i].edges.add(graph[i][j]);
            }
        }
    }

    public void doBFS(int start) {
        System.out.println("starting BFS...");
        Queue<GraphNode> queue = new LinkedList<>();
        queue.add(graph[start]);
        discovered[start] = true;
        dist[start] = 0;
        while (!queue.isEmpty()) {
            GraphNode curr = queue.poll();
            System.out.printf(" -> BFS [%d] \n", curr.i);
            // do discovery
            for (int i = 0; i < curr.edges.size(); ++i) {
                if (!discovered[curr.edges.get(i)]) {
                    System.out.printf(" -> * Discovered [%d] \n", curr.edges.get(i));
                    queue.add(graph[curr.edges.get(i)]);
                    discovered[curr.edges.get(i)] = true;
                    parents[curr.edges.get(i)] = curr.i;
                }
                if (dist[curr.i] + 1 < dist[curr.edges.get(i)]) {
                    dist[curr.edges.get(i)] = dist[curr.i] + 1;
                }
            }
        }
    }

    public int[] getParents() {
        return this.parents;
    }

    public void printParents() {
        for(int i=0;i<graph.length;++i) {
            System.out.printf("| %d ", i);
        }
        System.out.printf("|\n");
        for (int i : parents) {
            System.out.printf("| %d ", i);
        }
        System.out.printf("|\n");
    }

    public int[] getDist() {
        return this.dist;
    }

    public void printDist() {
        for(int i=0;i<graph.length;++i) {
            System.out.printf("| %d ", i);
        }
        System.out.println("|");
        for(int d : dist) {
            System.out.printf("| %d ", d);
        }
        System.out.println("|");
    }

    public boolean[] getDiscovered() {
        return this.discovered;
    }

    public void printDiscovery() {
        for (int i=0;i<graph.length;++i) {
            System.out.printf("| %d " , i);
        }
        System.out.println("|");
        for(boolean b : discovered) {
            System.out.print("| " + (b ? "*" : "_") + " ");
        }
        System.out.println("|");
    }

    public int numConnectedComponents() {
        int numComponents = 0;
        doBFS(0);
        for (int i = 1; i < this.graph.length; ++i) {
            if (!discovered[i])
                ++numComponents;
            doBFS(i);
        }
        return numComponents;
    }
}
