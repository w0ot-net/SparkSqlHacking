package com.univocity.parsers.common.processor;

import com.univocity.parsers.common.DefaultConversionProcessor;
import com.univocity.parsers.common.NormalizedString;

public class ObjectRowWriterProcessor extends DefaultConversionProcessor implements RowWriterProcessor {
   private NormalizedString[] normalizedHeaders;
   private String[] previousHeaders;

   public Object[] write(Object[] input, String[] headers, int[] indexesToWrite) {
      if (this.previousHeaders != headers) {
         this.previousHeaders = headers;
         this.normalizedHeaders = NormalizedString.toArray(headers);
      }

      return this.write(input, this.normalizedHeaders, indexesToWrite);
   }

   public Object[] write(Object[] input, NormalizedString[] headers, int[] indexesToWrite) {
      if (input == null) {
         return null;
      } else {
         Object[] output = new Object[input.length];
         System.arraycopy(input, 0, output, 0, input.length);
         return this.reverseConversions(false, output, headers, indexesToWrite) ? output : null;
      }
   }
}
