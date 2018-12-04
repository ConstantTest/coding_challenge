package cisco.java.challenge.task1;

import cisco.java.challenge.GNode;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.*;


public class GNodeAppTest {

    private GNodeApp gNodeApp;
    private Map<String, GNode> nodeMap;

    @Before
    public void setUp() {
        gNodeApp = new GNodeApp();
        nodeMap = new HashMap<>();

        GNode nodeD = new GNodeImpl("D", new GNode[]{});
        GNode nodeI = new GNodeImpl("I", new GNode[]{});
        GNode nodeH = new GNodeImpl("H", new GNode[]{});
        GNode nodeG = new GNodeImpl("G", new GNode[]{});
        GNode nodeC = new GNodeImpl("C", new GNode[]{nodeG, nodeH, nodeI});
        GNode nodeF = new GNodeImpl("F", new GNode[]{});
        GNode nodeE = new GNodeImpl("E", new GNode[]{});
        GNode nodeB = new GNodeImpl("B", new GNode[]{nodeE, nodeF});
        GNode nodeA = new GNodeImpl("A", new GNode[]{nodeB, nodeC, nodeD});

        // add to set
        nodeMap.put(nodeD.getName(), nodeD);
        nodeMap.put(nodeI.getName(), nodeI);
        nodeMap.put(nodeH.getName(), nodeH);
        nodeMap.put(nodeG.getName(), nodeG);
        nodeMap.put(nodeC.getName(), nodeC);
        nodeMap.put(nodeF.getName(), nodeF);
        nodeMap.put(nodeE.getName(), nodeE);
        nodeMap.put(nodeB.getName(), nodeB);
        nodeMap.put(nodeA.getName(), nodeA);
    }

    @Test
    public void getChildrenTest() {
        // when a GNode has no  children, getChildren() returns an array of size 0, and *not* null
        GNode nodeD = nodeMap.get("D");

        assertNotNull(nodeD);
        assertNotNull(nodeD.getChildren());
        assertEquals(0, nodeD.getChildren().length);
    }

    @Test
    public void pathsTest() {

        ArrayList<ArrayList<GNode>> allPaths = gNodeApp.paths(nodeMap.get("A")); // there are 6 paths
        assertEquals(6, allPaths.size());
    }

    @Test
    public void walkGraphTest() {

        ArrayList<GNode> nodesBE = gNodeApp.walkGraph(nodeMap.get("B")); // walk nodes B, F
        assertEquals(2, nodesBE.size());
        assertEquals("B", nodesBE.get(0).getName());
        assertEquals("F", nodesBE.get(1).getName());

        ArrayList<GNode> nodesACI = gNodeApp.walkGraph(nodeMap.get("A")); // walk nodes A, D
        assertEquals(2, nodesACI.size());
        assertEquals("A", nodesACI.get(0).getName());
        assertEquals("D", nodesACI.get(1).getName());
    }

    @Test
    public void walkGraphWhenBothNodesAssignedToEachOtherTest() {

        GNode nodeD = nodeMap.get("D");
        GNode nodeA = nodeMap.get("A");

        // nodeD does not contain nodeA
        assertNotNull(nodeD.getChildren());
        assertEquals(0, nodeD.getChildren().length);

        // add nodeA to nodeD
        ((GNodeImpl)nodeD).setChildren(new GNode[]{nodeA});

        // nodeA is added to nodeD
        assertEquals("A", nodeD.getChildren()[0].getName());

        // start walking from A through graph to node D. Number of paths does not changed
        ArrayList<ArrayList<GNode>> allPaths = gNodeApp.paths(nodeMap.get("A")); // there are 6 paths
        assertEquals(6, allPaths.size());

        // nodes A, D
        ArrayList<GNode> nodesACI = gNodeApp.walkGraph(nodeMap.get("A")); // walk nodes A, D
        assertEquals(2, nodesACI.size());
        assertEquals("A", nodesACI.get(0).getName());
        assertEquals("D", nodesACI.get(1).getName());
    }
}
