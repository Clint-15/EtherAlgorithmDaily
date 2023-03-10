package les06.graph.algorithm;

import java.util.*;

/**
 * @author 樊金亮
 * @date 2023/2/8
 */
public class TopologySort {
    public static List<GraphGenerator.Node> topologySort(GraphGenerator.Graph graph) {
        HashMap<GraphGenerator.Node, Integer> inMap = new HashMap<>();
        Queue<GraphGenerator.Node> zeroInQueue = new LinkedList<>();

        for (GraphGenerator.Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroInQueue.add(node);
            }
        }

        List<GraphGenerator.Node> result = new ArrayList<>();
        while (!zeroInQueue.isEmpty()) {
            GraphGenerator.Node cur = zeroInQueue.poll();
            result.add(cur);
            for (GraphGenerator.Node next : cur.nexts) {
                inMap.put(next, inMap.get(next) - 1);

                if (inMap.get(next) == 0) {
                    zeroInQueue.add(next);
                }
            }
        }

        return result;
    }
}
