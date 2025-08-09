package javax.jdo.query;

public interface DateExpression extends ComparableExpression {
   NumericExpression getYear();

   NumericExpression getMonth();

   NumericExpression getDay();
}
