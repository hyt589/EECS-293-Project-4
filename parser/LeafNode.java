package parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class LeafNode implements Node {

    private final Token token;

    private LeafNode(Token token) {
        this.token = token;
    }

    public Token getToken() {
        return token;
    }

    public static final LeafNode build(Token token) {
        return new LeafNode(Objects.requireNonNull(token, "Token cannot be null"));
    }

    @Override
    public final List<Token> toList() {
        ArrayList<Token> tokenList = new ArrayList<>();
        tokenList.add(token);
        return tokenList;
    }

    @Override
    public String toString() {
        return token.toString();
    }

}
