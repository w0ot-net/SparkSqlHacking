package org.datanucleus.api.jdo.query;

import javax.jdo.query.Expression;
import javax.jdo.query.OrderExpression;

public class OrderExpressionImpl implements OrderExpression {
   protected Expression orderExpr;
   protected OrderExpression.OrderDirection direction;

   public OrderExpressionImpl(Expression expr, OrderExpression.OrderDirection dir) {
      this.orderExpr = expr;
      this.direction = dir;
   }

   public OrderExpression.OrderDirection getDirection() {
      return this.direction;
   }

   public Expression getExpression() {
      return this.orderExpr;
   }
}
