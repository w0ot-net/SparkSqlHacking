package org.datanucleus.query.inmemory;

import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.query.QueryUtils;
import org.datanucleus.query.expression.InvokeExpression;
import org.datanucleus.query.expression.Literal;
import org.datanucleus.query.expression.ParameterExpression;
import org.datanucleus.query.expression.PrimaryExpression;

public class ConcatFunction implements InvocationEvaluator {
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

      Object param2 = expr.getArguments().get(1);
      Object param2Value = null;
      if (param2 instanceof PrimaryExpression) {
         PrimaryExpression primExpr = (PrimaryExpression)param;
         param2Value = eval.getValueForPrimaryExpression(primExpr);
      } else if (param2 instanceof ParameterExpression) {
         ParameterExpression param2Expr = (ParameterExpression)param2;
         param2Value = QueryUtils.getValueForParameterExpression(eval.getParameterValues(), param2Expr);
      } else {
         if (!(param2 instanceof Literal)) {
            throw new NucleusException(method + "(param, param2) where param2 is instanceof " + param2.getClass().getName() + " not supported");
         }

         param2Value = ((Literal)param).getLiteral();
      }

      return paramValue == null ? null : ((String)paramValue).concat((String)param2Value);
   }
}
