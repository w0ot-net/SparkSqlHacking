package org.datanucleus.query.inmemory;

import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.query.QueryUtils;
import org.datanucleus.query.expression.DyadicExpression;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.query.expression.InvokeExpression;
import org.datanucleus.query.expression.Literal;
import org.datanucleus.query.expression.ParameterExpression;
import org.datanucleus.query.expression.PrimaryExpression;
import org.datanucleus.util.Localiser;

public class StringSubstringMethod implements InvocationEvaluator {
   public Object evaluate(InvokeExpression expr, Object invokedValue, InMemoryExpressionEvaluator eval) {
      String method = expr.getOperation();
      if (invokedValue == null) {
         return null;
      } else if (!(invokedValue instanceof String)) {
         throw new NucleusException(Localiser.msg("021011", method, invokedValue.getClass().getName()));
      } else {
         int arg0 = -1;
         Expression arg0Expr = (Expression)expr.getArguments().get(0);
         Object arg0Val = null;
         if (arg0Expr instanceof PrimaryExpression) {
            arg0Val = eval.getValueForPrimaryExpression((PrimaryExpression)arg0Expr);
         } else if (arg0Expr instanceof ParameterExpression) {
            arg0Val = QueryUtils.getValueForParameterExpression(eval.getParameterValues(), (ParameterExpression)arg0Expr);
         } else if (arg0Expr instanceof Literal) {
            arg0Val = ((Literal)arg0Expr).getLiteral();
         } else if (arg0Expr instanceof DyadicExpression) {
            arg0Val = ((DyadicExpression)arg0Expr).evaluate(eval);
         }

         if (!(arg0Val instanceof Number)) {
            throw new NucleusException(method + "(param1[,param2]) : param1 must be numeric");
         } else {
            arg0 = ((Number)arg0Val).intValue();
            String result = null;
            if (expr.getArguments().size() == 2) {
               int arg1 = -1;
               Expression arg1Expr = (Expression)expr.getArguments().get(1);
               Object arg1Val = null;
               if (arg1Expr instanceof PrimaryExpression) {
                  arg1Val = eval.getValueForPrimaryExpression((PrimaryExpression)arg1Expr);
               } else if (arg1Expr instanceof ParameterExpression) {
                  arg1Val = QueryUtils.getValueForParameterExpression(eval.getParameterValues(), (ParameterExpression)arg1Expr);
               } else if (arg1Expr instanceof Literal) {
                  arg1Val = ((Literal)arg1Expr).getLiteral();
               } else if (arg0Expr instanceof DyadicExpression) {
                  arg1Val = ((DyadicExpression)arg1Expr).evaluate(eval);
               }

               if (!(arg1Val instanceof Number)) {
                  throw new NucleusException(method + "(param1,param2) : param2 must be numeric");
               }

               arg1 = ((Number)arg1Val).intValue();
               if (((String)invokedValue).length() < arg1) {
                  if (((String)invokedValue).length() < arg0) {
                     return null;
                  }

                  return ((String)invokedValue).substring(arg0);
               }

               result = ((String)invokedValue).substring(arg0, arg1);
            } else {
               if (((String)invokedValue).length() < arg0) {
                  return null;
               }

               result = ((String)invokedValue).substring(arg0);
            }

            return result;
         }
      }
   }
}
