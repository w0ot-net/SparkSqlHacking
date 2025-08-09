package org.datanucleus.query.inmemory;

import java.util.Iterator;
import java.util.List;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.query.QueryUtils;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.query.expression.InvokeExpression;
import org.datanucleus.query.expression.Literal;
import org.datanucleus.query.expression.ParameterExpression;
import org.datanucleus.query.expression.PrimaryExpression;

public class CoalesceFunction implements InvocationEvaluator {
   public Object evaluate(InvokeExpression expr, Object ignored, InMemoryExpressionEvaluator eval) {
      List<Expression> args = expr.getArguments();
      if (args != null && !args.isEmpty()) {
         Iterator<Expression> iter = args.iterator();
         Object argValue = null;

         while(iter.hasNext()) {
            Expression argExpr = (Expression)iter.next();
            argValue = this.getValueForArgExpression(argExpr, eval);
            if (argValue != null) {
               return argValue;
            }
         }

         return null;
      } else {
         return null;
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
            throw new NucleusException("Don't support COALESCE with argument of type " + argExpr.getClass().getName());
         }

         argValue = ((Literal)argExpr).getLiteral();
      }

      return argValue;
   }
}
