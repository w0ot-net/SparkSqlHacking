package org.datanucleus.query.inmemory;

import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.query.QueryUtils;
import org.datanucleus.query.expression.InvokeExpression;
import org.datanucleus.query.expression.Literal;
import org.datanucleus.query.expression.ParameterExpression;
import org.datanucleus.query.expression.PrimaryExpression;

public class LocateFunction implements InvocationEvaluator {
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
            throw new NucleusException(method + "(str1, str2, pos) where str1 is instanceof " + param.getClass().getName() + " not supported");
         }

         paramValue = ((Literal)param).getLiteral();
      }

      if (paramValue == null) {
         return -1;
      } else {
         Object locStr = expr.getArguments().get(1);
         String locStrValue = null;
         if (locStr instanceof Literal) {
            locStrValue = (String)((Literal)locStr).getLiteral();
            if (expr.getArguments().size() == 3) {
               Object pos = expr.getArguments().get(2);
               int num2Value = -1;
               if (pos instanceof Literal) {
                  num2Value = eval.getIntegerForLiteral((Literal)pos);
                  return ((String)paramValue).indexOf(locStrValue, num2Value);
               } else {
                  throw new NucleusException(method + "(str, str2, pos) where pos is instanceof " + pos.getClass().getName() + " not supported");
               }
            } else {
               return ((String)paramValue).indexOf(locStrValue);
            }
         } else {
            throw new NucleusException(method + "(str, str2, pos) where str2 is instanceof " + locStr.getClass().getName() + " not supported");
         }
      }
   }
}
