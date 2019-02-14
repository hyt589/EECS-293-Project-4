package parser;

import java.util.*;

public final class LeafNode implements Node {

    private final Token token;

    private LeafNode(Token token) {
        this.token = token;
    }

    public Token getToken() {
        return token;
    }

    public static final LeafNode build(Token token) {
        return new LeafNode(Objects.requireNonNull(token, "Token cannot be null!"));
    }

    @Override
    public final List<Token> toList() {
        ArrayList<Token> tokenList = new ArrayList<>();
        tokenList.add(token);
        return tokenList;
    }

    @Override
    public List<Node> getChildren(){
        return null;
    }

    @Override
    public boolean isFruitful(){
        return true;
    }

    @Override
    public Node simplify(){
        return this;
    }

    @Override
    public String toString() {
        return token.toString();
    }

}
