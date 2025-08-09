package org.datanucleus.query.inmemory;

import java.util.List;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.query.expression.InvokeExpression;
import org.datanucleus.util.Localiser;

public class StringTrimRightMethod implements InvocationEvaluator {
   public Object evaluate(InvokeExpression expr, Object invokedValue, InMemoryExpressionEvaluator eval) {
      String method = expr.getOperation();
      List args = expr.getArguments();
      char trimChar = ' ';
      if (args != null && args.size() > 0) {
         trimChar = (Character)args.get(0);
      }

      if (invokedValue == null) {
         return null;
      } else if (!(invokedValue instanceof String)) {
         throw new NucleusException(Localiser.msg("021011", method, invokedValue.getClass().getName()));
      } else {
         String strValue = (String)invokedValue;
         int substringPos = strValue.length();

         for(int i = strValue.length() - 1; i >= 0 && strValue.charAt(i) == trimChar; --i) {
            --substringPos;
         }

         return strValue.substring(0, substringPos);
      }
   }
}
