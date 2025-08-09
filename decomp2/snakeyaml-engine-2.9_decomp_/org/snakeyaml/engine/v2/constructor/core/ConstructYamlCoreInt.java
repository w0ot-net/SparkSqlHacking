package org.snakeyaml.engine.v2.constructor.core;

import java.math.BigInteger;
import org.snakeyaml.engine.v2.constructor.ConstructScalar;
import org.snakeyaml.engine.v2.exceptions.ConstructorException;
import org.snakeyaml.engine.v2.nodes.Node;

public class ConstructYamlCoreInt extends ConstructScalar {
   private static final int[][] RADIX_MAX = new int[17][2];

   private static int maxLen(int max, int radix) {
      return Integer.toString(max, radix).length();
   }

   private static int maxLen(long max, int radix) {
      return Long.toString(max, radix).length();
   }

   protected static Number createLongOrBigInteger(String number, int radix) {
      try {
         return Long.valueOf(number, radix);
      } catch (NumberFormatException var3) {
         return new BigInteger(number, radix);
      }
   }

   public Object construct(Node node) {
      String value = this.constructScalar(node);
      if (value.isEmpty()) {
         throw new ConstructorException("while constructing an int", node.getStartMark(), "found empty value", node.getStartMark());
      } else {
         return this.createIntNumber(value);
      }
   }

   public Object createIntNumber(String value) {
      int sign = 1;
      char first = value.charAt(0);
      if (first == '-') {
         sign = -1;
         value = value.substring(1);
      } else if (first == '+') {
         value = value.substring(1);
      }

      if ("0".equals(value)) {
         return 0;
      } else {
         int base;
         if (value.startsWith("0x")) {
            value = value.substring(2);
            base = 16;
         } else {
            if (!value.startsWith("0o")) {
               return this.createNumber(sign, value, 10);
            }

            value = value.substring(2);
            base = 8;
         }

         return this.createNumber(sign, value, base);
      }
   }

   private Number createNumber(int sign, String number, int radix) {
      int len = number != null ? number.length() : 0;
      if (sign < 0) {
         number = "-" + number;
      }

      int[] maxArr = radix < RADIX_MAX.length ? RADIX_MAX[radix] : null;
      if (maxArr != null) {
         boolean gtInt = len > maxArr[0];
         if (gtInt) {
            if (len > maxArr[1]) {
               return new BigInteger(number, radix);
            }

            return createLongOrBigInteger(number, radix);
         }
      }

      Number result;
      try {
         result = Integer.valueOf(number, radix);
      } catch (NumberFormatException var8) {
         result = createLongOrBigInteger(number, radix);
      }

      return result;
   }

   static {
      int[] radixList = new int[]{8, 10, 16};

      for(int radix : radixList) {
         RADIX_MAX[radix] = new int[]{maxLen(Integer.MAX_VALUE, radix), maxLen(Long.MAX_VALUE, radix)};
      }

   }
}
