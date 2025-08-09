package org.datanucleus.query.inmemory;

import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.query.QueryUtils;
import org.datanucleus.query.expression.DyadicExpression;
import org.datanucleus.query.expression.InvokeExpression;
import org.datanucleus.query.expression.Literal;
import org.datanucleus.query.expression.ParameterExpression;
import org.datanucleus.query.expression.PrimaryExpression;
import org.datanucleus.util.Localiser;

public class StringCharAtMethod implements InvocationEvaluator {
   public Object evaluate(InvokeExpression expr, Object invokedValue, InMemoryExpressionEvaluator eval) {
      String method = expr.getOperation();
      if (invokedValue == null) {
         return -1;
      } else if (!(invokedValue instanceof String)) {
         throw new NucleusException(Localiser.msg("021011", method, invokedValue.getClass().getName()));
      } else {
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
         } else if (param instanceof InvokeExpression) {
            arg1Obj = eval.getValueForInvokeExpression((InvokeExpression)param);
         } else {
            if (!(param instanceof DyadicExpression)) {
               throw new NucleusException(method + "(param1) where param is instanceof " + param.getClass().getName() + " not supported");
            }

            arg1Obj = ((DyadicExpression)param).evaluate(eval);
         }

         if (!(arg1Obj instanceof Number)) {
            throw new NucleusException(method + "(param1]) : param1 must be numeric");
         } else {
            int arg1 = ((Number)arg1Obj).intValue();
            return ((String)invokedValue).indexOf(arg1);
         }
      }
   }
}
