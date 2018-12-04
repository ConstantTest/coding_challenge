package cisco.java.challenge.task1;

import cisco.java.challenge.GNode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GNodeApp {

    private ArrayList<ArrayList<GNode>> paths = new ArrayList<>();
    private Set<GNode> visitedNodes = new HashSet<>();
    private int counter;
    private Map<String, Integer> numberOfNodes = new HashMap<>();
    private ArrayList<GNode> nodes;

    public static void main(String[] args) {

        GNodeApp gNodeApp = new GNodeApp();

        GNode nodeD = new GNodeImpl("D", new GNode[]{});
        GNode nodeI = new GNodeImpl("I", new GNode[]{});
        GNode nodeH = new GNodeImpl("H", new GNode[]{});
        GNode nodeG = new GNodeImpl("G", new GNode[]{});
        GNode nodeC = new GNodeImpl("C", new GNode[]{nodeG, nodeH, nodeI});
        GNode nodeF = new GNodeImpl("F", new GNode[]{});
        GNode nodeE = new GNodeImpl("E", new GNode[]{});
        GNode nodeB = new GNodeImpl("B", new GNode[]{nodeE, nodeF});
        GNode nodeA = new GNodeImpl("A", new GNode[]{nodeB, nodeC, nodeD});
        ((GNodeImpl)nodeD).setChildren(new GNode[]{nodeA});

        gNodeApp.paths(nodeA);
    }

    public ArrayList<GNode> walkGraph(GNode gNode) {

        visitedNodes.add(gNode);

        if (gNode.getChildren().length > 0) {
            setAmountOfNodes(gNode.getName(), gNode.getChildren().length);
            counter = numberOfNodes.get(gNode.getName());
        }

        for (GNode node : gNode.getChildren()) {
            if (gNode.getChildren().length > 0) {
                if (visitedNodes.contains(node)) {
                    nodes = new ArrayList<>(visitedNodes);
                    nodes.sort(Comparator.comparing(GNode::getName));
                    paths.add(nodes);
                    break;
                }
                walkGraph(node);
            }

            if (node.getChildren().length == 0 && visitedNodes.contains(node)) {
                nodes = new ArrayList<>(visitedNodes);
                nodes.sort(Comparator.comparing(GNode::getName));

                visitedNodes.remove(node);
                paths.add(nodes); // add list of visited nodes to paths list
                printResult(nodes); // print current result
                counter--;
                numberOfNodes.put(gNode.getName(), counter); // update counter info
            }

            if (counter == 0 && numberOfNodes.get(gNode.getName()) == 0) {
                visitedNodes.remove(gNode);
            }
        }
        return nodes;
    }

    private void setAmountOfNodes(String node, int amount) {
        numberOfNodes.put(node, amount);
    }

    private static void printResult(ArrayList<GNode> list) {
        System.out.println(list);
    }

    public ArrayList<ArrayList<GNode>> paths(GNode gNode) {
        walkGraph(gNode);
        System.out.println("paths(" + gNode.getName() + ") = " + paths);
        return paths;
    }
}
