import java.util.ArrayList;

/**
 * Created by derros on 2/4/18.
 * Runs depth-first-search using a custom testcase (similar to BFSRunner).
 */
public class DFSRunner {


    public static void main(String[] args) {
        GraphNode[] nodes = new GraphNode[5];
        for(int i=0;i<5;++i) {
            nodes[i] = new GraphNode();
            nodes[i].i = i;
            nodes[i].edges = new ArrayList<>();
        }
        nodes[0].edges.add(1);
        nodes[0].edges.add(4);
        nodes[1].edges.add(0);
        nodes[1].edges.add(2);
        nodes[2].edges.add(1);
        nodes[2].edges.add(4);
        nodes[3].edges.add(4);
        nodes[4].edges.add(0);
        nodes[4].edges.add(2);
        nodes[4].edges.add(3);
        DFS dfs = new DFS(nodes);
        System.out.println("Doing BFS on Graph.");
        dfs.doDFSCycles(0);
        System.out.println("DFS Finished.");
    }
}
