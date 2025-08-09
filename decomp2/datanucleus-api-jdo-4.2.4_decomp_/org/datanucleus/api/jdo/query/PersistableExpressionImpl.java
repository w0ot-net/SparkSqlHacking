package org.datanucleus.api.jdo.query;

import java.util.ArrayList;
import java.util.List;
import javax.jdo.query.PersistableExpression;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.query.expression.InvokeExpression;

public class PersistableExpressionImpl extends ExpressionImpl implements PersistableExpression {
   public PersistableExpressionImpl(PersistableExpression parent, String name) {
      super(parent, name);
   }

   public PersistableExpressionImpl(Class cls, String name, ExpressionType type) {
      super(cls, name, type);
   }

   public javax.jdo.query.Expression jdoObjectId() {
      List<Expression> args = new ArrayList();
      args.add(this.queryExpr);
      Expression invokeExpr = new InvokeExpression((Expression)null, "JDOHelper.getObjectId", args);
      return new CharacterExpressionImpl(invokeExpr);
   }

   public javax.jdo.query.Expression jdoVersion() {
      List<Expression> args = new ArrayList();
      args.add(this.queryExpr);
      Expression invokeExpr = new InvokeExpression((Expression)null, "JDOHelper.getVersion", args);
      return new CharacterExpressionImpl(invokeExpr);
   }
}
