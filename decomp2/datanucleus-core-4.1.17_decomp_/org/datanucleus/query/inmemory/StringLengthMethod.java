package org.datanucleus.query.inmemory;

import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.query.expression.InvokeExpression;
import org.datanucleus.util.Localiser;

public class StringLengthMethod implements InvocationEvaluator {
   public Object evaluate(InvokeExpression expr, Object invokedValue, InMemoryExpressionEvaluator eval) {
      String method = expr.getOperation();
      if (invokedValue == null) {
         return 0;
      } else if (invokedValue instanceof String) {
         return ((String)invokedValue).length();
      } else {
         throw new NucleusException(Localiser.msg("021011", method, invokedValue.getClass().getName()));
      }
   }
}
