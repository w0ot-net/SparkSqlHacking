package org.datanucleus.api.jdo.query;

import java.util.ArrayList;
import java.util.List;
import javax.jdo.query.Expression;
import javax.jdo.query.ListExpression;
import javax.jdo.query.NumericExpression;
import javax.jdo.query.PersistableExpression;
import org.datanucleus.query.expression.InvokeExpression;
import org.datanucleus.query.expression.Literal;

public class ListExpressionImpl extends CollectionExpressionImpl implements ListExpression {
   public ListExpressionImpl(PersistableExpression parent, String name) {
      super(parent, name);
   }

   public ListExpressionImpl(Class cls, String name, ExpressionType type) {
      super(cls, name, type);
   }

   public Expression get(int pos) {
      List args = new ArrayList();
      args.add(new Literal(pos));
      org.datanucleus.query.expression.Expression invokeExpr = new InvokeExpression(this.queryExpr, "get", args);
      return new ExpressionImpl(invokeExpr);
   }

   public Expression get(NumericExpression posExpr) {
      List args = new ArrayList();
      args.add(((ExpressionImpl)posExpr).getQueryExpression());
      org.datanucleus.query.expression.Expression invokeExpr = new InvokeExpression(this.queryExpr, "get", args);
      return new ExpressionImpl(invokeExpr);
   }
}
