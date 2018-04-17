class AddExpression
   extends BinaryExpression
{
   public AddExpression(final Expression lft, final Expression rht)
   {
      super(lft, rht, "+");
   }

   public double _applyOperator(final double left, final double right){
       return left + right;
   }
}
