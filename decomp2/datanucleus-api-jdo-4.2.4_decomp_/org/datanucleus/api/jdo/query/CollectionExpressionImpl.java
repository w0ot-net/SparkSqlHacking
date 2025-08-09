package org.datanucleus.api.jdo.query;

import java.util.ArrayList;
import java.util.List;
import javax.jdo.query.BooleanExpression;
import javax.jdo.query.CollectionExpression;
import javax.jdo.query.NumericExpression;
import javax.jdo.query.PersistableExpression;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.query.expression.InvokeExpression;
import org.datanucleus.query.expression.Literal;

public class CollectionExpressionImpl extends ExpressionImpl implements CollectionExpression {
   public CollectionExpressionImpl(PersistableExpression parent, String name) {
      super(parent, name);
   }

   public CollectionExpressionImpl(Class cls, String name, ExpressionType type) {
      super(cls, name, type);
   }

   public BooleanExpression contains(Object elem) {
      List<Expression> args = new ArrayList();
      args.add(new Literal(elem));
      Expression invokeExpr = new InvokeExpression(this.queryExpr, "contains", args);
      return new BooleanExpressionImpl(invokeExpr);
   }

   public BooleanExpression contains(javax.jdo.query.Expression expr) {
      List<Expression> args = new ArrayList();
      args.add(((ExpressionImpl)expr).getQueryExpression());
      Expression invokeExpr = new InvokeExpression(this.queryExpr, "contains", args);
      return new BooleanExpressionImpl(invokeExpr);
   }

   public BooleanExpression isEmpty() {
      Expression invokeExpr = new InvokeExpression(this.queryExpr, "isEmpty", (List)null);
      return new BooleanExpressionImpl(invokeExpr);
   }

   public NumericExpression size() {
      Expression invokeExpr = new InvokeExpression(this.queryExpr, "size", (List)null);
      return new NumericExpressionImpl(invokeExpr);
   }
}
