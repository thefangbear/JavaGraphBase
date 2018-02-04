import java.util.ArrayList;

/**
 * Created by derros on 2/4/18.
 */
public class KruskalRunner {
    public static void main(String[] args) {
        /* Graph:
         *    (0) ----1--- (1)          (2)
         *     | \        / |         /  |
         *     |   3    4   |       /    |
         *     4     \/     2     4      5
         *     |    /  \    |   /        |
         *     |  /      \  | /          |
         *    (3) ----4--- (4) ----7--- (5)
         * Correct MST:
         *  (1)
         *    0------1
         *           |
         *        3--4---2---5
         *  (2)
         *    0-----1
         *    |     |
         *    3     4----2---5
         *
         */
        GraphNode[] graph = new GraphNode[6];
        ArrayList<Integer> node0Edges = new ArrayList<>();
        ArrayList<Integer> node1Edges = new ArrayList<>();
        ArrayList<Integer> node2Edges = new ArrayList<>();
        ArrayList<Integer> node3Edges = new ArrayList<>();
        ArrayList<Integer> node4Edges = new ArrayList<>();
        ArrayList<Integer> node5Edges = new ArrayList<>();
        ArrayList<Integer> node0EWeights = new ArrayList<>();
        ArrayList<Integer> node1EWeights = new ArrayList<>();
        ArrayList<Integer> node2EWeights = new ArrayList<>();
        ArrayList<Integer> node3EWeights = new ArrayList<>();
        ArrayList<Integer> node4EWeights = new ArrayList<>();
        ArrayList<Integer> node5EWeights = new ArrayList<>();

        /* node 0 */
        node0Edges.add(1); node0Edges.add(3); node0Edges.add(4);
        node0EWeights.add(1); node0EWeights.add(4); node0EWeights.add(3);
        /* node 1 */
        node1Edges.add(0); node1Edges.add(3); node1Edges.add(4);
        node1EWeights.add(1); node1EWeights.add(4); node1EWeights.add(2);
        /* node 2 */
        node2Edges.add(4); node2Edges.add(5);
        node2EWeights.add(4); node2EWeights.add(5);
        /* node 3 */
        node3Edges.add(0); node3Edges.add(1); node3Edges.add(4);
        node3EWeights.add(4); node3EWeights.add(4); node3EWeights.add(4);
        /* node 4 */
        node4Edges.add(3); node4Edges.add(0); node4Edges.add(1); node4Edges.add(2); node4Edges.add(5);
        node4EWeights.add(4); node4EWeights.add(3); node4EWeights.add(2); node4EWeights.add(4);node4EWeights.add(7);
        /* node 5 */
        node5Edges.add(4); node5Edges.add(2);
        node5EWeights.add(7); node5EWeights.add(5);

        graph[0] = new GraphNode(0, Integer.MAX_VALUE, node0Edges, node0EWeights);
        graph[1] = new GraphNode(1, Integer.MAX_VALUE, node1Edges, node1EWeights);
        graph[2] = new GraphNode(2, Integer.MAX_VALUE, node2Edges, node2EWeights);
        graph[3] = new GraphNode(3, Integer.MAX_VALUE, node3Edges, node3EWeights);
        graph[4] = new GraphNode(4, Integer.MAX_VALUE, node4Edges, node4EWeights);
        graph[5] = new GraphNode(5, Integer.MAX_VALUE, node5Edges, node5EWeights);

        System.out.println("** Converting adjList to edgeList...");
        Kruskal kruskal = new Kruskal(graph);
        System.out.println("** Done **");
        System.out.println("*** Starting Kruskal ***");
        kruskal.doKruskal();
        System.out.println("*** Done ***");

    }
}
