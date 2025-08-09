package javax.jdo.query;

public interface Expression {
   BooleanExpression eq(Expression var1);

   BooleanExpression eq(Object var1);

   BooleanExpression ne(Expression var1);

   BooleanExpression ne(Object var1);

   NumericExpression count();

   NumericExpression countDistinct();

   BooleanExpression instanceOf(Class var1);

   Expression cast(Class var1);
}
