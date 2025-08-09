package javax.jdo.query;

public interface DateTimeExpression extends ComparableExpression {
   NumericExpression getYear();

   NumericExpression getMonth();

   NumericExpression getDay();

   NumericExpression getHour();

   NumericExpression getMinute();

   NumericExpression getSecond();
}
