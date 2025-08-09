package org.datanucleus.api.jdo.query;

import java.util.List;
import javax.jdo.query.EnumExpression;
import javax.jdo.query.NumericExpression;
import javax.jdo.query.PersistableExpression;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.query.expression.InvokeExpression;

public class EnumExpressionImpl extends ComparableExpressionImpl implements EnumExpression {
   public EnumExpressionImpl(PersistableExpression parent, String name) {
      super(parent, name);
   }

   public EnumExpressionImpl(Class cls, String name, ExpressionType type) {
      super(cls, name, type);
   }

   public EnumExpressionImpl(Expression queryExpr) {
      super(queryExpr);
   }

   public NumericExpression ordinal() {
      Expression invokeExpr = new InvokeExpression(this.queryExpr, "ordinal", (List)null);
      return new NumericExpressionImpl(invokeExpr);
   }
}
