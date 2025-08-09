package org.datanucleus.api.jdo.query;

import javax.jdo.query.ObjectExpression;
import javax.jdo.query.PersistableExpression;
import org.datanucleus.query.expression.Expression;

public class ObjectExpressionImpl extends ExpressionImpl implements ObjectExpression {
   public ObjectExpressionImpl(PersistableExpression parent, String name) {
      super(parent, name);
   }

   public ObjectExpressionImpl(Class cls, String name, ExpressionType type) {
      super(cls, name, type);
   }

   public ObjectExpressionImpl(Expression queryExpr) {
      super(queryExpr);
   }
}
