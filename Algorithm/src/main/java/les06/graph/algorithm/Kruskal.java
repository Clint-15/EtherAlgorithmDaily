package les06.graph.algorithm;

import java.util.*;

/**
 * @author 樊金亮
 * @date 2023/2/8
 */
public class Kruskal {
    public static class MySets {
        public HashMap<GraphGenerator.Node, List<GraphGenerator.Node>> setMap;

        public MySets(List<GraphGenerator.Node> nodes) {
            for (GraphGenerator.Node node : nodes) {
                List<GraphGenerator.Node> set = new ArrayList<>();
                set.add(node);
                setMap.put(node, set);
            }
        }

        public boolean isSameSet(GraphGenerator.Node from, GraphGenerator.Node to) {
            List<GraphGenerator.Node> fromSet = setMap.get(from);
            List<GraphGenerator.Node> toSet = setMap.get(to);
            return fromSet == toSet;
        }

        public void union(GraphGenerator.Node from, GraphGenerator.Node to) {
            List<GraphGenerator.Node> fromSet = setMap.get(from);
            List<GraphGenerator.Node> toSet = setMap.get(to);

            for (GraphGenerator.Node toNode : toSet) {
                fromSet.add(toNode);
                setMap.put(toNode, fromSet);
            }
        }
    }

    public static class UnionFind {
        private HashMap<GraphGenerator.Node, GraphGenerator.Node> fatherMap;
        private HashMap<GraphGenerator.Node, Integer> sizeMap;

        public UnionFind() {
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
        }

        public void makeSets(Collection<GraphGenerator.Node> nodes) {
            fatherMap.clear();
            sizeMap.clear();
            for (GraphGenerator.Node node : nodes) {
                fatherMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        private GraphGenerator.Node findFather(GraphGenerator.Node n) {
            Stack<GraphGenerator.Node> path = new Stack<>();
            while (n != fatherMap.get(n)) {
                path.add(n);
                n = fatherMap.get(n);
            }

            while (!path.isEmpty()) {
                fatherMap.put(path.pop(), n);
            }

            return n;
        }

        public boolean isSameSet(GraphGenerator.Node a, GraphGenerator.Node b) {
            return findFather(a) == findFather(b);
        }

        public void union(GraphGenerator.Node a, GraphGenerator.Node b) {
            if (a == null || b == null) {
                return;
            }

            GraphGenerator.Node aDai = findFather(a);
            GraphGenerator.Node bDai = findFather(b);

            if (aDai != bDai) {
                int aSetSize = sizeMap.get(aDai);
                int bSetSize = sizeMap.get(bDai);

                if (aSetSize <= bSetSize) {
                    fatherMap.put(aDai, bDai);
                    sizeMap.put(bDai, aSetSize + bSetSize);
                    sizeMap.remove(aDai);
                } else {
                    fatherMap.put(bDai, aDai);
                    sizeMap.put(aDai, aSetSize + bSetSize);
                    sizeMap.remove(bDai);
                }
            }
        }
    }

    public static class EdgeComparator implements Comparator<GraphGenerator.Edge> {
        @Override
        public int compare(GraphGenerator.Edge o1, GraphGenerator.Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static Set<GraphGenerator.Edge> kruskalMST(GraphGenerator.Graph graph) {
        UnionFind unionFind = new UnionFind();
        unionFind.makeSets(graph.nodes.values());
        PriorityQueue<GraphGenerator.Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());

        for (GraphGenerator.Edge edge : graph.edges) {
            priorityQueue.add(edge);
        }

        Set<GraphGenerator.Edge> result = new HashSet<>();
        while(!priorityQueue.isEmpty()) {
            GraphGenerator.Edge edge = priorityQueue.poll();
            if (!unionFind.isSameSet(edge.from, edge.to)) {
                result.add(edge);
                unionFind.union(edge.from, edge.to);
            }
        }

        return result;
    }
}
