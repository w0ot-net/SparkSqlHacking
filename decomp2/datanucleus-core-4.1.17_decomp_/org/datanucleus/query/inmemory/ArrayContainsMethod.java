package org.datanucleus.query.inmemory;

import java.lang.reflect.Array;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.query.QueryUtils;
import org.datanucleus.query.expression.InvokeExpression;
import org.datanucleus.query.expression.Literal;
import org.datanucleus.query.expression.ParameterExpression;
import org.datanucleus.query.expression.PrimaryExpression;
import org.datanucleus.query.expression.VariableExpression;
import org.datanucleus.util.Localiser;

public class ArrayContainsMethod implements InvocationEvaluator {
   public Object evaluate(InvokeExpression expr, Object invokedValue, InMemoryExpressionEvaluator eval) {
      String method = expr.getOperation();
      if (invokedValue == null) {
         return Boolean.FALSE;
      } else if (!invokedValue.getClass().isArray()) {
         throw new NucleusException(Localiser.msg("021011", method, invokedValue.getClass().getName()));
      } else {
         Object param = expr.getArguments().get(0);
         Object paramValue = null;
         if (param instanceof Literal) {
            paramValue = ((Literal)param).getLiteral();
         } else if (param instanceof PrimaryExpression) {
            PrimaryExpression primExpr = (PrimaryExpression)param;
            paramValue = eval.getValueForPrimaryExpression(primExpr);
         } else if (param instanceof ParameterExpression) {
            ParameterExpression paramExpr = (ParameterExpression)param;
            paramValue = QueryUtils.getValueForParameterExpression(eval.getParameterValues(), paramExpr);
         } else {
            if (!(param instanceof VariableExpression)) {
               throw new NucleusException("Dont currently support use of Array.contains(" + param.getClass().getName() + ")");
            }

            VariableExpression varExpr = (VariableExpression)param;

            try {
               paramValue = eval.getValueForVariableExpression(varExpr);
            } catch (VariableNotSetException var13) {
               throw new VariableNotSetException(varExpr, invokedValue);
            }
         }

         for(int i = 0; i < Array.getLength(invokedValue); ++i) {
            Object elem = Array.get(invokedValue, i);
            if (elem == null && paramValue == null) {
               return Boolean.TRUE;
            }

            if (elem != null && paramValue != null) {
               if (elem.equals(paramValue)) {
                  return Boolean.TRUE;
               }

               if (!paramValue.getClass().isAssignableFrom(elem.getClass()) && !elem.getClass().isAssignableFrom(paramValue.getClass()) && (paramValue.getClass() == Long.class || paramValue.getClass() == Integer.class || paramValue.getClass() == Short.class) && (elem.getClass() == Long.class || elem.getClass() == Integer.class || elem.getClass() == Short.class)) {
                  long paramLong = ((Number)paramValue).longValue();
                  long elemLong = ((Number)elem).longValue();
                  if (paramLong == elemLong) {
                     return Boolean.TRUE;
                  }
               }
            }
         }

         return Boolean.FALSE;
      }
   }
}
