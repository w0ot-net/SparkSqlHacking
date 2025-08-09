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

public class StringConcatMethod implements InvocationEvaluator {
   public Object evaluate(InvokeExpression expr, Object invokedValue, InMemoryExpressionEvaluator eval) {
      String method = expr.getOperation();
      if (invokedValue == null) {
         return null;
      } else if (!(invokedValue instanceof String)) {
         throw new NucleusException(Localiser.msg("021011", method, invokedValue.getClass().getName()));
      } else {
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

         if (!(arg0Val instanceof String)) {
            throw new NucleusException(method + "(param1) : param1 must be String");
         } else {
            return ((String)invokedValue).concat((String)arg0Val);
         }
      }
   }
}
