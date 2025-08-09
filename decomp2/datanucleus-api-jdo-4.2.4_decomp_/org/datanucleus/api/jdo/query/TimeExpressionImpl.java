package org.datanucleus.api.jdo.query;

import java.sql.Time;
import java.util.List;
import javax.jdo.query.NumericExpression;
import javax.jdo.query.PersistableExpression;
import javax.jdo.query.TimeExpression;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.query.expression.InvokeExpression;

public class TimeExpressionImpl extends ComparableExpressionImpl implements TimeExpression {
   public TimeExpressionImpl(PersistableExpression parent, String name) {
      super(parent, name);
   }

   public TimeExpressionImpl(Class cls, String name, ExpressionType type) {
      super(cls, name, type);
   }

   public TimeExpressionImpl(Expression queryExpr) {
      super(queryExpr);
   }

   public NumericExpression getHour() {
      Expression invokeExpr = new InvokeExpression(this.queryExpr, "getHour", (List)null);
      return new NumericExpressionImpl(invokeExpr);
   }

   public NumericExpression getMinute() {
      Expression invokeExpr = new InvokeExpression(this.queryExpr, "getMinute", (List)null);
      return new NumericExpressionImpl(invokeExpr);
   }

   public NumericExpression getSecond() {
      Expression invokeExpr = new InvokeExpression(this.queryExpr, "getSecond", (List)null);
      return new NumericExpressionImpl(invokeExpr);
   }
}
