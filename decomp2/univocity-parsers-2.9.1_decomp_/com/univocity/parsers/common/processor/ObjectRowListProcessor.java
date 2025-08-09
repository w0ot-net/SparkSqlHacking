package com.univocity.parsers.common.processor;

import com.univocity.parsers.common.processor.core.AbstractObjectListProcessor;

public class ObjectRowListProcessor extends AbstractObjectListProcessor implements RowProcessor {
   public ObjectRowListProcessor() {
   }

   public ObjectRowListProcessor(int expectedRowCount) {
      super(expectedRowCount);
   }
}
