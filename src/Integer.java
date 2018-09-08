/**
 * Created by Nastya on 11.10.2017.
 * A class that denotes an integer value
 */
public class Integer extends Primary{
    private int anInt;

    public void setAnInt(int anInt) {
        this.anInt = anInt;
    }


    @Override
    public int calculate() {
        return anInt;
    }

    @Override
    public StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb) {
        sb.append(prefix).append(isTail ? "└── " : "┌── ").append(anInt + "\n");
        return sb;
    }
}
