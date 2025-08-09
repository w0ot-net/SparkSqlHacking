package javax.jdo.query;

public interface ComparableExpression extends Expression {
   BooleanExpression lt(ComparableExpression var1);

   BooleanExpression lt(Object var1);

   BooleanExpression lteq(ComparableExpression var1);

   BooleanExpression lteq(Object var1);

   BooleanExpression gt(ComparableExpression var1);

   BooleanExpression gt(Object var1);

   BooleanExpression gteq(ComparableExpression var1);

   BooleanExpression gteq(Object var1);

   NumericExpression min();

   NumericExpression max();

   OrderExpression asc();

   OrderExpression desc();
}
