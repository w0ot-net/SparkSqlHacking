package shaded.parquet.com.fasterxml.jackson.core.io;

import java.math.BigInteger;
import shaded.parquet.com.fasterxml.jackson.core.internal.shaded.fdp.v2_18_1.JavaBigIntegerParser;

public final class BigIntegerParser {
   private BigIntegerParser() {
   }

   public static BigInteger parseWithFastParser(String valueStr) {
      try {
         return JavaBigIntegerParser.parseBigInteger((CharSequence)valueStr);
      } catch (NumberFormatException nfe) {
         String reportNum = valueStr.length() <= 1000 ? valueStr : valueStr.substring(0, 1000) + " [truncated]";
         throw new NumberFormatException("Value \"" + reportNum + "\" can not be represented as `java.math.BigInteger`, reason: " + nfe.getMessage());
      }
   }

   public static BigInteger parseWithFastParser(String valueStr, int radix) {
      try {
         return JavaBigIntegerParser.parseBigInteger((CharSequence)valueStr, radix);
      } catch (NumberFormatException nfe) {
         String reportNum = valueStr.length() <= 1000 ? valueStr : valueStr.substring(0, 1000) + " [truncated]";
         throw new NumberFormatException("Value \"" + reportNum + "\" can not be represented as `java.math.BigInteger` with radix " + radix + ", reason: " + nfe.getMessage());
      }
   }
}
