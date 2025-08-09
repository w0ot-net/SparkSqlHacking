package com.univocity.parsers.common.processor;

import com.univocity.parsers.common.processor.core.CompositeProcessor;
import com.univocity.parsers.common.processor.core.Processor;

public class CompositeRowProcessor extends CompositeProcessor implements RowProcessor {
   public CompositeRowProcessor(Processor... processors) {
      super(processors);
   }
}
