package les06.graph.algorithm;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author 樊金亮
 * @date 2023/2/8
 */
public class Prim {
    public static class EdgeComparator implements Comparator<GraphGenerator.Edge> {

        @Override
        public int compare(GraphGenerator.Edge o1, GraphGenerator.Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static Set<GraphGenerator.Edge> primMST(GraphGenerator.Graph graph) {
        // 解锁的边进入小根堆
        PriorityQueue<GraphGenerator.Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        HashSet<GraphGenerator.Node> set = new HashSet<>();

        // 依次挑选的边在result中
        HashSet<GraphGenerator.Edge> result = new HashSet<>();

        for (GraphGenerator.Node node : graph.nodes.values()) {
            // node是开始点
            if (!set.contains(node)) {
                set.add(node);
                for(GraphGenerator.Edge edge : node.edges) {     // 由一个点解锁相连的边
                    priorityQueue.add(edge);
                }

                while (!priorityQueue.isEmpty()) {
                    GraphGenerator.Edge edge = priorityQueue.poll();    // 弹出解锁的边中最小的边
                    GraphGenerator.Node toNode = edge.to;   // 可能的一个新的点

                    if (!set.contains(toNode)) {    // 不含有的就是新的点
                        set.add(toNode);
                        result.add(edge);
                        for (GraphGenerator.Edge nextEdge : toNode.edges) {
                            priorityQueue.add(nextEdge);
                        }
                    }
                }
            }
        }

        return result;
    }
}
