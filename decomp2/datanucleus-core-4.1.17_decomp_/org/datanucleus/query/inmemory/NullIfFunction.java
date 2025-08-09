package org.datanucleus.query.inmemory;

import java.util.List;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.query.QueryUtils;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.query.expression.InvokeExpression;
import org.datanucleus.query.expression.Literal;
import org.datanucleus.query.expression.ParameterExpression;
import org.datanucleus.query.expression.PrimaryExpression;

public class NullIfFunction implements InvocationEvaluator {
   public Object evaluate(InvokeExpression expr, Object ignored, InMemoryExpressionEvaluator eval) {
      List<Expression> args = expr.getArguments();
      if (args != null && !args.isEmpty()) {
         if (args.size() == 1) {
            return this.getValueForArgExpression((Expression)args.get(0), eval);
         } else {
            Expression argExpr1 = (Expression)args.get(0);
            Expression argExpr2 = (Expression)args.get(1);
            Object argValue1 = this.getValueForArgExpression(argExpr1, eval);
            Object argValue2 = this.getValueForArgExpression(argExpr2, eval);
            return argValue1 == argValue2 ? null : argValue1;
         }
      } else {
         throw new NucleusException("NULLIF requires two arguments");
      }
   }

   protected Object getValueForArgExpression(Expression argExpr, InMemoryExpressionEvaluator eval) {
      Object argValue = null;
      if (argExpr instanceof PrimaryExpression) {
         PrimaryExpression primExpr = (PrimaryExpression)argExpr;
         argValue = eval.getValueForPrimaryExpression(primExpr);
      } else if (argExpr instanceof ParameterExpression) {
         ParameterExpression paramExpr = (ParameterExpression)argExpr;
         argValue = QueryUtils.getValueForParameterExpression(eval.getParameterValues(), paramExpr);
      } else {
         if (!(argExpr instanceof Literal)) {
            throw new NucleusException("Don't support NULLIF with argument of type " + argExpr.getClass().getName());
         }

         argValue = ((Literal)argExpr).getLiteral();
      }

      return argValue;
   }
}
