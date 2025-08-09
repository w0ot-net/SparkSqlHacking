package org.datanucleus.query.inmemory;

import java.lang.reflect.Array;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.query.expression.InvokeExpression;
import org.datanucleus.util.Localiser;

public class ArraySizeMethod implements InvocationEvaluator {
   public Object evaluate(InvokeExpression expr, Object invokedValue, InMemoryExpressionEvaluator eval) {
      String method = expr.getOperation();
      Integer result = null;
      if (invokedValue == null) {
         result = 0;
      } else {
         if (!invokedValue.getClass().isArray()) {
            throw new NucleusException(Localiser.msg("021011", method, invokedValue.getClass().getName()));
         }

         result = Array.getLength(invokedValue);
      }

      return result;
   }
}
