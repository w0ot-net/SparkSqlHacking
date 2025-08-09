package org.datanucleus.query.inmemory;

import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.query.QueryUtils;
import org.datanucleus.query.expression.InvokeExpression;
import org.datanucleus.query.expression.Literal;
import org.datanucleus.query.expression.ParameterExpression;
import org.datanucleus.query.expression.PrimaryExpression;

public class ModFunction implements InvocationEvaluator {
   public Object evaluate(InvokeExpression expr, Object invokedValue, InMemoryExpressionEvaluator eval) {
      String method = expr.getOperation();
      Object param1 = expr.getArguments().get(0);
      int param1Value = -1;
      if (param1 instanceof PrimaryExpression) {
         PrimaryExpression primExpr = (PrimaryExpression)param1;
         Object val = eval.getValueForPrimaryExpression(primExpr);
         if (!(val instanceof Number)) {
            throw new NucleusException(method + "(num1, num2) where num1 is instanceof " + param1.getClass().getName() + " but should be integer");
         }

         param1Value = ((Number)val).intValue();
      } else if (param1 instanceof ParameterExpression) {
         ParameterExpression paramExpr = (ParameterExpression)param1;
         Object val = QueryUtils.getValueForParameterExpression(eval.getParameterValues(), paramExpr);
         if (!(val instanceof Number)) {
            throw new NucleusException(method + "(num1, num2) where num1 is instanceof " + param1.getClass().getName() + " but should be integer");
         }

         param1Value = ((Number)val).intValue();
      } else {
         if (!(param1 instanceof Literal)) {
            throw new NucleusException(method + "(num1, num2) where num1 is instanceof " + param1.getClass().getName() + " not supported");
         }

         Object val = ((Literal)param1).getLiteral();
         if (!(val instanceof Number)) {
            throw new NucleusException(method + "(num1, num2) where num1 is instanceof " + param1.getClass().getName() + " but should be integer");
         }

         param1Value = ((Number)val).intValue();
      }

      Object param2 = expr.getArguments().get(1);
      int param2Value = -1;
      if (param2 instanceof PrimaryExpression) {
         PrimaryExpression primExpr = (PrimaryExpression)param2;
         Object val = eval.getValueForPrimaryExpression(primExpr);
         if (!(val instanceof Number)) {
            throw new NucleusException(method + "(num1, num2) where num2 is instanceof " + param2.getClass().getName() + " but should be integer");
         }

         param2Value = ((Number)val).intValue();
      } else if (param2 instanceof ParameterExpression) {
         ParameterExpression paramExpr = (ParameterExpression)param2;
         Object val = QueryUtils.getValueForParameterExpression(eval.getParameterValues(), paramExpr);
         if (!(val instanceof Number)) {
            throw new NucleusException(method + "(num1, num2) where num1 is instanceof " + param2.getClass().getName() + " but should be integer");
         }

         param2Value = ((Number)val).intValue();
      } else {
         if (!(param2 instanceof Literal)) {
            throw new NucleusException(method + "(num1, num2) where num2 is instanceof " + param2.getClass().getName() + " not supported");
         }

         Object val = ((Literal)param2).getLiteral();
         if (!(val instanceof Number)) {
            throw new NucleusException(method + "(num1, num2) where num2 is instanceof " + param2.getClass().getName() + " but should be integer");
         }

         param2Value = ((Number)val).intValue();
      }

      return param1Value % param2Value;
   }
}
