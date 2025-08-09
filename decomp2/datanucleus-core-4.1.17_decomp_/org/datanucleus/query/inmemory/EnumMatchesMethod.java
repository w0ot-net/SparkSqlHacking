package org.datanucleus.query.inmemory;

import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.query.QueryUtils;
import org.datanucleus.query.expression.InvokeExpression;
import org.datanucleus.query.expression.Literal;
import org.datanucleus.query.expression.ParameterExpression;
import org.datanucleus.query.expression.PrimaryExpression;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class EnumMatchesMethod implements InvocationEvaluator {
   public Object evaluate(InvokeExpression expr, Object invokedValue, InMemoryExpressionEvaluator eval) {
      String method = expr.getOperation();
      if (invokedValue == null) {
         return Boolean.FALSE;
      } else if (!(invokedValue instanceof Enum)) {
         throw new NucleusException(Localiser.msg("021011", method, invokedValue.getClass().getName()));
      } else {
         String arg = null;
         Object argObj = null;
         Object param = expr.getArguments().get(0);
         if (expr.getArguments().size() > 1) {
            NucleusLogger.QUERY.info("Please note that any escape character is currently ignored");
         }

         if (param instanceof PrimaryExpression) {
            PrimaryExpression primExpr = (PrimaryExpression)param;
            argObj = eval.getValueForPrimaryExpression(primExpr);
         } else if (param instanceof ParameterExpression) {
            ParameterExpression paramExpr = (ParameterExpression)param;
            argObj = QueryUtils.getValueForParameterExpression(eval.getParameterValues(), paramExpr);
         } else {
            if (!(param instanceof Literal)) {
               throw new NucleusException(method + "(param, num1, num2) where param is instanceof " + param.getClass().getName() + " not supported");
            }

            argObj = ((Literal)param).getLiteral();
         }

         arg = QueryUtils.getStringValue(argObj);
         return ((Enum)invokedValue).toString().matches(arg) ? Boolean.TRUE : Boolean.FALSE;
      }
   }
}
