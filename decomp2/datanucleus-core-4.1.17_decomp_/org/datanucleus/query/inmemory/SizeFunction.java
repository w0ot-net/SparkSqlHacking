package org.datanucleus.query.inmemory;

import java.util.Collection;
import java.util.Map;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.query.QueryUtils;
import org.datanucleus.query.expression.InvokeExpression;
import org.datanucleus.query.expression.Literal;
import org.datanucleus.query.expression.ParameterExpression;
import org.datanucleus.query.expression.PrimaryExpression;

public class SizeFunction implements InvocationEvaluator {
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
      } else if (paramValue instanceof Collection) {
         return ((Collection)paramValue).size();
      } else {
         return paramValue instanceof Map ? ((Map)paramValue).size() : null;
      }
   }
}
