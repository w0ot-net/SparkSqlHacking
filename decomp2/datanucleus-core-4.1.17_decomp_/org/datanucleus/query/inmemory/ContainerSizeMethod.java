package org.datanucleus.query.inmemory;

import java.util.Collection;
import java.util.Map;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.query.expression.InvokeExpression;
import org.datanucleus.util.Localiser;

public class ContainerSizeMethod implements InvocationEvaluator {
   public Object evaluate(InvokeExpression expr, Object invokedValue, InMemoryExpressionEvaluator eval) {
      String method = expr.getOperation();
      Integer result = null;
      if (invokedValue == null) {
         result = 0;
      } else if (invokedValue instanceof Collection) {
         result = ((Collection)invokedValue).size();
      } else {
         if (!(invokedValue instanceof Map)) {
            throw new NucleusException(Localiser.msg("021011", method, invokedValue.getClass().getName()));
         }

         result = ((Map)invokedValue).size();
      }

      return result;
   }
}
