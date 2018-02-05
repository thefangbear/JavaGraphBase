import java.util.*;

/**
 * Created by derros on 2/3/18.
 * The Kruskal MST algorithm based on Union-Find-Dijoint sets.
 */
public class Kruskal {

    private ArrayList<GraphEdge> graph;
    private UnionFindDisjointSet set;
    private int mstWeight;

    public Kruskal(ArrayList<GraphEdge> graph) {
        this.graph = graph;
        this.set = new UnionFindDisjointSet(graph.size() * 2);
        this.graph.sort(new GraphEdgeComparator());
        this.mstWeight = 0;
    }

    public Kruskal(GraphNode[] adjList) {
        int numEdges = 0;
        for (GraphNode node : adjList)
            numEdges += node.edges.size();
        this.graph = new ArrayList<>();
        boolean[] discovered = new boolean[adjList.length];
        Arrays.fill(discovered, false);
        for (int h = 0; h < adjList.length; ++h) {
            // run BFS on all components and convert adjList to edgeList
            if (!discovered[h]) {
                Queue<GraphNode> queue = new LinkedList<>();
                queue.add(adjList[0]);
                discovered[0] = true;
                while (!queue.isEmpty()) {
                    GraphNode curr = queue.remove();
                    discovered[curr.i] = true;
                    for (int i = 0; i < curr.edges.size(); ++i) {
                        if (!discovered[curr.edges.get(i)]) {
                            System.out.printf("Disocovered edge: %d - %d\n", curr.i, curr.edges.get(i));
                            this.graph.add(new GraphEdge(curr.i, curr.edges.get(i), curr.edgeWeights.get(i), 0));
                            if (!queue.contains(adjList[curr.edges.get(i)]))
                                queue.add(adjList[curr.edges.get(i)]);
                        }
                    }
                }
            }
        }
        System.out.println(this.graph.size());
        this.graph.sort(new GraphEdgeComparator());
        for (int i = 0; i < this.graph.size(); ++i)
            this.graph.get(i).num = i;
        this.set = new UnionFindDisjointSet(graph.size() * 2);
        this.mstWeight = 0;
    }

    public void doKruskal() {
        for (GraphEdge edge : graph) {
            int u = edge.vtx1;
            int v = edge.vtx2;
            int uSet = set.find(u);
            int vSet = set.find(v);
            if (uSet != vSet) {
                System.out.printf("MST: %d - %d\n", u, v);
                this.mstWeight += edge.weight;
                set.union(uSet, vSet);
            }
        }
    }

    public int getMstWeight() {
        return this.mstWeight;
    }

    private class GraphEdgeComparator implements Comparator<GraphEdge> {

        @Override
        public int compare(GraphEdge o1, GraphEdge o2) {
            if (o1.weight < o2.weight)
                return -1;
            if (o1.weight == o2.weight)
                return 0;
            return 1;
        }
    }
}
