package org.datanucleus.api.jdo.query;

import javax.jdo.query.ByteExpression;
import javax.jdo.query.PersistableExpression;
import org.datanucleus.query.expression.Expression;

public class ByteExpressionImpl extends ComparableExpressionImpl implements ByteExpression {
   public ByteExpressionImpl(PersistableExpression parent, String name) {
      super(parent, name);
   }

   public ByteExpressionImpl(Class cls, String name, ExpressionType type) {
      super(cls, name, type);
   }

   public ByteExpressionImpl(Expression queryExpr) {
      super(queryExpr);
   }
}
