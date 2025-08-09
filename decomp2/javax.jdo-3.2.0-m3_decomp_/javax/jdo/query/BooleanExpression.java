package javax.jdo.query;

public interface BooleanExpression extends ComparableExpression {
   BooleanExpression and(BooleanExpression var1);

   BooleanExpression or(BooleanExpression var1);

   BooleanExpression not();

   BooleanExpression neg();
}
