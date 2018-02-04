import java.util.ArrayList;

/**
 * Created by derros on 2/4/18.
 * Creates a test graph and runs Dijkstra's algorithm from 0.
 */

public class DijkstraRunner {
    public static void main(String[] args) {
        GraphNode[] graph = new GraphNode[6];
        /*
         * Graph:
         *   [0]  ---(5)------ [3] -----(19)------ [5]
         *    | \             /     \             /
         *    |  \           /        \          /
         *    |   \        (24)       (13)      (2)
         *    (2)  (3)    /               \    /
         *    |     \    /                  \ /
         *   [1]-(6)-[2] ---------(7)------ [4]
         */
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
        node0Edges.add(1); node0Edges.add(2); node0Edges.add(3);
        node0EWeights.add(2); node0EWeights.add(3); node0EWeights.add(5);
        /* node 1 */
        node1Edges.add(0); node1Edges.add(2);
        node1EWeights.add(2); node1EWeights.add(6);
        /* node 2 */
        node2Edges.add(1); node2Edges.add(0); node2Edges.add(3); node2Edges.add(4);
        node2EWeights.add(6); node2EWeights.add(3); node2EWeights.add(24); node2EWeights.add(7);
        /* node 3 */
        node3Edges.add(0); node3Edges.add(2); node3Edges.add(4); node3Edges.add(5);
        node3EWeights.add(5); node3EWeights.add(24); node3EWeights.add(13); node3EWeights.add(19);
        /* node 4 */
        node4Edges.add(2); node4Edges.add(3); node4Edges.add(5);
        node4EWeights.add(7); node4EWeights.add(13); node4EWeights.add(2);
        /* node 5 */
        node5Edges.add(3); node5Edges.add(4);
        node5EWeights.add(19); node5EWeights.add(2);

        graph[0] = new GraphNode(0, Integer.MAX_VALUE, node0Edges, node0EWeights);
        graph[1] = new GraphNode(1, Integer.MAX_VALUE, node1Edges, node1EWeights);
        graph[2] = new GraphNode(2, Integer.MAX_VALUE, node2Edges, node2EWeights);
        graph[3] = new GraphNode(3, Integer.MAX_VALUE, node3Edges, node3EWeights);
        graph[4] = new GraphNode(4, Integer.MAX_VALUE, node4Edges, node4EWeights);
        graph[5] = new GraphNode(5, Integer.MAX_VALUE, node5Edges, node5EWeights);

        Dijkstra dijkstra = new Dijkstra(graph);

        System.out.println("***** Doing Dijkstra *****");
        dijkstra.doDijkstra(0);
        System.out.println("***** Dijkstra Run Finished *****");

        System.out.println(" ----- Parents -----");
        dijkstra.printParents();
        System.out.println(" -------------------");

        System.out.println(" ----- Distances ----- ");
        dijkstra.printDist();
        System.out.println(" --------------------- ");
    }
}
