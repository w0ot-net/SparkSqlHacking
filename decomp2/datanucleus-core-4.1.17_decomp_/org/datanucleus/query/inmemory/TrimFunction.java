package org.datanucleus.query.inmemory;

import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.query.QueryUtils;
import org.datanucleus.query.expression.InvokeExpression;
import org.datanucleus.query.expression.Literal;
import org.datanucleus.query.expression.ParameterExpression;
import org.datanucleus.query.expression.PrimaryExpression;

public class TrimFunction implements InvocationEvaluator {
   public Object evaluate(InvokeExpression expr, Object invokedValue, InMemoryExpressionEvaluator eval) {
      String method = expr.getOperation();
      Object param = expr.getArguments().get(0);
      char trimChar = ' ';
      if (expr.getArguments().size() == 2) {
         trimChar = (Character)((Literal)expr.getArguments().get(1)).getLiteral();
      }

      String paramValue = null;
      if (param instanceof PrimaryExpression) {
         PrimaryExpression primExpr = (PrimaryExpression)param;
         paramValue = (String)eval.getValueForPrimaryExpression(primExpr);
      } else if (param instanceof ParameterExpression) {
         ParameterExpression paramExpr = (ParameterExpression)param;
         paramValue = (String)QueryUtils.getValueForParameterExpression(eval.getParameterValues(), paramExpr);
      } else {
         if (!(param instanceof Literal)) {
            throw new NucleusException(method + "(str1) where str1 is instanceof " + param.getClass().getName() + " not supported");
         }

         paramValue = (String)((Literal)param).getLiteral();
      }

      if (paramValue == null) {
         return null;
      } else if (method.equals("TRIM")) {
         int substringStart = 0;

         for(int i = 0; i < paramValue.length() && paramValue.charAt(i) == trimChar; ++i) {
            ++substringStart;
         }

         int substringEnd = paramValue.length();

         for(int i = paramValue.length() - 1; i >= 0 && paramValue.charAt(i) == trimChar; --i) {
            --substringEnd;
         }

         return paramValue.substring(substringStart, substringEnd);
      } else if (method.equals("TRIM_LEADING")) {
         int substringPos = 0;

         for(int i = 0; i < paramValue.length() && paramValue.charAt(i) == trimChar; ++i) {
            ++substringPos;
         }

         return paramValue.substring(substringPos);
      } else if (!method.equals("TRIM_TRAILING")) {
         return null;
      } else {
         int substringPos = paramValue.length();

         for(int i = paramValue.length() - 1; i >= 0 && paramValue.charAt(i) == trimChar; --i) {
            --substringPos;
         }

         return paramValue.substring(0, substringPos);
      }
   }
}
