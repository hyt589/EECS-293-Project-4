package parser;

public final class Variable extends AbstractToken {

    private TerminalSymbol type = TerminalSymbol.VARIABLE;
    private final String representation;
    private static Cache<String, Variable> cache = new Cache<>();

    private Variable(String representation) {
        this.representation = representation;
    }

    public final TerminalSymbol getType() {
        return this.type;
    }

    public final String getRepresentation() {
        return representation;
    }

    public static final Variable build(String representation) {
        if(representation != null) {
            return cache.get(representation, Variable::new);
        }
        else{
            throw new NullPointerException("Null representation string");
        }
    }

    @Override
    public String toString() {
        return representation;
    }

}