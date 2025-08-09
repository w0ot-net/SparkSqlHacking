package com.univocity.parsers.common.processor;

import com.univocity.parsers.common.ParsingContext;
import com.univocity.parsers.common.ParsingContextSnapshot;
import com.univocity.parsers.common.ParsingContextWrapper;
import com.univocity.parsers.common.processor.core.AbstractConcurrentProcessor;

public class ConcurrentRowProcessor extends AbstractConcurrentProcessor implements RowProcessor {
   public ConcurrentRowProcessor(RowProcessor rowProcessor) {
      super(rowProcessor);
   }

   public ConcurrentRowProcessor(RowProcessor rowProcessor, int limit) {
      super(rowProcessor, limit);
   }

   protected ParsingContext copyContext(ParsingContext context) {
      return new ParsingContextSnapshot(context);
   }

   protected ParsingContext wrapContext(ParsingContext context) {
      return new ParsingContextWrapper(context) {
         public long currentRecord() {
            return ConcurrentRowProcessor.this.getRowCount();
         }
      };
   }
}
