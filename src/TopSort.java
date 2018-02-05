import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by derros on 2/3/18.
 * Topological Sorting algorithm.
 */
public class TopSort {

    private GraphNode[] graph;
    private GraphNode[] sorted;
    private int[] inDegree;
    private int k = 0;

    public TopSort(GraphNode[] graph) {
        this.graph = graph;
        this.sorted = new GraphNode[graph.length];
        this.inDegree = new int[graph.length];
        Arrays.fill(this.inDegree, 0);
        for (int i = 0; i < graph.length; ++i)
            for (int j : graph[i].edges)
                ++inDegree[j];
    }

    public boolean doTopSort() {
        Queue<GraphNode> queue = new LinkedList<>();
        for (int i = 0; i < graph.length; ++i)
            if (inDegree[i] == 0)
                queue.add(graph[i]);
        k = 0;
        while (!queue.isEmpty()) {
            GraphNode curr = queue.remove();
            sorted[k++] = curr;
            System.out.printf(" TopSort [%d]", curr.i);
            for (int j : curr.edges) {
                if ((--inDegree[j]) == 0) {
                    System.out.printf(" *--> %d\n", j);
                    queue.add(graph[j]);
                }
            }
        }
        return (k == graph.length);
    }

    public GraphNode[] getSortedGraph() {
        return this.sorted;
    }

    public void printSortedGraph() {
        System.out.printf("| Ord: ");
        for(int i = 0; i < k; ++i)
            System.out.printf("| %d ", k);
        System.out.println("|");
        System.out.printf("| Vtx: ");
        for(int i = 0; i < k; ++i)
            System.out.printf("| %d ", sorted[k].i);
        System.out.println("|");
    }
}
