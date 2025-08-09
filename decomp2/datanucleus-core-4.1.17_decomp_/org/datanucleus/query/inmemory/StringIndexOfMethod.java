package org.datanucleus.query.inmemory;

import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.query.QueryUtils;
import org.datanucleus.query.expression.DyadicExpression;
import org.datanucleus.query.expression.InvokeExpression;
import org.datanucleus.query.expression.Literal;
import org.datanucleus.query.expression.ParameterExpression;
import org.datanucleus.query.expression.PrimaryExpression;
import org.datanucleus.util.Localiser;

public class StringIndexOfMethod implements InvocationEvaluator {
   public Object evaluate(InvokeExpression expr, Object invokedValue, InMemoryExpressionEvaluator eval) {
      String method = expr.getOperation();
      if (invokedValue == null) {
         return -1;
      } else if (!(invokedValue instanceof String)) {
         throw new NucleusException(Localiser.msg("021011", method, invokedValue.getClass().getName()));
      } else {
         String arg1 = null;
         Object arg1Obj = null;
         Object param = expr.getArguments().get(0);
         if (param instanceof PrimaryExpression) {
            PrimaryExpression primExpr = (PrimaryExpression)param;
            arg1Obj = eval.getValueForPrimaryExpression(primExpr);
         } else if (param instanceof ParameterExpression) {
            ParameterExpression paramExpr = (ParameterExpression)param;
            arg1Obj = QueryUtils.getValueForParameterExpression(eval.getParameterValues(), paramExpr);
         } else if (param instanceof Literal) {
            arg1Obj = ((Literal)param).getLiteral();
         } else {
            if (!(param instanceof InvokeExpression)) {
               throw new NucleusException(method + "(param[, num1]) where param is instanceof " + param.getClass().getName() + " not supported");
            }

            arg1Obj = eval.getValueForInvokeExpression((InvokeExpression)param);
         }

         arg1 = QueryUtils.getStringValue(arg1Obj);
         Integer result = null;
         if (expr.getArguments().size() == 2) {
            int arg2 = -1;
            param = expr.getArguments().get(1);
            Object arg2Obj = null;
            if (param instanceof PrimaryExpression) {
               PrimaryExpression primExpr = (PrimaryExpression)param;
               arg2Obj = eval.getValueForPrimaryExpression(primExpr);
            } else if (param instanceof ParameterExpression) {
               ParameterExpression paramExpr = (ParameterExpression)param;
               arg2Obj = QueryUtils.getValueForParameterExpression(eval.getParameterValues(), paramExpr);
            } else if (param instanceof Literal) {
               arg2Obj = ((Literal)param).getLiteral();
            } else {
               if (!(param instanceof DyadicExpression)) {
                  throw new NucleusException(method + "(param1, param2) where param2 is instanceof " + param.getClass().getName() + " not supported");
               }

               arg2Obj = ((DyadicExpression)param).evaluate(eval);
            }

            if (!(arg2Obj instanceof Number)) {
               throw new NucleusException(method + "(param1,param2) : param2 must be numeric");
            }

            arg2 = ((Number)arg2Obj).intValue();
            result = ((String)invokedValue).indexOf(arg1, arg2);
         } else {
            result = ((String)invokedValue).indexOf(arg1);
         }

         return result;
      }
   }
}
