package com.univocity.parsers.common.processor;

import com.univocity.parsers.common.processor.core.AbstractListProcessor;

public class RowListProcessor extends AbstractListProcessor implements RowProcessor {
   public RowListProcessor() {
   }

   public RowListProcessor(int expectedRowCount) {
      super(expectedRowCount);
   }
}
