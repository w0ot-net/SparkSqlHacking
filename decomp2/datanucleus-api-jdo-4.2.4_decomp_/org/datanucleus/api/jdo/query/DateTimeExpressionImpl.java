package org.datanucleus.api.jdo.query;

import java.util.Date;
import java.util.List;
import javax.jdo.query.DateTimeExpression;
import javax.jdo.query.NumericExpression;
import javax.jdo.query.PersistableExpression;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.query.expression.InvokeExpression;

public class DateTimeExpressionImpl extends ComparableExpressionImpl implements DateTimeExpression {
   public DateTimeExpressionImpl(PersistableExpression parent, String name) {
      super(parent, name);
   }

   public DateTimeExpressionImpl(Class cls, String name, ExpressionType type) {
      super(cls, name, type);
   }

   public DateTimeExpressionImpl(Expression queryExpr) {
      super(queryExpr);
   }

   public NumericExpression getDay() {
      Expression invokeExpr = new InvokeExpression(this.queryExpr, "getDay", (List)null);
      return new NumericExpressionImpl(invokeExpr);
   }

   public NumericExpression getHour() {
      Expression invokeExpr = new InvokeExpression(this.queryExpr, "getHour", (List)null);
      return new NumericExpressionImpl(invokeExpr);
   }

   public NumericExpression getMinute() {
      Expression invokeExpr = new InvokeExpression(this.queryExpr, "getMinute", (List)null);
      return new NumericExpressionImpl(invokeExpr);
   }

   public NumericExpression getMonth() {
      Expression invokeExpr = new InvokeExpression(this.queryExpr, "getMonth", (List)null);
      return new NumericExpressionImpl(invokeExpr);
   }

   public NumericExpression getSecond() {
      Expression invokeExpr = new InvokeExpression(this.queryExpr, "getSecond", (List)null);
      return new NumericExpressionImpl(invokeExpr);
   }

   public NumericExpression getYear() {
      Expression invokeExpr = new InvokeExpression(this.queryExpr, "getYear", (List)null);
      return new NumericExpressionImpl(invokeExpr);
   }
}
