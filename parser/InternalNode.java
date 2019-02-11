package parser;

import java.util.*;

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
        return new ArrayList<>(children);
    }

    @Override
    public boolean isFruitful(){
        if(!this.getChildren().isEmpty())
            return true;
        else{
            return false;
        }
    }

    @Override
    public String toString(){
        if (representationString == null){
            representationString = "[";
            for (int i = 0; i < children.size(); i++){
                if (i < children.size() -1) {
                    representationString += children.get(i).toString() + ",";
                }
                else{
                    representationString += children.get(i).toString();
                }
            }
            representationString += "]";
        }
        return representationString;
    }

}
