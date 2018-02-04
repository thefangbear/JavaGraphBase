import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by derros on 2/3/18.
 * Performs connected components using a breadth-first search.
 */
public class ConnectedComponents {

    private GraphNode[] graph;
    private boolean[] discovered;
    public ConnectedComponents(GraphNode[] graph) {
        this.graph = graph;
        this.discovered = new boolean[graph.length];
        Arrays.fill(discovered, false);
    }

    public int getNumConnectedComponents() {
        int count = 0;
        for(int i=0;i<graph.length;++i) {
            if (!discovered[i]) {
                ++count;
                Queue<GraphNode> queue = new LinkedList<>();
                queue.add(graph[i]);
                discovered[i] = true;
                while (!queue.isEmpty()) {
                    GraphNode curr = queue.remove();
                    for(int vtx : curr.edges) {
                        if (!discovered[i]) {
                            queue.add(graph[i]);
                            discovered[i] = true;
                        }
                    }
                }
            }
        }
        return count;
    }
}
