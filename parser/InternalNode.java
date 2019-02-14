package parser;

import java.util.*;
import java.util.stream.Collectors;

public final class InternalNode implements Node{

    private final List<Node> children;
    private List<Token> representationList = null;
    private String representationString = null;

    private InternalNode(List<Node> children){
        this.children = new ArrayList<>(children);
    }

    public static final InternalNode build(List<Node> children) {
        return new InternalNode(Objects.requireNonNull(children, "Children cannot be null!"));
    }

    @Override
    public final List<Token> toList(){
        if (representationList == null){
            representationList = new ArrayList<>();

            for (Node child : children) {
                representationList.addAll(child.toList());
            }
        }
        return representationList;
    }

    @Override
    public List<Node> getChildren(){
        List<Node> childList = new ArrayList<>(children);
        return childList;
    }

    @Override
    public boolean isFruitful(){
        return !this.getChildren().isEmpty();
    }

    @Override
    public Node simplify(){
        Node simplifiedNode = this;

        if(simplifiedNode.getChildren().size() == 1) {
            Builder childBuilder = new Builder();
            childBuilder.children = simplifiedNode.getChildren();
            simplifiedNode = childBuilder.children.get(0);
        }
        return simplifiedNode;
    }

    @Override
    public String toString(){
        if (representationString == null){
            representationString = String.join(",",children.toString());
        }
        return representationString;
    }

    public static class Builder{

        private List<Node> children = new ArrayList<>();

        public boolean addChild(Node node){
            return this.children.add(node);
        }

        public Builder simplify(){
            this.children = this.children.stream()
                    .filter(child -> child.isFruitful())
                    .map(child -> child.simplify())
                    .collect(Collectors.toList());
            return this;
        }

        public InternalNode build(){
            return InternalNode.build(this.simplify().children);
        }
    }

}
