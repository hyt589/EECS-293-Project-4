package parser;

import java.util.*;
import java.util.function.Function;

public final class Connector extends AbstractToken {

    private TerminalSymbol type;
    private static Cache<TerminalSymbol, Connector> cache = new Cache<>();
    private static Map<TerminalSymbol, String> typeMap = new HashMap<>();
    static {
        typeMap.put(TerminalSymbol.PLUS, "+");
        typeMap.put(TerminalSymbol.MINUS, "-");
        typeMap.put(TerminalSymbol.TIMES, "*");
        typeMap.put(TerminalSymbol.DIVIDE, "/");
        typeMap.put(TerminalSymbol.OPEN, "(");
        typeMap.put(TerminalSymbol.CLOSE, ")");
    }

    private Connector(TerminalSymbol type) {
        this.type = type;
    }

    @Override
    public TerminalSymbol getType() {
        return this.type;
    }

    public static final Connector build(TerminalSymbol connectorType) {
        if (typeMap.containsKey(Objects.requireNonNull(connectorType,"Connector type cannot be null!"))){
            Function<? super TerminalSymbol, ? extends Connector> connectorConstructor = (Void) -> new Connector(connectorType);
            return cache.get(connectorType, connectorConstructor);
        }
        else {
            throw new IllegalArgumentException("Illegal connector type!");
        }
    }

    @Override
    public String toString() {
        return typeMap.get(this.type);
    }

}