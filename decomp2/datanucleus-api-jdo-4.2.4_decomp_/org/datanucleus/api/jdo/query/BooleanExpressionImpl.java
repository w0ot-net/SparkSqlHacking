package org.datanucleus.api.jdo.query;

import javax.jdo.query.BooleanExpression;
import javax.jdo.query.PersistableExpression;
import org.datanucleus.query.expression.DyadicExpression;
import org.datanucleus.query.expression.Expression;

public class BooleanExpressionImpl extends ComparableExpressionImpl implements BooleanExpression {
   public BooleanExpressionImpl(PersistableExpression parent, String name) {
      super(parent, name);
   }

   public BooleanExpressionImpl(Expression queryExpr) {
      super(queryExpr);
   }

   public BooleanExpressionImpl(Class cls, String name, ExpressionType type) {
      super(cls, name, type);
   }

   public BooleanExpression and(BooleanExpression expr) {
      Expression leftQueryExpr = this.queryExpr;
      Expression rightQueryExpr = ((ExpressionImpl)expr).getQueryExpression();
      Expression queryExpr = new DyadicExpression(leftQueryExpr, Expression.OP_AND, rightQueryExpr);
      return new BooleanExpressionImpl(queryExpr);
   }

   public BooleanExpression not() {
      Expression rightQueryExpr = this.queryExpr;
      Expression queryExpr = new DyadicExpression(Expression.OP_NOT, rightQueryExpr);
      return new BooleanExpressionImpl(queryExpr);
   }

   public BooleanExpression or(BooleanExpression expr) {
      Expression leftQueryExpr = this.queryExpr;
      Expression rightQueryExpr = ((ExpressionImpl)expr).getQueryExpression();
      Expression queryExpr = new DyadicExpression(leftQueryExpr, Expression.OP_OR, rightQueryExpr);
      return new BooleanExpressionImpl(queryExpr);
   }

   public BooleanExpression neg() {
      Expression queryExpr = new DyadicExpression(Expression.OP_NEG, this.queryExpr);
      return new BooleanExpressionImpl(queryExpr);
   }
}
