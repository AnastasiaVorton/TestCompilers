/**
 * Created by Nastya on 13.10.2017.
 */
public class Paranthersized extends Primary{

    private Expression expression;

    @Override
    public int calculate() {
        return expression.calculate();
    }

}
