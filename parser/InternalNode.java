package parser;

import java.util.ArrayList;
import java.util.List;

public final class InternalNode implements Node{

    private final List<Node> children;
    private List<Token> representationList = null;
    private String representationString = null;

    public List<Node> getChildren(){
        return new ArrayList<>(children);
    }

    private InternalNode(List<Node> children){
        this.children = new ArrayList<>(children);
    }

    public static final InternalNode build(List<Node> children) {
        if (children == null){
            throw(new NullPointerException("Null children"));
        }
        else {
            return new InternalNode(children);
        }
    }

    public final List<Token> toList(){
        if (representationList == null){
            representationList = new ArrayList<>();
            representationList.addAll(children.get(0).toList());
            representationList.addAll(children.get(1).toList());
        }
        return representationList;
    }

    @Override
    public String toString(){
        if (representationString == null){
            representationString = "[" + children.get(0).toString() + "," + children.get(1).toString() + "]";
        }
        return representationString;
    }

}
