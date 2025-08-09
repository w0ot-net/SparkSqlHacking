package org.apache.commons.text.translate;

import java.io.IOException;
import java.io.Writer;
import org.apache.commons.lang3.Range;

public class NumericEntityEscaper extends CodePointTranslator {
   private final boolean between;
   private final Range range;

   public static NumericEntityEscaper above(int codePoint) {
      return outsideOf(0, codePoint);
   }

   public static NumericEntityEscaper below(int codePoint) {
      return outsideOf(codePoint, Integer.MAX_VALUE);
   }

   public static NumericEntityEscaper between(int codePointLow, int codePointHigh) {
      return new NumericEntityEscaper(codePointLow, codePointHigh, true);
   }

   public static NumericEntityEscaper outsideOf(int codePointLow, int codePointHigh) {
      return new NumericEntityEscaper(codePointLow, codePointHigh, false);
   }

   public NumericEntityEscaper() {
      this(0, Integer.MAX_VALUE, true);
   }

   private NumericEntityEscaper(int below, int above, boolean between) {
      this.range = Range.of(below, above);
      this.between = between;
   }

   public boolean translate(int codePoint, Writer writer) throws IOException {
      if (this.between != this.range.contains(codePoint)) {
         return false;
      } else {
         writer.write("&#");
         writer.write(Integer.toString(codePoint, 10));
         writer.write(59);
         return true;
      }
   }
}
