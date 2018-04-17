class DivideExpression
   extends BinaryExpression
{
   public DivideExpression(final Expression lft, final Expression rht)
   {
      super(lft, rht, "/");
   }

   public double _applyOperator(final double left, final double right){
       return left / right;
   }
}
