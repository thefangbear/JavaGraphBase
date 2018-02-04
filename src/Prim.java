import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * Created by derros on 2/3/18.
 * Implementation of Prim's MST algorithm for graph.
 */
public class Prim {

    private GraphNode[] graph;
    private boolean[] in_tree;
    private int[] parents;

    public Prim(GraphNode[] graph) {
        this.graph = graph;
        this.in_tree = new boolean[graph.length];
        this.parents = new int[graph.length];
        Arrays.fill(this.parents, 0);
        for (int i = 0; i < graph.length; ++i) {
            graph[i].i = i;
            graph[i].weight = Integer.MAX_VALUE;
        }
    }

    public void doPrim(int src) {
        parents[src] = -1;
        TreeSet<GraphNode> queue = new TreeSet<>(new GraphNodeComparator());
        graph[src].weight = 0;
        queue.add(graph[src]);
        while (!queue.isEmpty()) {
            GraphNode curr = queue.pollFirst();
            System.out.printf(" Prim [%d] ew(%d)\n", curr.i, curr.weight);
            in_tree[curr.i] = true;
            for (int i = 0; i < curr.edges.size(); ++i) {
                int j = curr.edges.get(i);
                int w = curr.edgeWeights.get(i);
                if (graph[j].weight > w && !in_tree[j]) {
                    parents[j] = curr.i;
                    graph[j].weight = w;
                    System.out.println("--contains");
                    if (!queue.contains(graph[j]))
                        queue.add(graph[j]);
                    else {
                        queue.remove(graph[j]);
                        queue.add(graph[j]);
                    }
                    System.out.println("--end");
                    System.out.printf(" * Discovered [%d], ew(%d)\n", j, graph[j].weight);
                }
            }
            try {
                System.out.printf(" * NEXT: [%d], ew(%d)\n", queue.first().i, queue.first().weight);
            } catch (Exception ignored) {
            }
        }
    }

    public boolean[] getInTree() {
        return this.in_tree;
    }

    public int[] getParents() {
        return this.parents;
    }

    public void printTree() {
        int src = 0;
        ArrayList<GraphNode>[] children = (ArrayList<GraphNode>[]) new ArrayList[graph.length];
        for (int i = 0; i < graph.length; ++i) {
            children[i] = new ArrayList<>();
        }
        for (int i = 0; i < graph.length; ++i) {
            if (parents[i] == -1) {
                src = i;
                continue;
            }
            children[parents[i]].add(graph[i]);
        }
        for (int i = 0; i < graph.length; ++i) {
            System.out.printf("(%d) : ", i);
            for (GraphNode vtx : children[i]) {
                System.out.printf("%d(%d), ", vtx.i, vtx.weight);
            }
            System.out.println("");
        }
    }

    private class GraphNodeComparator implements Comparator<GraphNode> {

        @Override
        public int compare(GraphNode o1, GraphNode o2) {
            System.out.println("**Compare called!");
            if (o1.weight < o2.weight)
                return -1;
            if (o1.i == o2.i)
                return 0;

            return 1;
        }
    }
}
