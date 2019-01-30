package parser;

public final class Connector extends AbstractToken {

    private TerminalSymbol type;
    private static Cache<TerminalSymbol, Connector> cache = new Cache<>();

    private Connector(TerminalSymbol type) {
        this.type = type;
    }

    public TerminalSymbol getType() {
        return type;
    }

    public static final Connector build(String representation) throws NullPointerException, IllegalArgumentException {
        if (representation == null) {
            throw new NullPointerException("Connector type is null");
        }
        else if (legalType(representation)){
            return cache.get(typeFromString(representation), Connector::new);
        }
        else {
            throw new IllegalArgumentException("Illegal connector type");
        }
    }

    private static boolean legalType(String representation){
        return (representation.equals('+') ||
                representation.equals('-') ||
                representation.equals('*') ||
                representation.equals('/') ||
                representation.equals('(') ||
                representation.equals(')'));
    }

    private static TerminalSymbol typeFromString(String representation){
        switch (representation){
            case "+":
                return TerminalSymbol.PLUS;
            case "-":
                return TerminalSymbol.MINUS;
            case "*":
                return TerminalSymbol.TIMES;
            case "/":
                return TerminalSymbol.DIVIDE;
            case "(":
                return TerminalSymbol.OPEN;
            case ")":
                return TerminalSymbol.CLOSE;
            default:
                throw(new IllegalArgumentException("Illegal representation"));
        }
    }

    @Override
    public String toString() {
        switch (this.type) {
            case PLUS:
                return "+";
            case MINUS:
                return "-";
            case TIMES:
                return "*";
            case DIVIDE:
                return "/";
            case OPEN:
                return "(";
            case CLOSE:
                return ")";
            default:
                throw(new IllegalArgumentException("Illegal type"));
        }

    }
}
