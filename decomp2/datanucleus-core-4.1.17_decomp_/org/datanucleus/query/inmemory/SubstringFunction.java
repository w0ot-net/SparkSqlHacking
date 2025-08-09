package org.datanucleus.query.inmemory;

import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.query.QueryUtils;
import org.datanucleus.query.expression.InvokeExpression;
import org.datanucleus.query.expression.Literal;
import org.datanucleus.query.expression.ParameterExpression;
import org.datanucleus.query.expression.PrimaryExpression;

public class SubstringFunction implements InvocationEvaluator {
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
            throw new NucleusException(method + "(param, num1, num2) where param is instanceof " + param.getClass().getName() + " not supported");
         }

         paramValue = ((Literal)param).getLiteral();
      }

      if (paramValue == null) {
         return null;
      } else {
         Object num1 = expr.getArguments().get(1);
         int num1Value = -1;
         if (num1 instanceof Literal) {
            num1Value = eval.getIntegerForLiteral((Literal)num1);
            Object num2 = expr.getArguments().get(2);
            int num2Value = -1;
            if (num2 instanceof Literal) {
               num2Value = eval.getIntegerForLiteral((Literal)num2);
               return ((String)paramValue).substring(num1Value, num2Value);
            } else {
               throw new NucleusException(method + "(param, num1, num2) where num2 is instanceof " + num2.getClass().getName() + " not supported");
            }
         } else {
            throw new NucleusException(method + "(param, num1, num2) where num1 is instanceof " + num1.getClass().getName() + " not supported");
         }
      }
   }
}
