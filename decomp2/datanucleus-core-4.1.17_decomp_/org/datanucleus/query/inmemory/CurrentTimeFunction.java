package org.datanucleus.query.inmemory;

import java.sql.Time;
import java.util.Date;
import org.datanucleus.query.expression.InvokeExpression;

public class CurrentTimeFunction implements InvocationEvaluator {
   public Object evaluate(InvokeExpression expr, Object invokedValue, InMemoryExpressionEvaluator eval) {
      return new Time((new Date()).getTime());
   }
}
