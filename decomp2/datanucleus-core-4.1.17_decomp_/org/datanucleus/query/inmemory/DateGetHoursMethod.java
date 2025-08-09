package org.datanucleus.query.inmemory;

import java.util.Calendar;
import java.util.Date;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.query.expression.InvokeExpression;
import org.datanucleus.util.Localiser;

public class DateGetHoursMethod implements InvocationEvaluator {
   public Object evaluate(InvokeExpression expr, Object invokedValue, InMemoryExpressionEvaluator eval) {
      String method = expr.getOperation();
      if (invokedValue == null) {
         return Boolean.FALSE;
      } else if (!(invokedValue instanceof Date)) {
         throw new NucleusException(Localiser.msg("021011", method, invokedValue.getClass().getName()));
      } else {
         Calendar cal = Calendar.getInstance();
         cal.setTime((Date)invokedValue);
         return cal.get(11);
      }
   }
}
