package javax.jdo.query;

import java.util.Map;

public interface MapExpression extends Expression {
   BooleanExpression containsKey(Expression var1);

   BooleanExpression containsKey(Object var1);

   BooleanExpression containsValue(Expression var1);

   BooleanExpression containsValue(Object var1);

   BooleanExpression containsEntry(Expression var1);

   BooleanExpression containsEntry(Map.Entry var1);

   BooleanExpression isEmpty();

   NumericExpression size();
}
