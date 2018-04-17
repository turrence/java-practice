abstract class BinaryExpression implements Expression{

	private final Expression lft;
 	private final Expression rht;
	private final String op;

	public BinaryExpression(final Expression lft, final Expression rht, final String op){
		this.lft = lft;
		this.rht = rht;
		this.op = op;
	}

	public String toString()
	{
	   return "(" + lft + " " + op + " " + rht + ")";
	}

	public double evaluate(final Bindings bindings)
	{
	   return _applyOperator(lft.evaluate(bindings), rht.evaluate(bindings));
	}

	abstract protected double _applyOperator(final double left, final double right);
}
