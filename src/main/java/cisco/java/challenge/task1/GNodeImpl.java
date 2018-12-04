package cisco.java.challenge.task1;

import cisco.java.challenge.GNode;

public class GNodeImpl implements GNode {
    private String name;
    private GNode[] gNodes;

    public GNodeImpl(String name, GNode[] gNodes) {
        this.name= name;
        this.gNodes = gNodes;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public GNode[] getChildren() {
        return gNodes;
    }

    void setChildren(GNode[] gNodes) {
        this.gNodes = gNodes;
    }

    @Override
    public String toString() {
        return name;
    }
}
