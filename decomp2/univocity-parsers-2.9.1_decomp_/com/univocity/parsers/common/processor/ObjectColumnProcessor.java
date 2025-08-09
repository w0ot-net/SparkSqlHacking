package com.univocity.parsers.common.processor;

import com.univocity.parsers.common.processor.core.AbstractObjectColumnProcessor;

public class ObjectColumnProcessor extends AbstractObjectColumnProcessor implements RowProcessor {
   public ObjectColumnProcessor() {
      this(1000);
   }

   public ObjectColumnProcessor(int expectedRowCount) {
      super(expectedRowCount);
   }
}
