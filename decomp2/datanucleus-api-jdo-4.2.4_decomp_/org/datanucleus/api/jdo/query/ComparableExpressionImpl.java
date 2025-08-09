package org.datanucleus.api.jdo.query;

import java.util.ArrayList;
import java.util.List;
import javax.jdo.query.BooleanExpression;
import javax.jdo.query.ComparableExpression;
import javax.jdo.query.NumericExpression;
import javax.jdo.query.OrderExpression;
import javax.jdo.query.PersistableExpression;
import javax.jdo.query.OrderExpression.OrderDirection;
import org.datanucleus.query.expression.DyadicExpression;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.query.expression.InvokeExpression;
import org.datanucleus.query.expression.Literal;

public class ComparableExpressionImpl extends ExpressionImpl implements ComparableExpression {
   public ComparableExpressionImpl(PersistableExpression parent, String name) {
      super(parent, name);
   }

   public ComparableExpressionImpl(Expression queryExpr) {
      super(queryExpr);
   }

   public ComparableExpressionImpl(Class cls, String name, ExpressionType type) {
      super(cls, name, type);
   }

   public BooleanExpression gt(ComparableExpression expr) {
      Expression leftQueryExpr = this.queryExpr;
      Expression rightQueryExpr = ((ExpressionImpl)expr).getQueryExpression();
      Expression queryExpr = new DyadicExpression(leftQueryExpr, Expression.OP_GT, rightQueryExpr);
      return new BooleanExpressionImpl(queryExpr);
   }

   public BooleanExpression gt(Object t) {
      Expression leftQueryExpr = this.queryExpr;
      Expression rightQueryExpr = new Literal(t);
      Expression queryExpr = new DyadicExpression(leftQueryExpr, Expression.OP_GT, rightQueryExpr);
      return new BooleanExpressionImpl(queryExpr);
   }

   public BooleanExpression gteq(ComparableExpression expr) {
      Expression leftQueryExpr = this.queryExpr;
      Expression rightQueryExpr = ((ExpressionImpl)expr).getQueryExpression();
      Expression queryExpr = new DyadicExpression(leftQueryExpr, Expression.OP_GTEQ, rightQueryExpr);
      return new BooleanExpressionImpl(queryExpr);
   }

   public BooleanExpression gteq(Object t) {
      Expression leftQueryExpr = this.queryExpr;
      Expression rightQueryExpr = new Literal(t);
      Expression queryExpr = new DyadicExpression(leftQueryExpr, Expression.OP_GTEQ, rightQueryExpr);
      return new BooleanExpressionImpl(queryExpr);
   }

   public BooleanExpression lt(ComparableExpression expr) {
      Expression leftQueryExpr = this.queryExpr;
      Expression rightQueryExpr = ((ExpressionImpl)expr).getQueryExpression();
      Expression queryExpr = new DyadicExpression(leftQueryExpr, Expression.OP_LT, rightQueryExpr);
      return new BooleanExpressionImpl(queryExpr);
   }

   public BooleanExpression lt(Object t) {
      Expression leftQueryExpr = this.queryExpr;
      Expression rightQueryExpr = new Literal(t);
      Expression queryExpr = new DyadicExpression(leftQueryExpr, Expression.OP_LT, rightQueryExpr);
      return new BooleanExpressionImpl(queryExpr);
   }

   public BooleanExpression lteq(ComparableExpression expr) {
      Expression leftQueryExpr = this.queryExpr;
      Expression rightQueryExpr = ((ExpressionImpl)expr).getQueryExpression();
      Expression queryExpr = new DyadicExpression(leftQueryExpr, Expression.OP_LTEQ, rightQueryExpr);
      return new BooleanExpressionImpl(queryExpr);
   }

   public BooleanExpression lteq(Object t) {
      Expression leftQueryExpr = this.queryExpr;
      Expression rightQueryExpr = new Literal(t);
      Expression queryExpr = new DyadicExpression(leftQueryExpr, Expression.OP_LTEQ, rightQueryExpr);
      return new BooleanExpressionImpl(queryExpr);
   }

   public NumericExpression max() {
      List<Expression> args = new ArrayList();
      args.add(this.queryExpr);
      Expression invokeExpr = new InvokeExpression((Expression)null, "max", args);
      return new NumericExpressionImpl(invokeExpr);
   }

   public NumericExpression min() {
      List<Expression> args = new ArrayList();
      args.add(this.queryExpr);
      Expression invokeExpr = new InvokeExpression((Expression)null, "min", args);
      return new NumericExpressionImpl(invokeExpr);
   }

   public OrderExpression asc() {
      return new OrderExpressionImpl(this, OrderDirection.ASC);
   }

   public OrderExpression desc() {
      return new OrderExpressionImpl(this, OrderDirection.DESC);
   }
}
