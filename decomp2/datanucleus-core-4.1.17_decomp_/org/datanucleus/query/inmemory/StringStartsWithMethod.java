package org.datanucleus.query.inmemory;

import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.query.QueryUtils;
import org.datanucleus.query.expression.InvokeExpression;
import org.datanucleus.query.expression.Literal;
import org.datanucleus.query.expression.ParameterExpression;
import org.datanucleus.query.expression.PrimaryExpression;
import org.datanucleus.util.Localiser;

public class StringStartsWithMethod implements InvocationEvaluator {
   public Object evaluate(InvokeExpression expr, Object invokedValue, InMemoryExpressionEvaluator eval) {
      String method = expr.getOperation();
      if (invokedValue == null) {
         return Boolean.FALSE;
      } else if (!(invokedValue instanceof String)) {
         throw new NucleusException(Localiser.msg("021011", method, invokedValue.getClass().getName()));
      } else {
         String arg1 = null;
         Object argObj = null;
         Object param = expr.getArguments().get(0);
         if (param instanceof PrimaryExpression) {
            PrimaryExpression primExpr = (PrimaryExpression)param;
            argObj = eval.getValueForPrimaryExpression(primExpr);
         } else if (param instanceof ParameterExpression) {
            ParameterExpression paramExpr = (ParameterExpression)param;
            argObj = QueryUtils.getValueForParameterExpression(eval.getParameterValues(), paramExpr);
         } else if (param instanceof Literal) {
            argObj = ((Literal)param).getLiteral();
         } else {
            if (!(param instanceof InvokeExpression)) {
               throw new NucleusException(method + "(param) where param is instanceof " + param.getClass().getName() + " not supported");
            }

            argObj = eval.getValueForInvokeExpression((InvokeExpression)param);
         }

         arg1 = QueryUtils.getStringValue(argObj);
         Boolean result = null;
         if (expr.getArguments().size() == 2) {
            Literal param2 = (Literal)expr.getArguments().get(1);
            int arg2 = -1;
            if (param2.getLiteral() instanceof Number) {
               arg2 = ((Number)param2.getLiteral()).intValue();
            }

            result = ((String)invokedValue).startsWith(arg1, arg2) ? Boolean.TRUE : Boolean.FALSE;
         } else {
            result = ((String)invokedValue).startsWith(arg1) ? Boolean.TRUE : Boolean.FALSE;
         }

         return result;
      }
   }
}
