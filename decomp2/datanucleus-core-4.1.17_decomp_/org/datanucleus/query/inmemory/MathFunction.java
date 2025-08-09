package org.datanucleus.query.inmemory;

import java.math.BigDecimal;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.query.QueryUtils;
import org.datanucleus.query.expression.DyadicExpression;
import org.datanucleus.query.expression.InvokeExpression;
import org.datanucleus.query.expression.Literal;
import org.datanucleus.query.expression.ParameterExpression;
import org.datanucleus.query.expression.PrimaryExpression;

public abstract class MathFunction implements InvocationEvaluator {
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
      } else if (param instanceof InvokeExpression) {
         InvokeExpression invokeExpr = (InvokeExpression)param;
         paramValue = eval.getValueForInvokeExpression(invokeExpr);
      } else if (param instanceof Literal) {
         paramValue = ((Literal)param).getLiteral();
      } else {
         if (!(param instanceof DyadicExpression)) {
            throw new NucleusException(method + "(num) where num is instanceof " + param.getClass().getName() + " not supported");
         }

         DyadicExpression dyExpr = (DyadicExpression)param;
         paramValue = dyExpr.evaluate(eval);
      }

      Object result = null;
      if (paramValue instanceof Double) {
         result = new Double(this.evaluateMathFunction((Double)paramValue));
      } else if (paramValue instanceof Float) {
         result = new Float(this.evaluateMathFunction((double)(Float)paramValue));
      } else if (paramValue instanceof BigDecimal) {
         result = new BigDecimal(this.evaluateMathFunction(((BigDecimal)paramValue).doubleValue()));
      } else if (paramValue instanceof Integer) {
         result = new Double(this.evaluateMathFunction(((Integer)paramValue).doubleValue()));
      } else {
         if (!(paramValue instanceof Long)) {
            throw new NucleusException("Not possible to use " + this.getFunctionName() + " on value of type " + paramValue.getClass().getName());
         }

         result = new Double(this.evaluateMathFunction(((Long)paramValue).doubleValue()));
      }

      return result;
   }

   protected abstract String getFunctionName();

   protected abstract double evaluateMathFunction(double var1);
}
