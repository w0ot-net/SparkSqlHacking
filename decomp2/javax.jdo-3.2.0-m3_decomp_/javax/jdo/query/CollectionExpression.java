package javax.jdo.query;

public interface CollectionExpression extends Expression {
   BooleanExpression contains(Expression var1);

   BooleanExpression contains(Object var1);

   BooleanExpression isEmpty();

   NumericExpression size();
}
