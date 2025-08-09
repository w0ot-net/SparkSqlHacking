package org.datanucleus.query.inmemory;

import java.util.Date;
import org.datanucleus.query.expression.InvokeExpression;

public class CurrentDateFunction implements InvocationEvaluator {
   public Object evaluate(InvokeExpression expr, Object invokedValue, InMemoryExpressionEvaluator eval) {
      return new Date();
   }
}
