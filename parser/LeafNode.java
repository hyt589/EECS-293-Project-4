package parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeafNode implements Node {

    private final Token token;

    private LeafNode(Token token) {
        this.token = token;
    }

    public Token getToken() {
        return token;
    }

    public static final LeafNode build(Token token) {
        return new LeafNode(token);
    }

    @Override
    public List<Token> toList() {
        return new ArrayList<>(Arrays.asList(token));
    }

    @Override
    public String toString() {
        return token.toString();
    }
}
