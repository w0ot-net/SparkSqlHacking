package org.datanucleus.query.inmemory;

import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.query.QueryUtils;
import org.datanucleus.query.expression.InvokeExpression;
import org.datanucleus.query.expression.Literal;
import org.datanucleus.query.expression.ParameterExpression;
import org.datanucleus.query.expression.PrimaryExpression;
import org.datanucleus.util.Localiser;

public class LowerFunction implements InvocationEvaluator {
   public Object evaluate(InvokeExpression expr, Object invokedValue, InMemoryExpressionEvaluator eval) {
      String method = expr.getOperation();
      Object param = expr.getArguments().get(0);
      Object paramValue = null;
      if (param instanceof PrimaryExpression) {
         PrimaryExpression primExpr = (PrimaryExpression)param;
         paramValue = eval.getValueForPrimaryExpression(primExpr);
      } else if (param instanceof ParameterExpression) {
         ParameterExpression paramExpr = (ParameterExpression)param;
         paramValue = QueryUtils.getValueForParameterExpression(eval.getParameterValues(), paramExpr);
      } else {
         if (!(param instanceof Literal)) {
            throw new NucleusException(method + "(param) where param is instanceof " + param.getClass().getName() + " not supported");
         }

         paramValue = ((Literal)param).getLiteral();
      }

      if (paramValue == null) {
         return null;
      } else if (!(paramValue instanceof String)) {
         throw new NucleusException(Localiser.msg("021011", method, paramValue.getClass().getName()));
      } else {
         return ((String)paramValue).toLowerCase();
      }
   }
}
