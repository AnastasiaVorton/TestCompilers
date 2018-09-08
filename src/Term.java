/**
 * Created by Nastya on 11.10.2017.
 * A class that denotes a term
 */
public class Term extends Expression {
    enum Opcode {plus, minus, none}

    Opcode opcode;
    Expression leftFactor, rightFactor;

    public Term(Opcode opcode, Expression leftFactor, Expression rightFactor) {
        this.opcode = opcode;
        this.leftFactor = leftFactor;
        this.rightFactor = rightFactor;
    }

    @Override
    int calculate() {
        int result1 = leftFactor.calculate();
        int result2 = rightFactor.calculate();
        switch (opcode) {
            case plus:
                return result1 + result2;
            case minus:
                return result1 - result2;
            case none:
                return result1;
        }
        return result1;
    }

    @Override
    public StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb) {
        if (rightFactor != null) {
            rightFactor.toString(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
        }
        sb.append(prefix).append(isTail ? "└── " : "┌── ").append(opcode.toString()).append("\n");
        if (leftFactor != null) {
            leftFactor.toString(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
        }
        return sb;
    }
}
