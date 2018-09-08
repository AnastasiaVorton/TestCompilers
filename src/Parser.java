import java.lang.*;

/**
 * Created by Nastya on 11.10.2017.
 * A class that parses an input string and builds an AST based on the
 * class hierarchy implemented in the project
 */
public class Parser {
    private String input;
    int pointer = 0;

    public Parser(String s) {
        input = s;
    }

    public Expression parse() {
        return parseRelation();
    }

    private Expression parseRelation() {
        Expression output = parseTerm();
        Relation.Opcode op = parseRelOperator();
        if (op != Relation.Opcode.none) {
            Expression right = parseTerm();
            output = new Relation(op, output, right);
        }
        return output;
    }

    Expression parseTerm() {
        Expression output = parseFactor();
        Term.Opcode op = parseTermOperator();
        if (op != Term.Opcode.none) {
            Expression right = parseTerm();
            output = new Term(op, output, right);
        }
        return output;
    }

    Expression parseFactor() {
        Expression output = parsePrimary();
        Factor.Opcode op = parseFactOperator();
        if (op != Factor.Opcode.none) {
            Expression right = parseFactor();
            output = new Factor(op, output, right);
        }
        return output;
    }

    Expression parsePrimary() {
        Expression output = null;
        if (Character.isDigit(input.charAt(pointer))) {
            output = parseInteger();
        } else if (input.charAt(pointer) == '(') {
            pointer++;
            output = parse();
            pointer++;
        } else {
            return null;
        }
        return output;
    }

    Expression parseInteger() {
        Integer output = new Integer();
        int startPosition = pointer;
        while (pointer < input.length() && Character.isDigit(input.charAt(pointer))) {
            pointer++;
        }
        output.setAnInt(java.lang.Integer.parseInt(input.substring(startPosition, pointer)));
        return output;
    }

    Relation.Opcode parseRelOperator() {
        try {
            if (input.substring(pointer, pointer + 1).equals("<")) {
                pointer += 1;
                return Relation.Opcode.less;
            }
            if (input.substring(pointer, pointer + 1).equals(">")) {
                pointer += 1;
                return Relation.Opcode.greater;
            }
            if (input.substring(pointer, pointer + 1).equals("=")) {
                pointer += 1;
                return Relation.Opcode.equal;
            }
        } catch (StringIndexOutOfBoundsException ex) {
            return Relation.Opcode.none;
        }
        return Relation.Opcode.none;
    }

    Term.Opcode parseTermOperator() {
        try {
            if (input.substring(pointer, pointer + 1).equals("+")) {
                pointer += 1;
                return Term.Opcode.plus;
            }
            if (input.substring(pointer, pointer + 1).equals("-")) {
                pointer += 1;
                return Term.Opcode.minus;
            }
        } catch (StringIndexOutOfBoundsException ex) {
            return Term.Opcode.none;
        }
        return Term.Opcode.none;
    }

    Factor.Opcode parseFactOperator() {
        try {
            if (input.substring(pointer, pointer + 1).equals("*")) {
                pointer += 1;
                return Factor.Opcode.multiply;
            }
        } catch (StringIndexOutOfBoundsException ex) {
            return Factor.Opcode.none;
        }
        return Factor.Opcode.none;
    }

}
