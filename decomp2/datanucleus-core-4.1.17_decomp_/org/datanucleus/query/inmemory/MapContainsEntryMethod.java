package org.datanucleus.query.inmemory;

import java.util.Map;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.query.QueryUtils;
import org.datanucleus.query.expression.InvokeExpression;
import org.datanucleus.query.expression.Literal;
import org.datanucleus.query.expression.ParameterExpression;
import org.datanucleus.query.expression.PrimaryExpression;
import org.datanucleus.query.expression.VariableExpression;
import org.datanucleus.util.Localiser;

public class MapContainsEntryMethod implements InvocationEvaluator {
   public Object evaluate(InvokeExpression expr, Object invokedValue, InMemoryExpressionEvaluator eval) {
      String method = expr.getOperation();
      if (invokedValue == null) {
         return Boolean.FALSE;
      } else if (!(invokedValue instanceof Map)) {
         throw new NucleusException(Localiser.msg("021011", method, invokedValue.getClass().getName()));
      } else {
         Object keyParam = expr.getArguments().get(0);
         Object valParam = expr.getArguments().get(1);
         Object keyValue = null;
         if (keyParam instanceof Literal) {
            keyValue = ((Literal)keyParam).getLiteral();
         } else if (keyParam instanceof PrimaryExpression) {
            PrimaryExpression primExpr = (PrimaryExpression)keyParam;
            keyValue = eval.getValueForPrimaryExpression(primExpr);
         } else if (keyParam instanceof ParameterExpression) {
            ParameterExpression paramExpr = (ParameterExpression)keyParam;
            keyValue = QueryUtils.getValueForParameterExpression(eval.getParameterValues(), paramExpr);
         } else {
            if (!(keyParam instanceof VariableExpression)) {
               throw new NucleusException("Dont currently support use of containsEntry(" + keyParam.getClass().getName() + ",?)");
            }

            VariableExpression varExpr = (VariableExpression)keyParam;

            try {
               keyValue = eval.getValueForVariableExpression(varExpr);
            } catch (VariableNotSetException var12) {
               throw new VariableNotSetException(varExpr, ((Map)invokedValue).values().toArray());
            }
         }

         Object valValue = null;
         if (valParam instanceof Literal) {
            valValue = ((Literal)valParam).getLiteral();
         } else if (keyParam instanceof PrimaryExpression) {
            PrimaryExpression primExpr = (PrimaryExpression)valParam;
            valValue = eval.getValueForPrimaryExpression(primExpr);
         } else if (keyParam instanceof ParameterExpression) {
            ParameterExpression paramExpr = (ParameterExpression)valParam;
            valValue = QueryUtils.getValueForParameterExpression(eval.getParameterValues(), paramExpr);
         } else {
            if (!(keyParam instanceof VariableExpression)) {
               throw new NucleusException("Dont currently support use of containsEntry(?," + valParam.getClass().getName() + ")");
            }

            VariableExpression varExpr = (VariableExpression)valParam;

            try {
               valValue = eval.getValueForVariableExpression(varExpr);
            } catch (VariableNotSetException var11) {
               throw new VariableNotSetException(varExpr, ((Map)invokedValue).values().toArray());
            }
         }

         Map invokedMap = (Map)invokedValue;
         if (invokedMap.containsKey(keyValue)) {
            Object currentValForKey = invokedMap.get(keyValue);
            if (currentValForKey.equals(valValue)) {
               return Boolean.TRUE;
            }
         }

         return Boolean.FALSE;
      }
   }
}
