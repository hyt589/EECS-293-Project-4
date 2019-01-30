package parser;

import java.util.ArrayList;
import java.util.List;

public final class InternalNode implements Node{

    private final List<Node> children;

    public List<Node> getChildren(){
        return new ArrayList<Node>(children);
    }

    //TODO make this.children unmodifiable copies
    private InternalNode(List<Node> children){
        this.children = children;
    }

    //TODO handle null pointers
    public static final InternalNode build(List<Node> children) {
        return new InternalNode(children);
    }

    //TODO do this method
    public final List<Token> toList(){
        return new ArrayList<Token>();

    }

    //TODO write the toString
    @Override
    public String toString(){
        return "";
    }
}
