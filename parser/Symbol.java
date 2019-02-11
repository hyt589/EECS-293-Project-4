package parser;

import java.util.*;

interface Symbol {

    ParseState parse(List<Token> input);

}
