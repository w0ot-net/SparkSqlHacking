package org.datanucleus.query.inmemory;

import java.util.List;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.query.QueryUtils;
import org.datanucleus.query.expression.InvokeExpression;
import org.datanucleus.query.expression.Literal;
import org.datanucleus.query.expression.ParameterExpression;
import org.datanucleus.query.expression.PrimaryExpression;
import org.datanucleus.query.expression.VariableExpression;
import org.datanucleus.util.Localiser;

public class ListGetMethod implements InvocationEvaluator {
   public Object evaluate(InvokeExpression expr, Object invokedValue, InMemoryExpressionEvaluator eval) {
      String method = expr.getOperation();
      if (invokedValue == null) {
         return null;
      } else if (!(invokedValue instanceof List)) {
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
               throw new NucleusException("Dont currently support use of get(" + param.getClass().getName() + ")");
            }

            VariableExpression varExpr = (VariableExpression)param;

            try {
               paramValue = eval.getValueForVariableExpression(varExpr);
            } catch (VariableNotSetException var9) {
               throw new VariableNotSetException(varExpr, ((List)invokedValue).toArray());
            }
         }

         if (paramValue instanceof Number) {
            int paramInt = ((Number)paramValue).intValue();
            return ((List)invokedValue).get(paramInt);
         } else {
            throw new NucleusException("List.get() should take in an integer but is " + paramValue);
         }
      }
   }
}
