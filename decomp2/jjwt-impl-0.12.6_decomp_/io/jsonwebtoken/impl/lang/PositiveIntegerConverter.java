package io.jsonwebtoken.impl.lang;

import io.jsonwebtoken.lang.Assert;
import java.util.concurrent.atomic.AtomicInteger;

public class PositiveIntegerConverter implements Converter {
   public static final PositiveIntegerConverter INSTANCE = new PositiveIntegerConverter();

   public Object applyTo(Integer integer) {
      return integer;
   }

   public Integer applyFrom(Object o) {
      Assert.notNull(o, "Argument cannot be null.");
      int i;
      if (!(o instanceof Byte) && !(o instanceof Short) && !(o instanceof Integer) && !(o instanceof AtomicInteger)) {
         String sval = String.valueOf(o);

         try {
            i = Integer.parseInt(sval);
         } catch (NumberFormatException e) {
            String msg = "Value cannot be represented as a java.lang.Integer.";
            throw new IllegalArgumentException(msg, e);
         }
      } else {
         i = ((Number)o).intValue();
      }

      if (i <= 0) {
         String msg = "Value must be a positive integer.";
         throw new IllegalArgumentException(msg);
      } else {
         return i;
      }
   }
}
