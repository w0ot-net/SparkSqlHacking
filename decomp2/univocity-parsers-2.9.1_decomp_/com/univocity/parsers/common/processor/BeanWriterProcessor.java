package com.univocity.parsers.common.processor;

import com.univocity.parsers.annotations.helpers.MethodFilter;
import com.univocity.parsers.common.NormalizedString;
import com.univocity.parsers.common.fields.FieldConversionMapping;
import com.univocity.parsers.common.processor.core.BeanConversionProcessor;

public class BeanWriterProcessor extends BeanConversionProcessor implements RowWriterProcessor {
   private NormalizedString[] normalizedHeaders;
   private String[] previousHeaders;

   public BeanWriterProcessor(Class beanType) {
      super(beanType, MethodFilter.ONLY_GETTERS);
   }

   public Object[] write(Object input, String[] headers, int[] indexesToWrite) {
      if (this.previousHeaders != headers) {
         this.previousHeaders = headers;
         this.normalizedHeaders = NormalizedString.toArray(headers);
      }

      return this.write(input, this.normalizedHeaders, indexesToWrite);
   }

   public Object[] write(Object input, NormalizedString[] headers, int[] indexesToWrite) {
      if (!this.initialized) {
         super.initialize(headers);
      }

      return this.reverseConversions(input, headers, indexesToWrite);
   }

   protected FieldConversionMapping cloneConversions() {
      return null;
   }
}
