package org.datanucleus.api.jdo.query.inmemory;

import javax.jdo.JDOHelper;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.query.QueryUtils;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.query.expression.InvokeExpression;
import org.datanucleus.query.expression.ParameterExpression;
import org.datanucleus.query.expression.PrimaryExpression;
import org.datanucleus.query.inmemory.InMemoryExpressionEvaluator;
import org.datanucleus.query.inmemory.InvocationEvaluator;

public class JDOHelperGetObjectIdFunction implements InvocationEvaluator {
   public Object evaluate(InvokeExpression expr, Object invokedValue, InMemoryExpressionEvaluator eval) {
      Expression argExpr = (Expression)expr.getArguments().get(0);
      if (argExpr instanceof PrimaryExpression) {
         PrimaryExpression primExpr = (PrimaryExpression)argExpr;
         Object value = eval.getValueForPrimaryExpression(primExpr);
         return JDOHelper.getObjectId(value);
      } else if (argExpr instanceof ParameterExpression) {
         ParameterExpression paramExpr = (ParameterExpression)argExpr;
         Object value = QueryUtils.getValueForParameterExpression(eval.getParameterValues(), paramExpr);
         return JDOHelper.getObjectId(value);
      } else {
         throw new NucleusException("Dont currently support JDOHelper.getObjectId with arg of type " + argExpr.getClass().getName());
      }
   }
}
