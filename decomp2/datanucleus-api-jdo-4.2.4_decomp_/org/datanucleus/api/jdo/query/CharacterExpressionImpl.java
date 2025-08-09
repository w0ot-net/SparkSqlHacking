package org.datanucleus.api.jdo.query;

import java.util.List;
import javax.jdo.query.CharacterExpression;
import javax.jdo.query.PersistableExpression;
import org.datanucleus.query.expression.DyadicExpression;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.query.expression.InvokeExpression;

public class CharacterExpressionImpl extends ComparableExpressionImpl implements CharacterExpression {
   public CharacterExpressionImpl(PersistableExpression parent, String name) {
      super(parent, name);
   }

   public CharacterExpressionImpl(Class cls, String name, ExpressionType type) {
      super(cls, name, type);
   }

   public CharacterExpressionImpl(Expression queryExpr) {
      super(queryExpr);
   }

   public CharacterExpression toLowerCase() {
      Expression invokeExpr = new InvokeExpression(this.queryExpr, "toLowerCase", (List)null);
      return new CharacterExpressionImpl(invokeExpr);
   }

   public CharacterExpression toUpperCase() {
      Expression invokeExpr = new InvokeExpression(this.queryExpr, "toUpperCase", (List)null);
      return new CharacterExpressionImpl(invokeExpr);
   }

   public CharacterExpression neg() {
      Expression queryExpr = new DyadicExpression(Expression.OP_NEG, this.queryExpr);
      return new CharacterExpressionImpl(queryExpr);
   }

   public CharacterExpression com() {
      Expression queryExpr = new DyadicExpression(Expression.OP_COM, this.queryExpr);
      return new CharacterExpressionImpl(queryExpr);
   }
}
