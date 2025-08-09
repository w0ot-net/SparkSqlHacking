package org.datanucleus.query.inmemory;

import java.util.Collection;
import java.util.Map;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.query.expression.InvokeExpression;
import org.datanucleus.util.Localiser;

public class ContainerIsEmptyMethod implements InvocationEvaluator {
   public Object evaluate(InvokeExpression expr, Object invokedValue, InMemoryExpressionEvaluator eval) {
      String method = expr.getOperation();
      Boolean result = null;
      if (invokedValue == null) {
         result = Boolean.TRUE;
      } else if (invokedValue instanceof Collection) {
         result = ((Collection)invokedValue).isEmpty() ? Boolean.TRUE : Boolean.FALSE;
      } else {
         if (!(invokedValue instanceof Map)) {
            throw new NucleusException(Localiser.msg("021011", method, invokedValue.getClass().getName()));
         }

         result = ((Map)invokedValue).isEmpty() ? Boolean.TRUE : Boolean.FALSE;
      }

      return result;
   }
}
