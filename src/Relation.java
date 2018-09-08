/**
 * Created by Nastya on 11.10.2017.
 * A class that denotes a relation.
 */
public class Relation extends Expression {
    enum Opcode {less, greater, equal, none}

    Opcode opcode;
    Expression leftFactor, rightFactor;

    public Relation(Opcode opcode, Expression leftFactor, Expression reightFactor) {
        this.opcode = opcode;
        this.leftFactor = leftFactor;
        this.rightFactor = reightFactor;
    }

    @Override
    public int calculate() {
        int result1 = leftFactor.calculate();
        int result2 = rightFactor.calculate();
        switch (opcode) {
            case less:
                return (result1 < result2) ? 1 : 0;
            case greater:
                return (result1 > result2) ? 1 : 0;
            case equal:
                return (result1 == result2) ? 1 : 0;
            case none:
                return -1;
        }
        return -1;
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
