/**
 * Created by Nastya on 11.10.2017.
 * A class that denotes a basic expression
 */
public abstract class Expression {
    Expression left, right;

    /**
     * Calculates the expression
     * @return the result of the calculation
     */
    abstract int calculate();

    /**
     * This method is used only for printing the AST
     * The implementation was adopted from https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram
     * @return a graphical representation of a tree
     */
    public StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb) {
        if(right!=null) {
            right.toString(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
        }
        if(left!=null) {
            left.toString(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
        }
        return sb;
    }

    @Override
    public String toString() {
        return this.toString(new StringBuilder(), true, new StringBuilder()).toString();
    }

}
