package org.datanucleus.query.inmemory;

import org.datanucleus.query.QueryUtils;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.query.expression.InvokeExpression;
import org.datanucleus.query.expression.ParameterExpression;
import org.datanucleus.query.expression.PrimaryExpression;

public class ObjectGetClassMethod implements InvocationEvaluator {
   public Object evaluate(InvokeExpression expr, Object invokedValue, InMemoryExpressionEvaluator eval) {
      Expression argExpr = (Expression)expr.getArguments().get(0);
      if (argExpr instanceof ParameterExpression) {
         ParameterExpression paramExpr = (ParameterExpression)argExpr;
         Object value = QueryUtils.getValueForParameterExpression(eval.getParameterValues(), paramExpr);
         return value.getClass();
      } else {
         return eval.getValueForPrimaryExpression((PrimaryExpression)argExpr).getClass();
      }
   }
}
