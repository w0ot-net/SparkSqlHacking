package org.datanucleus.query.inmemory;

import java.sql.Timestamp;
import java.util.Date;
import org.datanucleus.query.expression.InvokeExpression;

public class CurrentTimestampFunction implements InvocationEvaluator {
   public Object evaluate(InvokeExpression expr, Object invokedValue, InMemoryExpressionEvaluator eval) {
      return new Timestamp((new Date()).getTime());
   }
}
