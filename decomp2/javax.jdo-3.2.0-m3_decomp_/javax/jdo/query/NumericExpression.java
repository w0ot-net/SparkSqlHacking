package javax.jdo.query;

public interface NumericExpression extends ComparableExpression {
   NumericExpression add(Expression var1);

   NumericExpression add(Number var1);

   NumericExpression sub(Expression var1);

   NumericExpression sub(Number var1);

   NumericExpression mul(Expression var1);

   NumericExpression mul(Number var1);

   NumericExpression div(Expression var1);

   NumericExpression div(Number var1);

   NumericExpression mod(Expression var1);

   NumericExpression mod(Number var1);

   NumericExpression neg();

   NumericExpression com();

   NumericExpression avg();

   NumericExpression sum();

   NumericExpression abs();

   NumericExpression sqrt();

   NumericExpression acos();

   NumericExpression asin();

   NumericExpression atan();

   NumericExpression sin();

   NumericExpression cos();

   NumericExpression tan();

   NumericExpression exp();

   NumericExpression log();

   NumericExpression ceil();

   NumericExpression floor();

   NumericExpression bAnd(NumericExpression var1);

   NumericExpression bOr(NumericExpression var1);

   NumericExpression bXor(NumericExpression var1);
}
