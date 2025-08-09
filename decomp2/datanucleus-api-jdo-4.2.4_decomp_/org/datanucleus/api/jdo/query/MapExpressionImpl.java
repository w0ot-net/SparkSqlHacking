package org.datanucleus.api.jdo.query;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.jdo.query.BooleanExpression;
import javax.jdo.query.MapExpression;
import javax.jdo.query.NumericExpression;
import javax.jdo.query.PersistableExpression;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.query.expression.InvokeExpression;
import org.datanucleus.query.expression.Literal;

public class MapExpressionImpl extends ExpressionImpl implements MapExpression {
   public MapExpressionImpl(PersistableExpression parent, String name) {
      super(parent, name);
   }

   public MapExpressionImpl(Class cls, String name, ExpressionType type) {
      super(cls, name, type);
   }

   public BooleanExpression containsEntry(Map.Entry entry) {
      List<Expression> args = new ArrayList();
      args.add(new Literal(entry));
      Expression invokeExpr = new InvokeExpression(this.queryExpr, "containsEntry", args);
      return new BooleanExpressionImpl(invokeExpr);
   }

   public BooleanExpression containsEntry(javax.jdo.query.Expression expr) {
      List<Expression> args = new ArrayList();
      args.add(((ExpressionImpl)expr).getQueryExpression());
      Expression invokeExpr = new InvokeExpression(this.queryExpr, "containsEntry", args);
      return new BooleanExpressionImpl(invokeExpr);
   }

   public BooleanExpression containsKey(javax.jdo.query.Expression expr) {
      List<Expression> args = new ArrayList();
      args.add(((ExpressionImpl)expr).getQueryExpression());
      Expression invokeExpr = new InvokeExpression(this.queryExpr, "containsKey", args);
      return new BooleanExpressionImpl(invokeExpr);
   }

   public BooleanExpression containsKey(Object key) {
      List<Expression> args = new ArrayList();
      args.add(new Literal(key));
      Expression invokeExpr = new InvokeExpression(this.queryExpr, "containsKey", args);
      return new BooleanExpressionImpl(invokeExpr);
   }

   public BooleanExpression containsValue(javax.jdo.query.Expression expr) {
      List<Expression> args = new ArrayList();
      args.add(((ExpressionImpl)expr).getQueryExpression());
      Expression invokeExpr = new InvokeExpression(this.queryExpr, "containsValue", args);
      return new BooleanExpressionImpl(invokeExpr);
   }

   public BooleanExpression containsValue(Object value) {
      List<Expression> args = new ArrayList();
      args.add(new Literal(value));
      Expression invokeExpr = new InvokeExpression(this.queryExpr, "containsValue", args);
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
