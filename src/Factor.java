/**
 * Created by Nastya on 11.10.2017.
 * A class that denotes a factor
 */
public class Factor extends Expression {
    enum Opcode {multiply, none}

    Opcode opcode;
    Expression leftPrimary, rightPrimary;

    public Factor(Opcode opcode, Expression leftPrimary, Expression rightPrimary) {
        this.opcode = opcode;
        this.leftPrimary = leftPrimary;
        this.rightPrimary = rightPrimary;
    }

    @Override
    public int calculate() {
        int result1 = leftPrimary.calculate();
        int result2 = rightPrimary.calculate();
        switch (opcode) {
            case multiply:
                return result1 * result2;
            case none:
                return result1;
        }
        return result1;
    }


    @Override
    public StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb) {
        if (rightPrimary != null) {
            rightPrimary.toString(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
        }
        sb.append(prefix).append(isTail ? "└── " : "┌── ").append(opcode.toString()).append("\n");
        if (leftPrimary != null) {
            leftPrimary.toString(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
        }
        return sb;
    }
}
