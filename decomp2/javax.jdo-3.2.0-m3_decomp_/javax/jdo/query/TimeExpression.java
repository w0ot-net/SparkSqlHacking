package javax.jdo.query;

public interface TimeExpression extends ComparableExpression {
   NumericExpression getHour();

   NumericExpression getMinute();

   NumericExpression getSecond();
}
